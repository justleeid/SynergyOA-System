package com.fno.back.cam.mapper;

import com.fno.back.cam.domain.CamMessageNotice;

import java.util.List;

/**
 * 视频监控提醒Mapper接口
 * 
 * @author fno
 * @date 2023-04-08
 */
public interface CamMessageNoticeMapper 
{
    /**
     * 查询视频监控提醒
     * 
     * @param id 视频监控提醒主键
     * @return 视频监控提醒
     */
    public CamMessageNotice selectCamMessageNoticeById(Long id);

    /**
     * 查询视频监控提醒列表
     * 
     * @param camMessageNotice 视频监控提醒
     * @return 视频监控提醒集合
     */
    public List<CamMessageNotice> selectCamMessageNoticeList(CamMessageNotice camMessageNotice);

    /**
     * 新增视频监控提醒
     * 
     * @param camMessageNotice 视频监控提醒
     * @return 结果
     */
    public int insertCamMessageNotice(CamMessageNotice camMessageNotice);

    /**
     * 修改视频监控提醒
     * 
     * @param camMessageNotice 视频监控提醒
     * @return 结果
     */
    public int updateCamMessageNotice(CamMessageNotice camMessageNotice);

    /**
     * 删除视频监控提醒
     * 
     * @param id 视频监控提醒主键
     * @return 结果
     */
    public int deleteCamMessageNoticeById(Long id);

    /**
     * 批量删除视频监控提醒
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCamMessageNoticeByIds(Long[] ids);
}
