package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaInvoiceMapper;
import com.fno.back.oa.domain.OaInvoice;
import com.fno.back.oa.service.OaInvoiceService;

/**
 * 发票管理Service业务层处理
 * 
 * @author fno
 * @date 2023-08-12
 */
@Service
public class OaInvoiceService
{
    @Autowired
    private OaInvoiceMapper oaInvoiceMapper;

    /**
     * 查询发票管理
     * 
     * @param id 发票管理主键
     * @return 发票管理
     */
    public OaInvoice selectOaInvoiceById(Long id)
    {
        return oaInvoiceMapper.selectOaInvoiceById(id);
    }

    /**
     * 查询发票管理列表
     * 
     * @param oaInvoice 发票管理
     * @return 发票管理
     */
    public List<OaInvoice> selectOaInvoiceList(OaInvoice oaInvoice)
    {
        return oaInvoiceMapper.selectOaInvoiceList(oaInvoice);
    }

    /**
     * 新增发票管理
     * 
     * @param oaInvoice 发票管理
     * @return 结果
     */
    public int insertOaInvoice(OaInvoice oaInvoice)
    {
        oaInvoice.setCreateTime(DateUtils.getNowDate());
        return oaInvoiceMapper.insertOaInvoice(oaInvoice);
    }

    /**
     * 修改发票管理
     * 
     * @param oaInvoice 发票管理
     * @return 结果
     */
    public int updateOaInvoice(OaInvoice oaInvoice)
    {
        oaInvoice.setUpdateTime(DateUtils.getNowDate());
        return oaInvoiceMapper.updateOaInvoice(oaInvoice);
    }

    /**
     * 批量删除发票管理
     * 
     * @param ids 需要删除的发票管理主键
     * @return 结果
     */
    public int deleteOaInvoiceByIds(Long[] ids)
    {
        return oaInvoiceMapper.deleteOaInvoiceByIds(ids);
    }

    /**
     * 删除发票管理信息
     * 
     * @param id 发票管理主键
     * @return 结果
     */
    public int deleteOaInvoiceById(Long id)
    {
        return oaInvoiceMapper.deleteOaInvoiceById(id);
    }
}
