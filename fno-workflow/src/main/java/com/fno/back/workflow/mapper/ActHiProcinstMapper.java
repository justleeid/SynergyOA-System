package com.fno.back.workflow.mapper;

import com.fno.back.workflow.domain.ActHiProcinst;

import java.util.List;

/**
 * 流程实例Mapper接口
 *
 */
public interface ActHiProcinstMapper 
{
    /**
     * 查询流程实例
     * 
     * @param id 流程实例主键
     * @return 流程实例
     */
    public ActHiProcinst selectActHiProcinstById(String id);

    /**
     * 查询流程实例列表
     * 
     * @param actHiProcinst 流程实例
     * @return 流程实例集合
     */
    public List<ActHiProcinst> selectActHiProcinstList(ActHiProcinst actHiProcinst);

    /**
     * 新增流程实例
     * 
     * @param actHiProcinst 流程实例
     * @return 结果
     */
    public int insertActHiProcinst(ActHiProcinst actHiProcinst);

    /**
     * 修改流程实例
     * 
     * @param actHiProcinst 流程实例
     * @return 结果
     */
    public int updateActHiProcinst(ActHiProcinst actHiProcinst);

    /**
     * 删除流程实例
     * 
     * @param id 流程实例主键
     * @return 结果
     */
    public int deleteActHiProcinstById(String id);

    /**
     * 批量删除流程实例
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActHiProcinstByIds(String[] ids);
}
