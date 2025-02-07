package com.fno.back.oa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaWorkPlanItemMapper;
import com.fno.back.oa.domain.OaWorkPlanItem;
import com.fno.back.oa.service.OaWorkPlanItemService;

/**
 * 工作计划安排Service业务层处理
 *
 */
@Service
public class OaWorkPlanItemService
{
    @Autowired
    private OaWorkPlanItemMapper oaWorkPlanItemMapper;

    /**
     * 查询工作计划安排
     * 
     * @param id 工作计划安排主键
     * @return 工作计划安排
     */
    public OaWorkPlanItem selectOaWorkPlanItemById(Long id)
    {
        return oaWorkPlanItemMapper.selectOaWorkPlanItemById(id);
    }

    /**
     * 查询工作计划安排列表
     * 
     * @param oaWorkPlanItem 工作计划安排
     * @return 工作计划安排
     */
    public List<OaWorkPlanItem> selectOaWorkPlanItemList(OaWorkPlanItem oaWorkPlanItem)
    {
        return oaWorkPlanItemMapper.selectOaWorkPlanItemList(oaWorkPlanItem);
    }

    /**
     * 新增工作计划安排
     * 
     * @param oaWorkPlanItem 工作计划安排
     * @return 结果
     */
    public int insertOaWorkPlanItem(OaWorkPlanItem oaWorkPlanItem)
    {
        return oaWorkPlanItemMapper.insertOaWorkPlanItem(oaWorkPlanItem);
    }

    /**
     * 修改工作计划安排
     * 
     * @param oaWorkPlanItem 工作计划安排
     * @return 结果
     */
    public int updateOaWorkPlanItem(OaWorkPlanItem oaWorkPlanItem)
    {
        return oaWorkPlanItemMapper.updateOaWorkPlanItem(oaWorkPlanItem);
    }

    /**
     * 批量删除工作计划安排
     * 
     * @param ids 需要删除的工作计划安排主键
     * @return 结果
     */
    public int deleteOaWorkPlanItemByIds(Long[] ids)
    {
        return oaWorkPlanItemMapper.deleteOaWorkPlanItemByIds(ids);
    }

    /**
     * 删除工作计划安排信息
     * 
     * @param id 工作计划安排主键
     * @return 结果
     */
    public int deleteOaWorkPlanItemById(Long id)
    {
        return oaWorkPlanItemMapper.deleteOaWorkPlanItemById(id);
    }
}
