package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaOfficeGoodsMapper;
import com.fno.back.oa.domain.OaOfficeGoods;
import com.fno.back.oa.service.OaOfficeGoodsService;

/**
 * 办公用品Service业务层处理
 * 
 * @author fno
 * @date 2023-08-09
 */
@Service
public class OaOfficeGoodsService
{
    @Autowired
    private OaOfficeGoodsMapper oaOfficeGoodsMapper;

    /**
     * 查询办公用品
     * 
     * @param id 办公用品主键
     * @return 办公用品
     */
    public OaOfficeGoods selectOaOfficeGoodsById(Long id)
    {
        return oaOfficeGoodsMapper.selectOaOfficeGoodsById(id);
    }

    /**
     * 查询办公用品列表
     * 
     * @param oaOfficeGoods 办公用品
     * @return 办公用品
     */
    public List<OaOfficeGoods> selectOaOfficeGoodsList(OaOfficeGoods oaOfficeGoods)
    {
        //租户
        oaOfficeGoods.setTenantId(SecurityUtils.getTenantId());
        return oaOfficeGoodsMapper.selectOaOfficeGoodsList(oaOfficeGoods);
    }

    /**
     * 新增办公用品
     * 
     * @param oaOfficeGoods 办公用品
     * @return 结果
     */
    public int insertOaOfficeGoods(OaOfficeGoods oaOfficeGoods)
    {
        oaOfficeGoods.setCreateTime(DateUtils.getNowDate());
        //租户
        oaOfficeGoods.setTenantId(SecurityUtils.getTenantId());
        return oaOfficeGoodsMapper.insertOaOfficeGoods(oaOfficeGoods);
    }

    /**
     * 修改办公用品
     * 
     * @param oaOfficeGoods 办公用品
     * @return 结果
     */
    public int updateOaOfficeGoods(OaOfficeGoods oaOfficeGoods)
    {
        oaOfficeGoods.setUpdateTime(DateUtils.getNowDate());
        return oaOfficeGoodsMapper.updateOaOfficeGoods(oaOfficeGoods);
    }

    /**
     * 批量删除办公用品
     * 
     * @param ids 需要删除的办公用品主键
     * @return 结果
     */
    public int deleteOaOfficeGoodsByIds(Long[] ids)
    {
        return oaOfficeGoodsMapper.deleteOaOfficeGoodsByIds(ids);
    }

    /**
     * 删除办公用品信息
     * 
     * @param id 办公用品主键
     * @return 结果
     */
    public int deleteOaOfficeGoodsById(Long id)
    {
        return oaOfficeGoodsMapper.deleteOaOfficeGoodsById(id);
    }
}
