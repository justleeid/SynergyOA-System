package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaScheduleMapper;
import com.fno.back.oa.domain.OaSchedule;

/**
 * 日程管理Service业务层处理
 * 
 * @author fno
 * @date 2023-09-25
 */
@Service
public class OaScheduleService
{
    @Autowired
    private OaScheduleMapper oaScheduleMapper;

    /**
     * 查询日程管理
     * 
     * @param id 日程管理主键
     * @return 日程管理
     */
    public OaSchedule selectOaScheduleById(Long id)
    {
        return oaScheduleMapper.selectOaScheduleById(id);
    }

    /**
     * 查询日程管理列表
     * 
     * @param oaSchedule 日程管理
     * @return 日程管理
     */
    public List<OaSchedule> selectOaScheduleList(OaSchedule oaSchedule)
    {
        oaSchedule.setUserId(SecurityUtils.getUserId());
        return oaScheduleMapper.selectOaScheduleList(oaSchedule);
    }

    /**
     * 新增日程管理
     * 
     * @param oaSchedule 日程管理
     * @return 结果
     */
    public int insertOaSchedule(OaSchedule oaSchedule)
    {
        oaSchedule.setCreateTime(DateUtils.getNowDate());
        oaSchedule.setUserId(SecurityUtils.getUserId());
        oaSchedule.setCreateBy(SecurityUtils.getUserId());
        return oaScheduleMapper.insertOaSchedule(oaSchedule);
    }

    /**
     * 修改日程管理
     * 
     * @param oaSchedule 日程管理
     * @return 结果
     */
    public int updateOaSchedule(OaSchedule oaSchedule)
    {
        oaSchedule.setUpdateTime(DateUtils.getNowDate());
        return oaScheduleMapper.updateOaSchedule(oaSchedule);
    }

    /**
     * 批量删除日程管理
     * 
     * @param ids 需要删除的日程管理主键
     * @return 结果
     */
    public int deleteOaScheduleByIds(Long[] ids)
    {
        return oaScheduleMapper.deleteOaScheduleByIds(ids);
    }

    /**
     * 删除日程管理信息
     * 
     * @param id 日程管理主键
     * @return 结果
     */
    public int deleteOaScheduleById(Long id)
    {
        return oaScheduleMapper.deleteOaScheduleById(id);
    }
}
