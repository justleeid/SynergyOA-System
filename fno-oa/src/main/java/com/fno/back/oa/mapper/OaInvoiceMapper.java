package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaInvoice;

/**
 * 发票管理Mapper接口
 * 
 * @author fno
 * @date 2023-08-12
 */
public interface OaInvoiceMapper 
{
    /**
     * 查询发票管理
     * 
     * @param id 发票管理主键
     * @return 发票管理
     */
    public OaInvoice selectOaInvoiceById(Long id);

    /**
     * 查询发票管理列表
     * 
     * @param oaInvoice 发票管理
     * @return 发票管理集合
     */
    public List<OaInvoice> selectOaInvoiceList(OaInvoice oaInvoice);

    /**
     * 新增发票管理
     * 
     * @param oaInvoice 发票管理
     * @return 结果
     */
    public int insertOaInvoice(OaInvoice oaInvoice);

    /**
     * 修改发票管理
     * 
     * @param oaInvoice 发票管理
     * @return 结果
     */
    public int updateOaInvoice(OaInvoice oaInvoice);

    /**
     * 删除发票管理
     * 
     * @param id 发票管理主键
     * @return 结果
     */
    public int deleteOaInvoiceById(Long id);

    /**
     * 批量删除发票管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaInvoiceByIds(Long[] ids);
}
