package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaTicketApply;

/**
 * 车票申请Mapper接口
 *
 */
public interface OaTicketApplyMapper 
{
    /**
     * 查询车票申请
     * 
     * @param id 车票申请主键
     * @return 车票申请
     */
    public OaTicketApply selectOaTicketApplyById(Long id);

    /**
     * 查询车票申请列表
     * 
     * @param oaTicketApply 车票申请
     * @return 车票申请集合
     */
    public List<OaTicketApply> selectOaTicketApplyList(OaTicketApply oaTicketApply);

    /**
     * 新增车票申请
     * 
     * @param oaTicketApply 车票申请
     * @return 结果
     */
    public int insertOaTicketApply(OaTicketApply oaTicketApply);

    /**
     * 修改车票申请
     * 
     * @param oaTicketApply 车票申请
     * @return 结果
     */
    public int updateOaTicketApply(OaTicketApply oaTicketApply);

    /**
     * 删除车票申请
     * 
     * @param id 车票申请主键
     * @return 结果
     */
    public int deleteOaTicketApplyById(Long id);

    /**
     * 批量删除车票申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaTicketApplyByIds(Long[] ids);
}
