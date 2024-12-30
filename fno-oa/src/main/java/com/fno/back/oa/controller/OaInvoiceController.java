package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.common.utils.SecurityUtils;
import liquibase.pro.packaged.S;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.enums.BusinessType;
import com.fno.back.oa.domain.OaInvoice;
import com.fno.back.oa.service.OaInvoiceService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 发票管理Controller
 * 
 * @author fno
 * @date 2023-08-12
 */
@RestController
@RequestMapping("/oa/invoice")
public class OaInvoiceController extends BaseController
{
    @Autowired
    private OaInvoiceService oaInvoiceService;

    /**
     * 查询发票管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:invoice:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaInvoice oaInvoice)
    {
        startPage();
        long tenantId = SecurityUtils.getTenantId();
        oaInvoice.setTenantId(tenantId);
        List<OaInvoice> list = oaInvoiceService.selectOaInvoiceList(oaInvoice);
        return getDataTable(list);
    }

    /**
     * 导出发票管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:invoice:export')")
    @Log(title = "发票管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaInvoice oaInvoice)
    {
        long tenantId = SecurityUtils.getTenantId();
        oaInvoice.setTenantId(tenantId);
        List<OaInvoice> list = oaInvoiceService.selectOaInvoiceList(oaInvoice);
        ExcelUtil<OaInvoice> util = new ExcelUtil<OaInvoice>(OaInvoice.class);
        util.exportExcel(response, list, "发票管理数据");
    }

    /**
     * 获取发票管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:invoice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaInvoiceService.selectOaInvoiceById(id));
    }

    /**
     * 新增发票管理
     */
    @PreAuthorize("@ss.hasPermi('oa:invoice:add')")
    @Log(title = "发票管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaInvoice oaInvoice)
    {
        long tenantId = SecurityUtils.getTenantId();
        oaInvoice.setTenantId(tenantId);
        return toAjax(oaInvoiceService.insertOaInvoice(oaInvoice));
    }

    /**
     * 修改发票管理
     */
    @PreAuthorize("@ss.hasPermi('oa:invoice:edit')")
    @Log(title = "发票管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaInvoice oaInvoice)
    {
        return toAjax(oaInvoiceService.updateOaInvoice(oaInvoice));
    }

    /**
     * 删除发票管理
     */
    @PreAuthorize("@ss.hasPermi('oa:invoice:remove')")
    @Log(title = "发票管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaInvoiceService.deleteOaInvoiceByIds(ids));
    }
}
