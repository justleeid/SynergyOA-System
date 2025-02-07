package com.fno.back.workflow.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 接口实现类
 *
 */
public interface TaskListenerMapper {


    public Long getUserIdByDeptRoleKey(@Param("deptId") Long deptId,@Param("roleKey") String roleKey);

    public Long getUserIdByDeptPostCode(@Param("deptId") Long deptId,@Param("postCode") String postCode);

    public void updateHiTaskAssignee(@Param("taskId") String taskId,@Param("assignee") String assignee);


    public Map selectHisTask(@Param("taskId") String taskId);

    public String getReceiverId(@Param("tableName") String tableName , @Param("id") Long id);
}