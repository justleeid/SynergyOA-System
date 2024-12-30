package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaMeetingroomApply;

/**
 * 会议室申请Mapper接口
 * 
 * @author fno
 * @date 2023-05-29
 */
public interface OaMeetingroomApplyMapper 
{
    /**
     * 查询会议室申请
     * 
     * @param id 会议室申请主键
     * @return 会议室申请
     */
    public OaMeetingroomApply selectOaMeetingroomApplyById(Long id);

    /**
     * 查询会议室申请列表
     * 
     * @param oaMeetingroomApply 会议室申请
     * @return 会议室申请集合
     */
    public List<OaMeetingroomApply> selectOaMeetingroomApplyList(OaMeetingroomApply oaMeetingroomApply);

    /**
     * 新增会议室申请
     * 
     * @param oaMeetingroomApply 会议室申请
     * @return 结果
     */
    public int insertOaMeetingroomApply(OaMeetingroomApply oaMeetingroomApply);

    /**
     * 修改会议室申请
     * 
     * @param oaMeetingroomApply 会议室申请
     * @return 结果
     */
    public int updateOaMeetingroomApply(OaMeetingroomApply oaMeetingroomApply);

    /**
     * 删除会议室申请
     * 
     * @param id 会议室申请主键
     * @return 结果
     */
    public int deleteOaMeetingroomApplyById(Long id);

    /**
     * 批量删除会议室申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaMeetingroomApplyByIds(Long[] ids);
}
