package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaWorkPlanItem;

/**
 * 工作计划安排Mapper接口
 * 
 * @author fno
 * @date 2023-08-18
 */
public interface OaWorkPlanItemMapper 
{
    /**
     * 查询工作计划安排
     * 
     * @param id 工作计划安排主键
     * @return 工作计划安排
     */
    public OaWorkPlanItem selectOaWorkPlanItemById(Long id);

    /**
     * 查询工作计划安排列表
     * 
     * @param oaWorkPlanItem 工作计划安排
     * @return 工作计划安排集合
     */
    public List<OaWorkPlanItem> selectOaWorkPlanItemList(OaWorkPlanItem oaWorkPlanItem);

    /**
     * 新增工作计划安排
     * 
     * @param oaWorkPlanItem 工作计划安排
     * @return 结果
     */
    public int insertOaWorkPlanItem(OaWorkPlanItem oaWorkPlanItem);

    /**
     * 修改工作计划安排
     * 
     * @param oaWorkPlanItem 工作计划安排
     * @return 结果
     */
    public int updateOaWorkPlanItem(OaWorkPlanItem oaWorkPlanItem);

    /**
     * 删除工作计划安排
     * 
     * @param id 工作计划安排主键
     * @return 结果
     */
    public int deleteOaWorkPlanItemById(Long id);

    /**
     * 批量删除工作计划安排
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaWorkPlanItemByIds(Long[] ids);
}
