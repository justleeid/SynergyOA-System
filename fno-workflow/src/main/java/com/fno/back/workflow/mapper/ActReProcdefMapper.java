package com.fno.back.workflow.mapper;

import com.fno.back.workflow.domain.ActReProcdef;

import java.util.List;

/**
 * 流程定义Mapper接口
 * 
 * @author fno
 * @date 2022-07-09
 */
public interface ActReProcdefMapper
{
    /**
     * 查询流程
     * 
     * @return 流程定义
     */
    public List<ActReProcdef> selectAllFlowList();

    public List<ActReProcdef> selectAllProcDefList(ActReProcdef actReProcdef);

}
