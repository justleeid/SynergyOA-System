package com.fno.back.workflow.listener.taskListener;

import com.alibaba.fastjson2.JSONObject;
import com.fno.back.workflow.mapper.TaskListenerMapper;
import com.fno.back.common.constant.CommonConstants;
import com.fno.common.utils.spring.SpringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/***
 * @des
 */
@Component
public class SetAssigneeByReceiverIdListener implements TaskListener {

    //@Autowired
    //private HistoryService historyService;
    //@Autowired
    //private TaskService taskService;

    @Override
    public void notify(DelegateTask delegateTask) {
        Object orderinfo = delegateTask.getVariable(CommonConstants.APPLY_VARS_ORDERINFO);
        if(orderinfo!=null) {
            String orderinfoStr = orderinfo.toString();
            JSONObject order = JSONObject.parseObject(orderinfoStr);
            //查询到提交人
            Long userId = Long.parseLong(order.getString(CommonConstants.APPLY_VARS_USERID));
            delegateTask.getProcessInstanceId();
            HistoricProcessInstance historicProcessInstance = SpringUtils.getBean(HistoryService.class).createHistoricProcessInstanceQuery().processInstanceId(delegateTask.getProcessInstanceId()).singleResult();
            String businessKey = historicProcessInstance.getBusinessKey();
            String[] arr = businessKey.split(":");
            String billType = arr[0];
            String tablename = arr[1];
            String businessId = arr[2];
            //查询单据的任务接收人
            String assignee = SpringUtils.getBean(TaskListenerMapper.class).getReceiverId(tablename,Long.parseLong(businessId));
            //设置为审批人
            delegateTask.setAssignee(assignee);
            //因为，delegateTask.setAssignee(assignee)，只会更新runtimeService中的，ru_task表中的数据，hi历史记录表的数据，不会修改。
            //该监听器中直接修改表，此时还没有历史任务的记录，需要配置流程图的时候，在complete的事件中更新下办理人。
            //此处地方处理的不完美，需要考虑其他方案。
            SpringUtils.getBean(TaskListenerMapper.class).updateHiTaskAssignee(delegateTask.getId(),assignee);
        }

    }
}
