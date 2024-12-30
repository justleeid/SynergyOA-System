package com.fno.back.cam.service;

import java.util.List;

import com.fno.back.cam.domain.CamMessageNotice;
import com.fno.back.cam.mapper.CamMessageNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 视频监控提醒Service业务层处理
 * 
 * @author fno
 * @date 2023-04-08
 */
@Service
public class CamMessageNoticeService
{
    @Autowired
    private CamMessageNoticeMapper camMessageNoticeMapper;

    /**
     * 查询视频监控提醒
     * 
     * @param id 视频监控提醒主键
     * @return 视频监控提醒
     */
    public CamMessageNotice selectCamMessageNoticeById(Long id)
    {
        return camMessageNoticeMapper.selectCamMessageNoticeById(id);
    }

    /**
     * 查询视频监控提醒列表
     * 
     * @param camMessageNotice 视频监控提醒
     * @return 视频监控提醒
     */
    public List<CamMessageNotice> selectCamMessageNoticeList(CamMessageNotice camMessageNotice)
    {
        return camMessageNoticeMapper.selectCamMessageNoticeList(camMessageNotice);
    }

    /**
     * 新增视频监控提醒
     * 
     * @param camMessageNotice 视频监控提醒
     * @return 结果
     */
    public int insertCamMessageNotice(CamMessageNotice camMessageNotice)
    {
        return camMessageNoticeMapper.insertCamMessageNotice(camMessageNotice);
    }

    /**
     * 修改视频监控提醒
     * 
     * @param camMessageNotice 视频监控提醒
     * @return 结果
     */
    public int updateCamMessageNotice(CamMessageNotice camMessageNotice)
    {
        return camMessageNoticeMapper.updateCamMessageNotice(camMessageNotice);
    }

    /**
     * 批量删除视频监控提醒
     * 
     * @param ids 需要删除的视频监控提醒主键
     * @return 结果
     */
    public int deleteCamMessageNoticeByIds(Long[] ids)
    {
        return camMessageNoticeMapper.deleteCamMessageNoticeByIds(ids);
    }

    /**
     * 删除视频监控提醒信息
     * 
     * @param id 视频监控提醒主键
     * @return 结果
     */
    public int deleteCamMessageNoticeById(Long id)
    {
        return camMessageNoticeMapper.deleteCamMessageNoticeById(id);
    }
}
