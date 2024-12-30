package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaOfficeGoodsApply;
import com.fno.back.oa.domain.OaOfficeGoodsApplyItem;

/**
 * 办公用品领用Mapper接口
 * 
 * @author fno
 * @date 2023-08-09
 */
public interface OaOfficeGoodsApplyMapper 
{
    /**
     * 查询办公用品领用
     * 
     * @param id 办公用品领用主键
     * @return 办公用品领用
     */
    public OaOfficeGoodsApply selectOaOfficeGoodsApplyById(Long id);

    /**
     * 查询办公用品领用列表
     * 
     * @param oaOfficeGoodsApply 办公用品领用
     * @return 办公用品领用集合
     */
    public List<OaOfficeGoodsApply> selectOaOfficeGoodsApplyList(OaOfficeGoodsApply oaOfficeGoodsApply);

    /**
     * 新增办公用品领用
     * 
     * @param oaOfficeGoodsApply 办公用品领用
     * @return 结果
     */
    public int insertOaOfficeGoodsApply(OaOfficeGoodsApply oaOfficeGoodsApply);

    /**
     * 修改办公用品领用
     * 
     * @param oaOfficeGoodsApply 办公用品领用
     * @return 结果
     */
    public int updateOaOfficeGoodsApply(OaOfficeGoodsApply oaOfficeGoodsApply);

    /**
     * 删除办公用品领用
     * 
     * @param id 办公用品领用主键
     * @return 结果
     */
    public int deleteOaOfficeGoodsApplyById(Long id);

    /**
     * 批量删除办公用品领用
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaOfficeGoodsApplyByIds(Long[] ids);

    /**
     * 批量删除办公用品领用明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaOfficeGoodsApplyItemByApplyIds(Long[] ids);
    
    /**
     * 批量新增办公用品领用明细
     * 
     * @param oaOfficeGoodsApplyItemList 办公用品领用明细列表
     * @return 结果
     */
    public int batchOaOfficeGoodsApplyItem(List<OaOfficeGoodsApplyItem> oaOfficeGoodsApplyItemList);
    

    /**
     * 通过办公用品领用主键删除办公用品领用明细信息
     * 
     * @param id 办公用品领用ID
     * @return 结果
     */
    public int deleteOaOfficeGoodsApplyItemByApplyId(Long id);
}
