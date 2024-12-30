package com.fno.back.pg.mapper;

import java.util.List;
import java.util.Map;

import com.fno.back.pg.domain.PgTask;

/**
 * 任务迭代Mapper接口
 * 
 * @author fno
 * @date 2023-04-15
 */
public interface PgTaskMapper 
{
    /**
     * 查询任务迭代
     * 
     * @param id 任务迭代主键
     * @return 任务迭代
     */
    public PgTask selectPgTaskById(Long id);

    /**
     * 查询任务迭代列表
     * 
     * @param pgTask 任务迭代
     * @return 任务迭代集合
     */
    public List<PgTask> selectPgTaskList(PgTask pgTask);

    /**
     * 新增任务迭代
     * 
     * @param pgTask 任务迭代
     * @return 结果
     */
    public int insertPgTask(PgTask pgTask);

    /**
     * 修改任务迭代
     * 
     * @param pgTask 任务迭代
     * @return 结果
     */
    public int updatePgTask(PgTask pgTask);

    /**
     * 删除任务迭代
     * 
     * @param id 任务迭代主键
     * @return 结果
     */
    public int deletePgTaskById(Long id);

    /**
     * 批量删除任务迭代
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePgTaskByIds(Long[] ids);



    public List<Map> getRoleChart(Long id);


    public List<Map> getPostChart(Long id);
}
