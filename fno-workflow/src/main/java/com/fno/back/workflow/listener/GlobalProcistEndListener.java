package com.fno.back.workflow.listener;

import com.alibaba.fastjson2.JSONObject;
import com.fno.back.common.domain.SysMessage;
import com.fno.back.common.service.SysMessageService;
import com.fno.back.common.domain.SysBillType;
import com.fno.back.common.service.SysBillTypeService;
import com.fno.back.workflow.domain.ProcessTableUpdateDomain;
import com.fno.back.workflow.mapper.FlowProcessMapper;
import com.fno.back.common.constant.CommonConstants;
import com.fno.back.workflow.service.business.FlowCallBusinessService;
import org.flowable.common.engine.api.delegate.event.FlowableEngineEntityEvent;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/***
 * @des
 * 流程结束全局监听器
 * @author Ly
 * @date 2023/5/31
 */

@Component
public class GlobalProcistEndListener extends AbstractFlowableEngineEventListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FlowProcessMapper flowProcessMapper;
    @Autowired
    private SysMessageService sysMessageService;
    @Autowired
    private SysBillTypeService sysBillTypeService;
    @Autowired
    private FlowCallBusinessService flowCallBusinessService;




    @Override
    protected void processCompleted(FlowableEngineEntityEvent event) {
        logger.info("进入流程完成监听器------------------------Start---------------------->");
        String eventName = event.getType().name();

        FlowableEntityEventImpl flowableEntityEvent = (FlowableEntityEventImpl) event;
        ExecutionEntityImpl processInstance = (ExecutionEntityImpl) flowableEntityEvent.getEntity();
        //获取结束类型----如果是驳回的话，类型会是reject
        Object finishType = processInstance.getVariable(CommonConstants.FLOW_FINISH_TYPE);
        //默认为审批通过
        String status = CommonConstants.FLOW_STATUS_FINISH;
        String insId = null;
        //如果结束状态不为空，并为【驳回】，则修改单据状态
        if(finishType!=null){
            if(CommonConstants.FLOW_FINISH_TYPE_REJECT.equals(finishType.toString())){
                status = CommonConstants.FLOW_STATUS_REJECT;
                insId = "";//流程id清空
            }
        }
        String businessKey = processInstance.getBusinessKey();
        String[] arr = businessKey.split(":");
        String billType = arr[0];
        String tablename = arr[1];
        String businessId = arr[2];
        Long id = Long.parseLong(businessId);

        ProcessTableUpdateDomain updater = new ProcessTableUpdateDomain();
        updater.setId(id);
        updater.setTableName(tablename);
        updater.setStatus(status);
        updater.setInsId(insId);
        flowProcessMapper.updateBusinessBillStatus(updater);

        //流程申请结束，为申请人发送站内信
        //{"title":"[管理员]的请假申请单","userId":1,"nickname":"管理员","applytime":"2023-08-17 08:55:43","iscustomform":"N"}
        Object orderinfo = processInstance.getVariable(CommonConstants.APPLY_VARS_ORDERINFO);
        if(orderinfo!=null){
            String orderinfoStr = orderinfo.toString();
            JSONObject order = JSONObject.parseObject(orderinfoStr);
            if(order!=null){
                SysBillType sysBillType = sysBillTypeService.selectOaBillTypeByBillType(billType);
                //String iscustomform = order.getString(CommonConstants.APPLY_VARS_ISCUSTOMFORM);
                String userId = order.getString(CommonConstants.APPLY_VARS_USERID);
                String title = order.getString(CommonConstants.APPLY_VARS_TITLE);
                String nickname = order.getString(CommonConstants.APPLY_VARS_NICKNAME);
                String applytime = order.getString(CommonConstants.APPLY_VARS_APPLYTIME);
                String msg = nickname+"，您好。您在"+applytime+"发起的【"+ sysBillType.getBillName() +"】，标题为："+title+"，";
                String msgTitle = "【"+ sysBillType.getBillName()+"】审批结果：";
                //判断是被驳回还是审批完成----
                if(finishType!=null&&CommonConstants.FLOW_FINISH_TYPE_REJECT.equals(finishType.toString())){
                    msg = msg + "被【审核人驳回】，请您悉知。谢谢。";
                    msgTitle = msgTitle + "【驳回】";
                }else{
                    msg = msg + "已经【审批通过】，请您悉知。谢谢。";
                    msgTitle = msgTitle + "【通过】";
                }
                SysMessage sysMessage = new SysMessage();
                sysMessage.setReceiveUserId(Long.parseLong(userId));
                sysMessage.setContent(msg);
                sysMessage.setTitle(msgTitle);
                sysMessage.setSendTime(new Date());
                sysMessageService.insertSysMessage(sysMessage);


                // 部分业务场景比较复杂，无法通过billtype中的表名进行操作，使用反射，回调用其他业务模块
                // （正常调用无法实例化对象maven依赖的问题，其他业务模块依赖流程中心，流程中心无法再依赖其他业务模块，会造成循环依赖）
                try {
                    flowCallBusinessService.flowFinishCall(sysBillType, id);
                } catch (Exception e) {
                    logger.error("【流程中心】-流程结束调用业务模块功能报错",e);
                    throw new RuntimeException(e);
                }
            }
        }



        Date startTime = processInstance.getStartTime();
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        String processInstanceId = processInstance.getProcessInstanceId();
        String processInstanceBusinessKey = processInstance.getProcessInstanceBusinessKey();
        int suspensionState = processInstance.getSuspensionState();
        logger.info("流程事件类型->{}", eventName);
        logger.info("流程开始时间->{}", startTime);
        logger.info("流程定义Key->{}", processDefinitionKey);
        logger.info("流程实例ID->{}", processInstanceId);
        logger.info("流程业务key->{}", processInstanceBusinessKey);
        logger.info("流程是否挂起标志->{}", suspensionState);
        logger.info("流程完成监听器------------------------End---------------------->");
    }

}