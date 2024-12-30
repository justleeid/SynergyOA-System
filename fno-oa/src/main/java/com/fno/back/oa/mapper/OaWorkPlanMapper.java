package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaWorkPlan;
import com.fno.back.oa.domain.OaWorkPlanItem;

/**
 * 工作计划Mapper接口
 * 
 * @author fno
 * @date 2023-08-13
 */
public interface OaWorkPlanMapper 
{
    /**
     * 查询工作计划
     * 
     * @param id 工作计划主键
     * @return 工作计划
     */
    public OaWorkPlan selectOaWorkPlanById(Long id);

    /**
     * 查询工作计划列表
     * 
     * @param oaWorkPlan 工作计划
     * @return 工作计划集合
     */
    public List<OaWorkPlan> selectOaWorkPlanList(OaWorkPlan oaWorkPlan);

    /**
     * 新增工作计划
     * 
     * @param oaWorkPlan 工作计划
     * @return 结果
     */
    public int insertOaWorkPlan(OaWorkPlan oaWorkPlan);

    /**
     * 修改工作计划
     * 
     * @param oaWorkPlan 工作计划
     * @return 结果
     */
    public int updateOaWorkPlan(OaWorkPlan oaWorkPlan);

    /**
     * 删除工作计划
     * 
     * @param id 工作计划主键
     * @return 结果
     */
    public int deleteOaWorkPlanById(Long id);

    /**
     * 批量删除工作计划
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaWorkPlanByIds(Long[] ids);

    /**
     * 批量删除工作计划安排
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaWorkPlanItemByPlanIds(Long[] ids);
    
    /**
     * 批量新增工作计划安排
     * 
     * @param oaWorkPlanItemList 工作计划安排列表
     * @return 结果
     */
    public int batchOaWorkPlanItem(List<OaWorkPlanItem> oaWorkPlanItemList);
    

    /**
     * 通过工作计划主键删除工作计划安排信息
     * 
     * @param id 工作计划ID
     * @return 结果
     */
    public int deleteOaWorkPlanItemByPlanId(Long id);
}
