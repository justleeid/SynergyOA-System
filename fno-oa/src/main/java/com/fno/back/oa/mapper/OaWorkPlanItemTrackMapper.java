package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaWorkPlanItemTrack;

/**
 * 进度跟踪Mapper接口
 * 
 * @author fno
 * @date 2023-08-18
 */
public interface OaWorkPlanItemTrackMapper 
{
    /**
     * 查询进度跟踪
     * 
     * @param id 进度跟踪主键
     * @return 进度跟踪
     */
    public OaWorkPlanItemTrack selectOaWorkPlanItemTrackById(Long id);

    /**
     * 查询进度跟踪列表
     * 
     * @param oaWorkPlanItemTrack 进度跟踪
     * @return 进度跟踪集合
     */
    public List<OaWorkPlanItemTrack> selectOaWorkPlanItemTrackList(OaWorkPlanItemTrack oaWorkPlanItemTrack);

    /**
     * 新增进度跟踪
     * 
     * @param oaWorkPlanItemTrack 进度跟踪
     * @return 结果
     */
    public int insertOaWorkPlanItemTrack(OaWorkPlanItemTrack oaWorkPlanItemTrack);

    /**
     * 修改进度跟踪
     * 
     * @param oaWorkPlanItemTrack 进度跟踪
     * @return 结果
     */
    public int updateOaWorkPlanItemTrack(OaWorkPlanItemTrack oaWorkPlanItemTrack);

    /**
     * 删除进度跟踪
     * 
     * @param id 进度跟踪主键
     * @return 结果
     */
    public int deleteOaWorkPlanItemTrackById(Long id);

    /**
     * 批量删除进度跟踪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaWorkPlanItemTrackByIds(Long[] ids);
}
