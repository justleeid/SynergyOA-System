package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaSendTaskTrackMapper;
import com.fno.back.oa.domain.OaSendTaskTrack;
import com.fno.back.oa.service.OaSendTaskTrackService;

/**
 * 任务跟踪Service业务层处理
 * 
 * @author fno
 * @date 2023-08-20
 */
@Service
public class OaSendTaskTrackService
{
    @Autowired
    private OaSendTaskTrackMapper oaSendTaskTrackMapper;

    /**
     * 查询任务跟踪
     * 
     * @param id 任务跟踪主键
     * @return 任务跟踪
     */
    public OaSendTaskTrack selectOaSendTaskTrackById(Long id)
    {
        return oaSendTaskTrackMapper.selectOaSendTaskTrackById(id);
    }

    /**
     * 查询任务跟踪列表
     * 
     * @param oaSendTaskTrack 任务跟踪
     * @return 任务跟踪
     */
    public List<OaSendTaskTrack> selectOaSendTaskTrackList(OaSendTaskTrack oaSendTaskTrack)
    {
        return oaSendTaskTrackMapper.selectOaSendTaskTrackList(oaSendTaskTrack);
    }

    /**
     * 新增任务跟踪
     * 
     * @param oaSendTaskTrack 任务跟踪
     * @return 结果
     */
    public int insertOaSendTaskTrack(OaSendTaskTrack oaSendTaskTrack)
    {
        oaSendTaskTrack.setCreateTime(DateUtils.getNowDate());
        oaSendTaskTrack.setUserId(SecurityUtils.getUserId());
        return oaSendTaskTrackMapper.insertOaSendTaskTrack(oaSendTaskTrack);
    }

    /**
     * 修改任务跟踪
     * 
     * @param oaSendTaskTrack 任务跟踪
     * @return 结果
     */
    public int updateOaSendTaskTrack(OaSendTaskTrack oaSendTaskTrack)
    {
        return oaSendTaskTrackMapper.updateOaSendTaskTrack(oaSendTaskTrack);
    }

    /**
     * 批量删除任务跟踪
     * 
     * @param ids 需要删除的任务跟踪主键
     * @return 结果
     */
    public int deleteOaSendTaskTrackByIds(Long[] ids)
    {
        return oaSendTaskTrackMapper.deleteOaSendTaskTrackByIds(ids);
    }

    /**
     * 删除任务跟踪信息
     * 
     * @param id 任务跟踪主键
     * @return 结果
     */
    public int deleteOaSendTaskTrackById(Long id)
    {
        return oaSendTaskTrackMapper.deleteOaSendTaskTrackById(id);
    }
}
