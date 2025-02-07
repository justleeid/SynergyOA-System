package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaBdMeetingroom;

/**
 * 会议室管理Mapper接口
 *
 */
public interface OaBdMeetingroomMapper 
{
    /**
     * 查询会议室管理
     * 
     * @param id 会议室管理主键
     * @return 会议室管理
     */
    public OaBdMeetingroom selectOaBdMeetingroomById(Long id);

    /**
     * 查询会议室管理列表
     * 
     * @param oaBdMeetingroom 会议室管理
     * @return 会议室管理集合
     */
    public List<OaBdMeetingroom> selectOaBdMeetingroomList(OaBdMeetingroom oaBdMeetingroom);

    /**
     * 新增会议室管理
     * 
     * @param oaBdMeetingroom 会议室管理
     * @return 结果
     */
    public int insertOaBdMeetingroom(OaBdMeetingroom oaBdMeetingroom);

    /**
     * 修改会议室管理
     * 
     * @param oaBdMeetingroom 会议室管理
     * @return 结果
     */
    public int updateOaBdMeetingroom(OaBdMeetingroom oaBdMeetingroom);

    /**
     * 删除会议室管理
     * 
     * @param id 会议室管理主键
     * @return 结果
     */
    public int deleteOaBdMeetingroomById(Long id);

    /**
     * 批量删除会议室管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaBdMeetingroomByIds(Long[] ids);
}
