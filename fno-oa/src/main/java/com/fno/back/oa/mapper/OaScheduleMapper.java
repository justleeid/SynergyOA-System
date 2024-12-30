package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaSchedule;

/**
 * 日程管理Mapper接口
 * 
 * @author fno
 * @date 2023-09-25
 */
public interface OaScheduleMapper
{
    /**
     * 查询日程管理
     * 
     * @param id 日程管理主键
     * @return 日程管理
     */
    public OaSchedule selectOaScheduleById(Long id);

    /**
     * 查询日程管理列表
     * 
     * @param oaSchedule 日程管理
     * @return 日程管理集合
     */
    public List<OaSchedule> selectOaScheduleList(OaSchedule oaSchedule);

    /**
     * 新增日程管理
     * 
     * @param oaSchedule 日程管理
     * @return 结果
     */
    public int insertOaSchedule(OaSchedule oaSchedule);

    /**
     * 修改日程管理
     * 
     * @param oaSchedule 日程管理
     * @return 结果
     */
    public int updateOaSchedule(OaSchedule oaSchedule);

    /**
     * 删除日程管理
     * 
     * @param id 日程管理主键
     * @return 结果
     */
    public int deleteOaScheduleById(Long id);

    /**
     * 批量删除日程管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaScheduleByIds(Long[] ids);
}
