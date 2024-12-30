package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaCarApply;

/**
 * 车辆申请Mapper接口
 * 
 * @author fno
 * @date 2023-06-05
 */
public interface OaCarApplyMapper 
{
    /**
     * 查询车辆申请
     * 
     * @param id 车辆申请主键
     * @return 车辆申请
     */
    public OaCarApply selectOaCarApplyById(Long id);

    /**
     * 查询车辆申请列表
     * 
     * @param oaCarApply 车辆申请
     * @return 车辆申请集合
     */
    public List<OaCarApply> selectOaCarApplyList(OaCarApply oaCarApply);

    /**
     * 新增车辆申请
     * 
     * @param oaCarApply 车辆申请
     * @return 结果
     */
    public int insertOaCarApply(OaCarApply oaCarApply);

    /**
     * 修改车辆申请
     * 
     * @param oaCarApply 车辆申请
     * @return 结果
     */
    public int updateOaCarApply(OaCarApply oaCarApply);

    /**
     * 删除车辆申请
     * 
     * @param id 车辆申请主键
     * @return 结果
     */
    public int deleteOaCarApplyById(Long id);

    /**
     * 批量删除车辆申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaCarApplyByIds(Long[] ids);
}
