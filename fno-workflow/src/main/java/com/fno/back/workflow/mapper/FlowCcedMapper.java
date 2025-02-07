package com.fno.back.workflow.mapper;

import com.fno.back.workflow.domain.FlowCced;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 抄送我的Mapper接口
 *
 */
public interface FlowCcedMapper
{
    /**
     * 查询抄送我的
     * 
     * @param id 抄送我的主键
     * @return 抄送我的
     */
    public FlowCced selectOaFlowCcedById(Long id);

    /**
     * 查询抄送我的列表
     * 
     * @param oaFlowCced 抄送我的
     * @return 抄送我的集合
     */
    public List<FlowCced> selectOaFlowCcedList(FlowCced oaFlowCced);


    /**
     * 计算抄送数量
     */
    public int countCced(FlowCced oaFlowCced);

    /**
     * 新增抄送我的
     * 
     * @param oaFlowCced 抄送我的
     * @return 结果
     */
    public int insertOaFlowCced(FlowCced oaFlowCced);


    public int insertBatch(List<FlowCced> list);

    /**
     * 修改抄送我的
     * 
     * @param oaFlowCced 抄送我的
     * @return 结果
     */
    public int updateOaFlowCced(FlowCced oaFlowCced);

    /**
     * 删除抄送我的
     * 
     * @param id 抄送我的主键
     * @return 结果
     */
    public int deleteOaFlowCcedById(Long id);

    /**
     * 批量删除抄送我的
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaFlowCcedByIds(Long[] ids);


    public int deleteCcedByBillTypeAndBusinessId(@Param("billType") String billType,@Param("businessId") Long businessId);

}
