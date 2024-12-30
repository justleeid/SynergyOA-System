package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaOfficeGoods;

/**
 * 办公用品Mapper接口
 * 
 * @author fno
 * @date 2023-08-09
 */
public interface OaOfficeGoodsMapper 
{
    /**
     * 查询办公用品
     * 
     * @param id 办公用品主键
     * @return 办公用品
     */
    public OaOfficeGoods selectOaOfficeGoodsById(Long id);

    /**
     * 查询办公用品列表
     * 
     * @param oaOfficeGoods 办公用品
     * @return 办公用品集合
     */
    public List<OaOfficeGoods> selectOaOfficeGoodsList(OaOfficeGoods oaOfficeGoods);

    /**
     * 新增办公用品
     * 
     * @param oaOfficeGoods 办公用品
     * @return 结果
     */
    public int insertOaOfficeGoods(OaOfficeGoods oaOfficeGoods);

    /**
     * 修改办公用品
     * 
     * @param oaOfficeGoods 办公用品
     * @return 结果
     */
    public int updateOaOfficeGoods(OaOfficeGoods oaOfficeGoods);

    /**
     * 删除办公用品
     * 
     * @param id 办公用品主键
     * @return 结果
     */
    public int deleteOaOfficeGoodsById(Long id);

    /**
     * 批量删除办公用品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaOfficeGoodsByIds(Long[] ids);
}
