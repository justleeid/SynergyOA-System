package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaSendTask;

/**
 * 任务分配Mapper接口
 * 
 * @author fno
 * @date 2023-08-19
 */
public interface OaSendTaskMapper 
{
    /**
     * 查询任务分配
     * 
     * @param id 任务分配主键
     * @return 任务分配
     */
    public OaSendTask selectOaSendTaskById(Long id);

    /**
     * 查询任务分配列表
     * 
     * @param oaSendTask 任务分配
     * @return 任务分配集合
     */
    public List<OaSendTask> selectOaSendTaskList(OaSendTask oaSendTask);

    /**
     * 新增任务分配
     * 
     * @param oaSendTask 任务分配
     * @return 结果
     */
    public int insertOaSendTask(OaSendTask oaSendTask);

    /**
     * 修改任务分配
     * 
     * @param oaSendTask 任务分配
     * @return 结果
     */
    public int updateOaSendTask(OaSendTask oaSendTask);

    /**
     * 删除任务分配
     * 
     * @param id 任务分配主键
     * @return 结果
     */
    public int deleteOaSendTaskById(Long id);

    /**
     * 批量删除任务分配
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaSendTaskByIds(Long[] ids);
}
