package com.fno.back.workflow.mapper;

import com.fno.back.workflow.domain.ActHiTaskinst;

import java.util.List;
import java.util.Map;

/**
 * 历史任务Mapper接口
 * 
 * @author fno
 * @date 2023-05-14
 */
public interface ActHiTaskinstMapper 
{
    /**
     * 查询历史任务
     * 
     * @param id 历史任务主键
     * @return 历史任务
     */
    public ActHiTaskinst selectActHiTaskinstById(String id);

    /**
     * 查询历史任务列表
     * 
     * @param actHiTaskinst 历史任务
     * @return 历史任务集合
     */
    public List<ActHiTaskinst> selectActHiTaskinstList(ActHiTaskinst actHiTaskinst);

    /**
     * 新增历史任务
     * 
     * @param actHiTaskinst 历史任务
     * @return 结果
     */
    public int insertActHiTaskinst(ActHiTaskinst actHiTaskinst);

    /**
     * 修改历史任务
     * 
     * @param actHiTaskinst 历史任务
     * @return 结果
     */
    public int updateActHiTaskinst(ActHiTaskinst actHiTaskinst);

    /**
     * 删除历史任务
     * 
     * @param id 历史任务主键
     * @return 结果
     */
    public int deleteActHiTaskinstById(String id);

    /**
     * 批量删除历史任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActHiTaskinstByIds(String[] ids);




    public List<Map> getTaskCishuReport(ActHiTaskinst actHiTaskinst);
}
