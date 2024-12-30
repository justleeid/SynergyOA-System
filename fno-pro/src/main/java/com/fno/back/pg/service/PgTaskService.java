package com.fno.back.pg.service;

import java.util.List;
import java.util.Map;

import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.pg.mapper.PgTaskMapper;
import com.fno.back.pg.domain.PgTask;

/**
 * 任务迭代Service业务层处理
 * 
 * @author fno
 * @date 2023-04-15
 */
@Service
public class PgTaskService
{
    @Autowired
    private PgTaskMapper pgTaskMapper;

    /**
     * 查询任务迭代
     * 
     * @param id 任务迭代主键
     * @return 任务迭代
     */
    public PgTask selectPgTaskById(Long id)
    {
        return pgTaskMapper.selectPgTaskById(id);
    }

    /**
     * 查询任务迭代列表
     * 
     * @param pgTask 任务迭代
     * @return 任务迭代
     */
    public List<PgTask> selectPgTaskList(PgTask pgTask)
    {
        return pgTaskMapper.selectPgTaskList(pgTask);
    }

    /**
     * 新增任务迭代
     * 
     * @param pgTask 任务迭代
     * @return 结果
     */
    public int insertPgTask(PgTask pgTask)
    {
        pgTask.setCreateTime(DateUtils.getNowDate());
        return pgTaskMapper.insertPgTask(pgTask);
    }

    /**
     * 修改任务迭代
     * 
     * @param pgTask 任务迭代
     * @return 结果
     */
    public int updatePgTask(PgTask pgTask)
    {
        pgTask.setUpdateTime(DateUtils.getNowDate());
        return pgTaskMapper.updatePgTask(pgTask);
    }

    /**
     * 批量删除任务迭代
     * 
     * @param ids 需要删除的任务迭代主键
     * @return 结果
     */
    public int deletePgTaskByIds(Long[] ids)
    {
        return pgTaskMapper.deletePgTaskByIds(ids);
    }

    /**
     * 删除任务迭代信息
     * 
     * @param id 任务迭代主键
     * @return 结果
     */
    public int deletePgTaskById(Long id)
    {
        return pgTaskMapper.deletePgTaskById(id);
    }



    public List<Map> getRoleChart(Long id){
        return pgTaskMapper.getRoleChart(id);
    }


    public List<Map> getPostChart(Long id){
        return pgTaskMapper.getPostChart(id);
    }
}
