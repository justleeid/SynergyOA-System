package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaHotelApply;

/**
 * 酒店申请Mapper接口
 * 
 * @author fno
 * @date 2023-05-29
 */
public interface OaHotelApplyMapper 
{
    /**
     * 查询酒店申请
     * 
     * @param id 酒店申请主键
     * @return 酒店申请
     */
    public OaHotelApply selectOaHotelApplyById(Long id);

    /**
     * 查询酒店申请列表
     * 
     * @param oaHotelApply 酒店申请
     * @return 酒店申请集合
     */
    public List<OaHotelApply> selectOaHotelApplyList(OaHotelApply oaHotelApply);

    /**
     * 新增酒店申请
     * 
     * @param oaHotelApply 酒店申请
     * @return 结果
     */
    public int insertOaHotelApply(OaHotelApply oaHotelApply);

    /**
     * 修改酒店申请
     * 
     * @param oaHotelApply 酒店申请
     * @return 结果
     */
    public int updateOaHotelApply(OaHotelApply oaHotelApply);

    /**
     * 删除酒店申请
     * 
     * @param id 酒店申请主键
     * @return 结果
     */
    public int deleteOaHotelApplyById(Long id);

    /**
     * 批量删除酒店申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaHotelApplyByIds(Long[] ids);
}
