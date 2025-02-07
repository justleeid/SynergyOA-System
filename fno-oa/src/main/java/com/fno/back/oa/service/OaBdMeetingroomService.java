package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaBdMeetingroomMapper;
import com.fno.back.oa.domain.OaBdMeetingroom;

/**
 * 会议室管理Service业务层处理
 *
 */
@Service
public class OaBdMeetingroomService
{
    @Autowired
    private OaBdMeetingroomMapper oaBdMeetingroomMapper;

    /**
     * 查询会议室管理
     * 
     * @param id 会议室管理主键
     * @return 会议室管理
     */
    public OaBdMeetingroom selectOaBdMeetingroomById(Long id)
    {
        return oaBdMeetingroomMapper.selectOaBdMeetingroomById(id);
    }

    /**
     * 查询会议室管理列表
     * 
     * @param oaBdMeetingroom 会议室管理
     * @return 会议室管理
     */
    public List<OaBdMeetingroom> selectOaBdMeetingroomList(OaBdMeetingroom oaBdMeetingroom)
    {
        //租户
        oaBdMeetingroom.setTenantId(SecurityUtils.getTenantId());
        return oaBdMeetingroomMapper.selectOaBdMeetingroomList(oaBdMeetingroom);
    }

    /**
     * 新增会议室管理
     * 
     * @param oaBdMeetingroom 会议室管理
     * @return 结果
     */
    public int insertOaBdMeetingroom(OaBdMeetingroom oaBdMeetingroom)
    {
        oaBdMeetingroom.setCreateTime(DateUtils.getNowDate());
        //租户
        oaBdMeetingroom.setTenantId(SecurityUtils.getTenantId());
        return oaBdMeetingroomMapper.insertOaBdMeetingroom(oaBdMeetingroom);
    }

    /**
     * 修改会议室管理
     * 
     * @param oaBdMeetingroom 会议室管理
     * @return 结果
     */
    public int updateOaBdMeetingroom(OaBdMeetingroom oaBdMeetingroom)
    {
        oaBdMeetingroom.setUpdateTime(DateUtils.getNowDate());
        return oaBdMeetingroomMapper.updateOaBdMeetingroom(oaBdMeetingroom);
    }

    /**
     * 批量删除会议室管理
     * 
     * @param ids 需要删除的会议室管理主键
     * @return 结果
     */
    public int deleteOaBdMeetingroomByIds(Long[] ids)
    {
        return oaBdMeetingroomMapper.deleteOaBdMeetingroomByIds(ids);
    }

    /**
     * 删除会议室管理信息
     * 
     * @param id 会议室管理主键
     * @return 结果
     */
    public int deleteOaBdMeetingroomById(Long id)
    {
        return oaBdMeetingroomMapper.deleteOaBdMeetingroomById(id);
    }
}
