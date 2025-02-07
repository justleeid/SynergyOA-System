package com.fno.back.workflow.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fno.back.common.domain.SysBillType;
import com.fno.back.common.service.SysBillTypeService;
import com.fno.back.workflow.domain.FlowProcessInstance;
import com.fno.back.workflow.domain.ProcessTableUpdateDomain;
import com.fno.back.workflow.mapper.FlowProcessMapper;
import com.fno.back.common.constant.BillTypeConstants;
import com.fno.back.common.constant.CommonConstants;
import com.fno.back.workflow.service.business.FlowCallBusinessService;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.back.common.util.FlowUtil;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import com.fno.system.service.ISysUserService;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/***
 * @des
 */
@Service
public class FlowProcessInstanceService {
    private static final Logger logger = LoggerFactory.getLogger(FlowProcessInstanceService.class);
    @Autowired
    private FlowProcessMapper flowProcessMapper;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private SysBillTypeService oaBillTypeService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private FlowCallBusinessService flowCallBusinessService;

    

    /*****
     * 启动流程---入口，调用其他单据类型服务
     * @param ins
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitFlow(FlowProcessInstance ins ) throws Exception {
        SysBillType sysBillType = oaBillTypeService.selectOaBillTypeByBillType(ins.getBillType());
        flowProcessInstanceService.checkBillType(sysBillType);
        Map object = flowProcessMapper.selectObjectById(sysBillType.getTableName(),ins.getBusinessId());
        String userId = object.get("create_by").toString();
        Long applyUserId = Long.parseLong(userId);
        SysUser user = userService.selectUserById(applyUserId);
        String title = "用户["+user.getNickName()+"]发起的["+ sysBillType.getBillName()+"]";
        String defKey = "";
        String isCustom = "";
        //自定义表单提交
        if(BillTypeConstants.CUSTOM_FORM.equals(ins.getBillType())){
            //OaCustomFormApply customFormApply = oaCustomFormApplyService.selectOaCustomFormApplyById(ins.getBusinessId());
            //defKey = customFormApply.getFlowKey();
            defKey = ins.getDefKey();
            isCustom = CommonConstants.YES;
        }else{
            defKey = sysBillType.getDefKey();
            isCustom = CommonConstants.NO;
        }
        ProcessInstance procIns = flowProcessInstanceService.startProcess(ins.getBusinessId().toString(),title,defKey, sysBillType.getTableName(), sysBillType.getBillType(),isCustom, sysBillType.getAppStatus());
        Date now = new Date();
        ProcessTableUpdateDomain updater =new ProcessTableUpdateDomain();
        updater.setTableName(sysBillType.getTableName());
        updater.setStatus(CommonConstants.FLOW_STATUS_SUBMIT);
        updater.setInsId(procIns.getId());
        updater.setId(ins.getBusinessId());
        updater.setFlowKey(defKey);
        updater.setSubmitTime(now);
        updater.setUpdateTime(now);
        flowProcessMapper.updateBusinessBillStatus(updater);

        //提交后调用业务模块服务
        flowCallBusinessService.flowSubmitCall(sysBillType,ins.getBusinessId());
    }


    public void checkBillType(SysBillType sysBillType){
        if(sysBillType ==null){
            throw new RuntimeException("单据类型未注册，请联系管理人员，从菜单[系统设置]-[单据类型配置]，处维护单据类型相关配置");
        }
        if(StringUtils.isBlank(sysBillType.getDefKey())||StringUtils.isBlank(sysBillType.getTableName())||StringUtils.isBlank(sysBillType.getFormUrl())){
            throw new RuntimeException("单据类型信息不全，请联系管理人员，从菜单[系统设置]-[单据类型配置]，处维护单据类型相关配置");
        }
    }

    /****
     * 取回流程---入口调用其他单据类型服务
     * @param ins
     */
    public void cancleFlow(FlowProcessInstance ins ) throws Exception {
        SysBillType sysBillType = oaBillTypeService.selectOaBillTypeByBillType(ins.getBillType());
        flowProcessInstanceService.checkBillType(sysBillType);
        Map object = flowProcessMapper.selectObjectById(sysBillType.getTableName(),ins.getBusinessId());
        String insId = object.get("flow_ins_id").toString();

        if(StrUtil.isNotBlank(insId)){
            flowProcessInstanceService.cancleProcesInstance(insId,"用户撤销");
        }
        ProcessTableUpdateDomain updater =new ProcessTableUpdateDomain();
        updater.setTableName(sysBillType.getTableName());
        updater.setId(ins.getBusinessId());
        updater.setStatus(CommonConstants.FLOW_STATUS_START);
        updater.setInsId("");
        //自定义流程，不要清空key
        if(!BillTypeConstants.CUSTOM_FORM.equals(ins.getBillType())){
            updater.setFlowKey("");
        }
        updater.setSubmitTime(null);
        updater.setUpdateTime(new Date());
        flowProcessMapper.updateBusinessBillStatus(updater);
        //取消提交申请后，调用业务模块的服务
        flowCallBusinessService.flowCancelCall(sysBillType,ins.getBusinessId());
    }



    /****
     * 流程取回----历史和当前运行都删除
     * @param insId
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancleProcesInstance(String insId, String deleteReason){
        // 执行此方法后未审批的任务 act_ru_task 会被删除，流程历史 act_hi_taskinst 不会被删除，并且流程历史的状态为finished完成
        // 撤销都删除-----需要先删运行的，再删历史的
        ProcessInstance rins = runtimeService.createProcessInstanceQuery().processInstanceId(insId).singleResult();
        if(rins!=null){
            runtimeService.deleteProcessInstance(insId, deleteReason);
        }

        HistoricProcessInstance his = historyService.createHistoricProcessInstanceQuery().processInstanceId(insId).singleResult();
        if(his!=null){
            historyService.deleteHistoricProcessInstance(his.getId());
        }
    }

    /*****
     * 强制终止流程-----不会删除历史记录，流程会直接结束。（历史记录中的流程也会结束，历史审批信息会保留）
     * @param processInstanceId
     */
    @Transactional(rollbackFor = Exception.class)
    public void stopProcessInstanceById(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance != null) {
            //1、获取终止节点---任意一个终止节点
            List<EndEvent> endNodes = findEndFlowElement(processInstance.getProcessDefinitionId());
            String endId = endNodes.get(0).getId();
            //2、执行终止----查出所有的执行流（可能有并行的）。所有执行流均跳转到结束节点
            List<Execution> executions = runtimeService.createExecutionQuery().parentId(processInstanceId).list();
            if(executions!=null && !executions.isEmpty()){
                //查出任意一个执行流---设置处理方式为。驳回
                runtimeService.setVariable(executions.get(0).getId(), CommonConstants.FLOW_FINISH_TYPE, CommonConstants.FLOW_FINISH_TYPE_REJECT);
            }
            List<String> executionIds = new ArrayList<>();
            assert executions != null;
            executions.forEach(execution -> executionIds.add(execution.getId()));
            runtimeService.createChangeActivityStateBuilder().moveExecutionsToSingleActivityId(executionIds, endId).changeState();
        }
    }

    /*****
     * 启动流程
     * @param id
     * @param title
     * @param billType
     */
    public ProcessInstance startProcess(Long id,String title,String billType){

        SysBillType sysBillType = oaBillTypeService.selectOaBillTypeByBillType(billType);

        String tableName = sysBillType.getTableName();
        String defKey = sysBillType.getDefKey();
        if(StrUtil.isBlank(tableName)||StrUtil.isBlank(defKey)){
            throw new RuntimeException("单据类型缺少配置信息");
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Map<String,Object> vars = FlowUtil.buildFlowVars(title,user.getUserId(),user.getNickName(),
                DateUtil.formatDateTime(new Date()), CommonConstants.NO, sysBillType.getAppStatus());

        return runtimeService.startProcessInstanceByKeyAndTenantId(defKey,billType+":"+tableName+":"+id,vars,SecurityUtils.getTenantId().toString());
    }


    /***
     * 启动流程
     * @param id
     * @param title
     * @param defKey
     * @param tableName
     * @param billType
     * @param isCustom
     * @return
     */
    public ProcessInstance startProcess(String id, String title, String defKey, String tableName, String billType, String isCustom, String appStatus){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Map<String,Object> vars = FlowUtil.buildFlowVars(title, user.getUserId(), user.getNickName(), DateUtil.formatDateTime(new Date()), isCustom , appStatus);

        //当使用流程变量userId为流程变量时候
        //vars.put("userId","1");
        //当会签时候测试
        //List<Long> userList = new ArrayList<>();
        //userList.add(1L);
        //userList.add(115L);
        //userList.add(116L);
        //userList.add(117L);
        //userList.add(118L);
        //userList.add(119L);
        //vars.put("userList",userList);

        ProcessInstance procIns = null;
        try{
            procIns = runtimeService.startProcessInstanceByKeyAndTenantId(defKey,billType+":"+tableName+":"+id,vars,SecurityUtils.getTenantId().toString());
        }catch(Exception e){
            logger.error("提交失败：",e);
            if(e.getMessage().contains("is suspended")){
                throw new RuntimeException("提交失败：您的单据需要的【流程定义】已经被【挂起】，请联系【流程管理员】将该【流程定义】【激活】。");
            }else{
                throw new RuntimeException("提交失败：请联系【流程管理员】。");
            }
        }
        return procIns;
    }

    /****
     * 查找结束节点
     * @param processDefId
     * @return
     */
    private List findEndFlowElement(String processDefId) {
        Process mainProcess = repositoryService.getBpmnModel(processDefId).getMainProcess();
        Collection<FlowElement> list = mainProcess.getFlowElements();
        if (CollectionUtil.isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        return list.stream().filter(f -> f instanceof EndEvent).collect(Collectors.toList());
    }




    /****
     * 清空所有流程实例
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteAllProcessInstance(){
        List<HistoricProcessInstance> list =historyService.createHistoricProcessInstanceQuery().list();
        for(HistoricProcessInstance p:list){
            String insId = p.getId();
            ProcessInstance rins = runtimeService.createProcessInstanceQuery().processInstanceId(insId).singleResult();
            if(rins!=null){
                runtimeService.deleteProcessInstance(insId, "清空流程历史记录");
            }
            historyService.deleteHistoricProcessInstance(insId);
        }
        //重置所有业务单据
        SysBillType query = new SysBillType();
        query.setStatus("0");
        List<SysBillType> billList = oaBillTypeService.selectOaBillTypeListAll(query);
        for(SysBillType type:billList){
            try{
                flowProcessMapper.resetBusinessFlowApply(type.getTableName());
            }catch (Exception e){
                logger.error(e.getMessage(), e);
            }

        }

    }


    /****
     * 根据实例id删除流程实例
     * @param insId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteProcessInstanceById(String insId){
        ProcessInstance rins = runtimeService.createProcessInstanceQuery().processInstanceId(insId).singleResult();
        if(rins!=null){
            runtimeService.deleteProcessInstance(insId, "清空流程历史记录");
        }
        historyService.deleteHistoricProcessInstance(insId);

    }

}
