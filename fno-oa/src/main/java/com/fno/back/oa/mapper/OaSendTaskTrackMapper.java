package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaSendTaskTrack;

/**
 * 任务跟踪Mapper接口
 * 
 * @author fno
 * @date 2023-08-20
 */
public interface OaSendTaskTrackMapper 
{
    /**
     * 查询任务跟踪
     * 
     * @param id 任务跟踪主键
     * @return 任务跟踪
     */
    public OaSendTaskTrack selectOaSendTaskTrackById(Long id);

    /**
     * 查询任务跟踪列表
     * 
     * @param oaSendTaskTrack 任务跟踪
     * @return 任务跟踪集合
     */
    public List<OaSendTaskTrack> selectOaSendTaskTrackList(OaSendTaskTrack oaSendTaskTrack);

    /**
     * 新增任务跟踪
     * 
     * @param oaSendTaskTrack 任务跟踪
     * @return 结果
     */
    public int insertOaSendTaskTrack(OaSendTaskTrack oaSendTaskTrack);

    /**
     * 修改任务跟踪
     * 
     * @param oaSendTaskTrack 任务跟踪
     * @return 结果
     */
    public int updateOaSendTaskTrack(OaSendTaskTrack oaSendTaskTrack);

    /**
     * 删除任务跟踪
     * 
     * @param id 任务跟踪主键
     * @return 结果
     */
    public int deleteOaSendTaskTrackById(Long id);

    /**
     * 批量删除任务跟踪
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaSendTaskTrackByIds(Long[] ids);
}
