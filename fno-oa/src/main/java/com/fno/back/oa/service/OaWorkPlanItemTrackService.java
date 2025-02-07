package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaWorkPlanItemTrackMapper;
import com.fno.back.oa.domain.OaWorkPlanItemTrack;
import com.fno.back.oa.service.OaWorkPlanItemTrackService;

/**
 * 进度跟踪Service业务层处理
 *
 */
@Service
public class OaWorkPlanItemTrackService
{
    @Autowired
    private OaWorkPlanItemTrackMapper oaWorkPlanItemTrackMapper;

    /**
     * 查询进度跟踪
     * 
     * @param id 进度跟踪主键
     * @return 进度跟踪
     */
    public OaWorkPlanItemTrack selectOaWorkPlanItemTrackById(Long id)
    {
        return oaWorkPlanItemTrackMapper.selectOaWorkPlanItemTrackById(id);
    }

    /**
     * 查询进度跟踪列表
     * 
     * @param oaWorkPlanItemTrack 进度跟踪
     * @return 进度跟踪
     */
    public List<OaWorkPlanItemTrack> selectOaWorkPlanItemTrackList(OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        return oaWorkPlanItemTrackMapper.selectOaWorkPlanItemTrackList(oaWorkPlanItemTrack);
    }

    /**
     * 新增进度跟踪
     * 
     * @param oaWorkPlanItemTrack 进度跟踪
     * @return 结果
     */
    public int insertOaWorkPlanItemTrack(OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        oaWorkPlanItemTrack.setCreateTime(DateUtils.getNowDate());
        oaWorkPlanItemTrack.setTrackUserId(SecurityUtils.getUserId());
        return oaWorkPlanItemTrackMapper.insertOaWorkPlanItemTrack(oaWorkPlanItemTrack);
    }

    /**
     * 修改进度跟踪
     * 
     * @param oaWorkPlanItemTrack 进度跟踪
     * @return 结果
     */
    public int updateOaWorkPlanItemTrack(OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        return oaWorkPlanItemTrackMapper.updateOaWorkPlanItemTrack(oaWorkPlanItemTrack);
    }

    /**
     * 批量删除进度跟踪
     * 
     * @param ids 需要删除的进度跟踪主键
     * @return 结果
     */
    public int deleteOaWorkPlanItemTrackByIds(Long[] ids)
    {
        return oaWorkPlanItemTrackMapper.deleteOaWorkPlanItemTrackByIds(ids);
    }

    /**
     * 删除进度跟踪信息
     * 
     * @param id 进度跟踪主键
     * @return 结果
     */
    public int deleteOaWorkPlanItemTrackById(Long id)
    {
        return oaWorkPlanItemTrackMapper.deleteOaWorkPlanItemTrackById(id);
    }
}
