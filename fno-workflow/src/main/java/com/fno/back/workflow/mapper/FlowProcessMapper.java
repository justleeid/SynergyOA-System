package com.fno.back.workflow.mapper;

import com.fno.back.workflow.domain.ProcessTableUpdateDomain;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 流程实例
 * 
 * @author fno
 * @date 2023-05-31
 */
public interface FlowProcessMapper
{
    public void updateBusinessBillStatus(ProcessTableUpdateDomain processTableUpdateDomain);

    /****
     * 重置流程申请字段
     */
    public void resetBusinessFlowApply(@Param("tableName") String tableName);

    public Map selectObjectById(@Param("tableName") String tableName, @Param("id") Long id);
}
