package com.fno.back.workflow.mapper;

import com.fno.back.workflow.domain.VTasklist;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 我的待办Mapper接口
 * 
 * @author fno
 * @date 2022-07-16
 */
public interface VTasklistMapper 
{
    /**
     * 查询我的待办
     * 
     * @param taskid 我的待办主键
     * @return 我的待办
     */
    public VTasklist selectVTasklistByTaskid(String taskid);

    /**
     * 查询我的待办列表
     * 
     * @param vTasklist 我的待办
     * @return 我的待办集合
     */
    public List<VTasklist> selectVTasklistList(VTasklist vTasklist);

    /****
     * 查询审批历史
     * @param insId
     * @return
     */
    public List<VTasklist> selectHisByInsId(String insId);


    public List<VTasklist> getIfCanClaim(@Param("taskId") String taskId,@Param("userId") Long userId);

    public List<Map> getAllCandidate(@Param("taskId") String taskId);

    /****
     * 查询待办任务列表
     * @param username
     * @return
     */
    public List<VTasklist> selectTodoTaskList(@Param("userId") String userId,@Param("key") String key,@Param("name") String name);

    /***
     * 查询我的已办任务列表
     * @param username
     * @param key
     * @param name
     * @return
     */
    public List<Map> selectHaveDoneTaskList(@Param("userId") String userId,@Param("key") String key,@Param("name") String name);
}
