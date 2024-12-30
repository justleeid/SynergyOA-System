package com.fno.back.workflow.listener.taskListener;

import com.alibaba.fastjson2.JSONObject;
import com.fno.back.workflow.mapper.TaskListenerMapper;
import com.fno.back.common.constant.CommonConstants;
import com.fno.common.core.domain.entity.SysDept;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.utils.spring.SpringUtils;
import com.fno.system.mapper.SysDeptMapper;
import com.fno.system.mapper.SysUserMapper;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.stereotype.Component;

/***
 * @des
 * @author Ly
 * @date 2023/8/7
 */
@Component
public class SetAssigneeByApplyUserDeptPostListener implements TaskListener {

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
            SysUser user = SpringUtils.getBean(SysUserMapper.class).selectUserById(userId);
            Long deptid = user.getDeptId();
            //查询提交人所属的部门
            SysDept dept = SpringUtils.getBean(SysDeptMapper.class).selectDeptById(deptid);
            //查询上级部门拥有se岗位的用户
            Long assigneeUserId = SpringUtils.getBean(TaskListenerMapper.class).getUserIdByDeptPostCode(dept.getParentId(),"se");
            //设置为审批人
            delegateTask.setAssignee(assigneeUserId.toString());
            //因为，delegateTask.setAssignee(assignee)，只会更新runtimeService中的，ru_task表中的数据，hi历史记录表的数据，不会修改。
            //该监听器中直接修改表，此时还没有历史任务的记录，需要配置流程图的时候，在complete的事件中更新下办理人。
            //此处地方处理的不完美，需要考虑其他方案。
            SpringUtils.getBean(TaskListenerMapper.class).updateHiTaskAssignee(delegateTask.getId(),assigneeUserId.toString());
        }

    }
}
