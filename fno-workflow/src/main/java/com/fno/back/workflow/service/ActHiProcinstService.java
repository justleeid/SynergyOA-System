package com.fno.back.workflow.service;

import com.fno.back.workflow.domain.ActHiProcinst;
import com.fno.back.workflow.mapper.ActHiProcinstMapper;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程实例Service业务层处理
 * 
 * @author fno
 * @date 2023-08-08
 */
@Service
public class ActHiProcinstService
{
    @Autowired
    private ActHiProcinstMapper actHiProcinstMapper;

    /**
     * 查询流程实例
     * 
     * @param id 流程实例主键
     * @return 流程实例
     */
    public ActHiProcinst selectActHiProcinstById(String id)
    {
        return actHiProcinstMapper.selectActHiProcinstById(id);
    }

    /**
     * 查询流程实例列表
     * 
     * @param actHiProcinst 流程实例
     * @return 流程实例
     */
    public List<ActHiProcinst> selectActHiProcinstList(ActHiProcinst actHiProcinst)
    {
        actHiProcinst.setTenantId(SecurityUtils.getTenantId());
        return actHiProcinstMapper.selectActHiProcinstList(actHiProcinst);
    }

    /**
     * 新增流程实例
     * 
     * @param actHiProcinst 流程实例
     * @return 结果
     */
    public int insertActHiProcinst(ActHiProcinst actHiProcinst)
    {
        return actHiProcinstMapper.insertActHiProcinst(actHiProcinst);
    }

    /**
     * 修改流程实例
     * 
     * @param actHiProcinst 流程实例
     * @return 结果
     */
    public int updateActHiProcinst(ActHiProcinst actHiProcinst)
    {
        return actHiProcinstMapper.updateActHiProcinst(actHiProcinst);
    }

    /**
     * 批量删除流程实例
     * 
     * @param ids 需要删除的流程实例主键
     * @return 结果
     */
    public int deleteActHiProcinstByIds(String[] ids)
    {
        return actHiProcinstMapper.deleteActHiProcinstByIds(ids);
    }

    /**
     * 删除流程实例信息
     * 
     * @param id 流程实例主键
     * @return 结果
     */
    public int deleteActHiProcinstById(String id)
    {
        return actHiProcinstMapper.deleteActHiProcinstById(id);
    }
}
