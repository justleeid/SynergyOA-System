package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaReimburseApply;
import com.fno.back.oa.domain.OaReimburseFeeitem;

/**
 * 报销申请Mapper接口
 *
 */
public interface OaReimburseApplyMapper 
{
    /**
     * 查询报销申请
     * 
     * @param id 报销申请主键
     * @return 报销申请
     */
    public OaReimburseApply selectOaReimburseApplyById(Long id);

    /**
     * 查询报销申请列表
     * 
     * @param oaReimburseApply 报销申请
     * @return 报销申请集合
     */
    public List<OaReimburseApply> selectOaReimburseApplyList(OaReimburseApply oaReimburseApply);

    /**
     * 新增报销申请
     * 
     * @param oaReimburseApply 报销申请
     * @return 结果
     */
    public int insertOaReimburseApply(OaReimburseApply oaReimburseApply);

    /**
     * 修改报销申请
     * 
     * @param oaReimburseApply 报销申请
     * @return 结果
     */
    public int updateOaReimburseApply(OaReimburseApply oaReimburseApply);

    /**
     * 删除报销申请
     * 
     * @param id 报销申请主键
     * @return 结果
     */
    public int deleteOaReimburseApplyById(Long id);

    /**
     * 批量删除报销申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaReimburseApplyByIds(Long[] ids);

    /**
     * 批量删除报销费用明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteoaReimburseFeeitemByReimburseIds(Long[] ids);
    
    /**
     * 批量新增报销费用明细
     * 
     * @param oaReimburseFeeitemList 报销费用明细列表
     * @return 结果
     */
    public int batchoaReimburseFeeitem(List<OaReimburseFeeitem> oaReimburseFeeitemList);
    

    /**
     * 通过报销申请主键删除报销费用明细信息
     * 
     * @param id 报销申请ID
     * @return 结果
     */
    public int deleteoaReimburseFeeitemByReimburseId(Long id);
}
