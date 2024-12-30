package com.fno.back.workflow.listener;

import com.fno.back.common.domain.SysMessage;
import com.fno.back.common.service.SysMessageService;
import com.fno.back.workflow.mapper.FlowProcessMapper;
import com.fno.back.common.constant.BillTypeConstants;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import org.flowable.engine.delegate.event.AbstractFlowableEngineEventListener;
import org.flowable.engine.delegate.event.FlowableProcessStartedEvent;
import org.flowable.engine.delegate.event.impl.FlowableEntityEventImpl;
import org.flowable.engine.impl.persistence.entity.ExecutionEntityImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/***
 * @des
 * 全局的流程启动的监听器
 * @author Ly
 * @date 2023/5/31
 */

@Component
public class GlobalProcessStartedListener extends AbstractFlowableEngineEventListener {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FlowProcessMapper flowProcessMapper;
    @Autowired
    private SysMessageService sysMessageService;

    @Override
    protected void processStarted(FlowableProcessStartedEvent event) {
        logger.info("进入流程开始监听器------------------------Start---------------------->");

        String eventName = event.getType().name();

        FlowableEntityEventImpl flowableEntityEvent = (FlowableEntityEventImpl) event;
        ExecutionEntityImpl processInstance = (ExecutionEntityImpl) flowableEntityEvent.getEntity();

        Date startTime = processInstance.getStartTime();
        String processDefinitionKey = processInstance.getProcessDefinitionKey();
        String processInstanceId = processInstance.getProcessInstanceId();
        String processInstanceBusinessKey = processInstance.getProcessInstanceBusinessKey();
        int suspensionState = processInstance.getSuspensionState();


        if(BillTypeConstants.SEND_TASK.equals(processDefinitionKey)){

            String businessKey = processInstance.getProcessInstanceBusinessKey();
            String[] arr = businessKey.split(":");
            String billType = arr[0];
            String tablename = arr[1];
            String businessId = arr[2];
            Long id = Long.parseLong(businessId);

            Map object = flowProcessMapper.selectObjectById(tablename,id);

            String ids = object.get("ext_user_id").toString();
            if(StringUtils.isNotBlank(ids)){
                String idArr[] = ids.split(",");
                for(String uid:idArr){
                    //给协助人发送站内信
                    SysMessage msg = new SysMessage();
                    msg.setTitle("您有【工作任务】需要协助："+object.get("title").toString());
                    msg.setSendTime(new Date());
                    msg.setContent("您有【工作任务】需要协助。"+object.get("title").toString()+"。负责人："+object.get("receive_nick_name").toString()+"。任务发送人："+object.get("send_nick_name").toString());
                    msg.setSendUserId(Long.parseLong(object.get("create_by").toString()));
                    msg.setReceiveUserId(Long.parseLong(uid));
                    msg.setTenantId(SecurityUtils.getTenantId());
                    sysMessageService.insertSysMessage(msg);
                }
            }
        }

        logger.info("流程事件类型->{}", eventName);
        logger.info("流程开始时间->{}", startTime);
        logger.info("流程定义Key->{}", processDefinitionKey);
        logger.info("流程实例ID->{}", processInstanceId);
        logger.info("流程业务key->{}", processInstanceBusinessKey);
        logger.info("流程是否挂起标志->{}", suspensionState);

        logger.info("流程开始监听器------------------------End---------------------->");

    }
}
