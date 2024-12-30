package com.fno.back.workflow.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.fno.back.common.domain.SysBillType;
import com.fno.back.common.service.SysBillTypeService;
import com.fno.back.workflow.mapper.FlowCcedMapper;
import com.fno.back.workflow.domain.CommitTask;
import com.fno.back.workflow.domain.FlowCced;
import com.fno.back.workflow.domain.VTasklist;
import com.fno.back.workflow.mapper.FlowProcessMapper;
import com.fno.back.workflow.mapper.TaskListenerMapper;
import com.fno.back.workflow.mapper.VTasklistMapper;
import com.fno.back.common.constant.CommonConstants;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.exception.ServiceException;
import com.fno.back.common.service.base.BaseService;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import com.fno.system.mapper.SysUserMapper;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.DelegationState;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 我的待办Service业务层处理
 * 
 * @author fno
 * @date 2022-07-16
 */
@Service
public class VTasklistService extends BaseService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(VTasklistService.class);
    @Autowired
    private VTasklistMapper vTasklistMapper;
    @Autowired
    private TaskListenerMapper taskListenerMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private SysBillTypeService oaBillTypeService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private VTasklistService vTasklistService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private FlowCcedMapper oaFlowCcedMapper;
    @Autowired
    private FlowProcessMapper flowProcessMapper;

    /**
     * 查询我的待办
     * 
     * @param taskid 我的待办主键
     * @return 我的待办
     */
    public VTasklist selectVTasklistByTaskid(String taskid)
    {
        return vTasklistMapper.selectVTasklistByTaskid(taskid);
    }

    /**
     * 查询我的待办列表
     * 
     * @param vTasklist 我的待办
     * @return 我的待办
     */
    public List<VTasklist> selectVTasklistList(VTasklist vTasklist)
    {
        return vTasklistMapper.selectVTasklistList(vTasklist);
    }


    /****
     * 待办任务数量
     * @return
     */
    public int queryTodoTaskCount(){
        int count = 0;
        String userId = SecurityUtils.getUserId().toString();
        List<VTasklist> list = vTasklistService.selectTodoTaskList(userId,"","");
        if(list!=null){
            count = count + list.size();
        }
        return count;
    }


    /****
     * 查询审批历史
     * @param insId
     * @return
     */
    public List<VTasklist> selectHisByInsId(String insId){
        List<VTasklist> list =  vTasklistMapper.selectHisByInsId(insId);
        for(VTasklist t:list){
            //如果，没有办结，并且，没有办理人-----历史任务可能会没有办理人
            if(StringUtils.isBlank(t.getAssignee()) && StringUtils.isBlank(t.getDueTime())){
                //查正在运行的时候
                VTasklist task = vTasklistMapper.selectVTasklistByTaskid(t.getTaskid());
                if(task!=null&&StringUtils.isNotBlank(task.getAssignee())){
                    Long userId = Long.parseLong(task.getAssignee());
                    SysUser user = sysUserMapper.selectUserById(userId);
                    t.setAssignee(task.getAssignee());
                    t.setNickName(user.getNickName());
                    t.setUserName(user.getUserName());
                }
            }
            //如果没有指定办理人，则一定有候选人
            if(StringUtils.isBlank(t.getAssignee())){
                List<Map> candidateList = vTasklistMapper.getAllCandidate(t.getTaskid());

                List<String> candidateStrList = new ArrayList<>();
                for(Map<String,String> map:candidateList){
                    candidateStrList.add(map.get("nickName")+"["+map.get("userName")+"]");
                }
                String candidateStr = StringUtils.join(candidateStrList,";");
                t.setCandidateName(candidateStr);
            }else{
                t.setNickName(t.getNickName()+"["+t.getUserName()+"]");
            }
            if(StringUtils.isNotBlank(t.getDueTime())){
                t.setCompleteStatus("已完成");
            }else{
                t.setCompleteStatus("未审批");
            }
        }
        return list;
    }

    /****
     * 查询审批历史记录
     * @param businessId
     * @param billType
     * @return
     */
    public List<VTasklist> getHisByBusinessId(Long businessId,String billType){
        SysBillType sysBillType = oaBillTypeService.selectOaBillTypeByBillType(billType);
        LinkedList<VTasklist> result = new LinkedList<>();
        //第一次申请的流程实例Id
        String firstInsId = "";
        String businessKey = billType+":"+ sysBillType.getTableName()+":"+businessId;
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).orderByProcessInstanceStartTime().asc().list();
        if(list!=null&&list.size()>0){
            firstInsId = list.get(0).getId();
        }
        for(HistoricProcessInstance his:list){
            String insId = his.getId();
            List<VTasklist> l = vTasklistService.selectHisByInsId(insId);
            result.addAll(l);
        }

        //查询申请人----第一个流程实例提交的人
        try{
            Map object = flowProcessMapper.selectObjectById(sysBillType.getTableName(),businessId);
            SysUser applier = sysUserMapper.selectUserById(Long.parseLong(object.get("create_by").toString()));
            VTasklist apply = new VTasklist();
            apply.setTaskname("提交申请");
            apply.setNickName(applier.getNickName());
            if(object.get("submit_time")!=null){
                apply.setDueTime(object.get("submit_time").toString());
            }else{
                HistoricProcessInstance first = historyService.createHistoricProcessInstanceQuery().processInstanceId(firstInsId).singleResult();
                apply.setDueTime(DateUtil.formatDateTime(first.getStartTime()));
            }
            apply.setCompleteStatus("已完成");
            result.addFirst(apply);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

        return result;
    }


    /****
     * 查询待办任务列表
     * @param
     * @return
     */
    public List<VTasklist> selectTodoTaskList(String userId,String key,String name ){
        return vTasklistMapper.selectTodoTaskList(userId,key,name);
    }

    /****
     * 查询我的已办任务
     * @param
     * @return
     */
    public List<Map> selectHaveDoneTaskList(String userId,String key,String name ){
        return vTasklistMapper.selectHaveDoneTaskList(userId,key,name);
    }

    /*****
     * 完成任务
     * @param commitTask
     */
    @Transactional(rollbackFor = Exception.class)
    public void commitTask(CommitTask commitTask){
        String taskId = commitTask.getTaskId();
        String insId = commitTask.getInsId();
        String comment = commitTask.getComment();
        String operateType = commitTask.getOperateType();
        String ifCced = commitTask.getIfCced();

        // 添加环境变量----同意
        taskService.setVariable(taskId, CommonConstants.FLOW_FINISH_TYPE, CommonConstants.FLOW_FINISH_TYPE_AGREE);

        // 当前任务 task
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if(ObjectUtil.isEmpty(task)){
            throw new ServiceException("任务已完成，没有待处理任务");
        }

        //【抄送】--抄送单独维护表----需要先做抄送，如果办理完了，流程实例可能用runtimeservice查不到。因为commit之后流程可能会结束
        if(CommonConstants.TASK_CIMMIT_IS_CCED.equals(ifCced)){
            String str = commitTask.getCcedListStr();
            if(StringUtils.isNotBlank(str)){
                String[] idarr = str.split(",");
                ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                String businessKey = instance.getBusinessKey();
                if(StringUtils.isNotBlank(businessKey)){
                    //billType+":"+tableName+":"+id
                    String[] keyArr = businessKey.split(":");
                    String billType = keyArr[0];
                    String tableName = keyArr[1];
                    String businessId = keyArr[2];
                    Object orderinfo = runtimeService.getVariable(task.getExecutionId(),CommonConstants.APPLY_VARS_ORDERINFO);
                    if(orderinfo!=null) {
                        String orderinfoStr = orderinfo.toString();
                        JSONObject order = JSONObject.parseObject(orderinfoStr);
                        String title = order.getString(CommonConstants.APPLY_VARS_TITLE);
                        String isCustomForm = order.getString(CommonConstants.APPLY_VARS_ISCUSTOMFORM);
                        List<FlowCced> batchList = new ArrayList<>();
                        for(String id:idarr){
                            FlowCced c = new FlowCced();
                            c.setFromUserId(SecurityUtils.getUserId());
                            c.setToUserId(Long.parseLong(id));
                            c.setBusinessKey(businessKey);
                            c.setFlowKey(instance.getProcessDefinitionKey());
                            c.setBillType(billType);
                            c.setBusinessId(Long.parseLong(businessId));
                            c.setFlowInsId(instance.getId());
                            c.setCreateTime(new Date());
                            c.setTableName(tableName);
                            c.setTitle(title);
                            c.setIsCustomForm(isCustomForm);
                            batchList.add(c);
                        }
                        oaFlowCcedMapper.insertBatch(batchList);
                    }
                }
            }
        }


        //【办理】--正常流程流转
        if(CommonConstants.TASK_OPERATE_TYPE_COMMIT.equals(operateType)){
            //查询候选人集合---有候选人的话，先领取--领取后其他人将无需办理
            List<VTasklist> list = vTasklistMapper.getIfCanClaim(taskId,SecurityUtils.getUserId());
            if(list!=null&&list.size()>0){
                taskService.claim(taskId, SecurityUtils.getUserId().toString());
            }
            //查询是否是办理委托任务
            if(DelegationState.PENDING.equals(task.getDelegationState())){
                taskService.addComment(taskId,insId,"【受委托办理】办理完成。办理意见："+comment);
                taskService.resolveTask(taskId);
            }else{
                taskService.addComment(taskId,insId,"【同意】"+comment);
                taskService.complete(taskId);
            }
        }
        //【转办】--任务转交给他人
        else if(CommonConstants.TASK_OPERATE_TYPE_TRANS.equals(operateType)){
            Long transUserId = commitTask.getTransUserId();
            if(ObjectUtil.isEmpty(transUserId)){
                throw new ServiceException("获取转办接收人失败");
            }
            SysUser user = sysUserMapper.selectUserById(transUserId);
            if(ObjectUtil.isEmpty(user)){
                throw new ServiceException("获取转办接收人失败");
            }
            taskService.addComment(taskId,insId,"【转办】"+comment);
            taskService.setAssignee(taskId, user.getUserId().toString());
        }
        //【委托】--委托他人处理一下
        else if(CommonConstants.TASK_OPERATE_TYPE_DELEGATE.equals(operateType)){
            taskService.addComment(taskId,insId,"【委托】"+comment);
            this.delegateTask(commitTask);
        }
    }



    //Q：为什么在被委托人C处理通过后，在其已办列表中无法看到这条工作流信息？
    //A：由于委托人和处理人的转换，是基于一条任务数据上变化的。所以在被委派人C处理通过后，
    // 这条工作流信息并不会存在于被委派人C的已办列表中。C的审批记录只会保存在act_hi_comment历史审批表中。

    /****
     * 委托任务
     * @param commitTask
     */
    public void delegateTask(CommitTask commitTask){
        String taskId = commitTask.getTaskId();
        String insId = commitTask.getInsId();
        String comment = commitTask.getComment();
        Long userId = commitTask.getDelegateUserId();
        SysUser delegateUser = sysUserMapper.selectUserById(userId);
        if(ObjectUtil.isEmpty(delegateUser)){
            throw new ServiceException("获取被委托人失败！");
        }
        // 添加审批意见
        taskService.addComment(taskId,insId,"【委托】：他人委托["+delegateUser.getNickName()+"]办理。委托意见："+comment);
        // 当前任务 task
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (ObjectUtil.isEmpty(task)) {
            throw new ServiceException("获取任务失败！");
        }
        //可能会多次委托，拥有者，必须始终为第一次委托的用户
        if (StringUtils.isBlank(task.getOwner())) {
            // 设置办理人为当前登录人
            taskService.setOwner(taskId, SecurityUtils.getUserId().toString());
        }
        // 执行委派
        taskService.delegateTask(taskId, delegateUser.getUserId().toString());
    }










    /*****
     * 驳回任务
     * @param taskId
     */
    public void rejectTask(String taskId,String insId,String comment){
        //查询候选人集合
        List<VTasklist> list = vTasklistMapper.getIfCanClaim(taskId,SecurityUtils.getUserId());
        if(list!=null&&list.size()>0){
            taskService.claim(taskId, SecurityUtils.getUserId().toString());
        }
        taskService.addComment(taskId,insId,"【驳回】"+comment);
        //驳回的时候，将历史任务办理人更新为当前人
        taskListenerMapper.updateHiTaskAssignee(taskId,SecurityUtils.getUserId().toString());
        flowProcessInstanceService.stopProcessInstanceById(insId);
    }
}
