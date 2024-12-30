package com.fno.back.cms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.common.constant.CommonConstants;
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
import com.fno.back.cms.domain.CmsCatalog;
import com.fno.back.cms.service.CmsCatalogService;
import com.fno.common.utils.poi.ExcelUtil;

/**
 * 栏目管理Controller
 * 
 * @author fno
 * @date 2023-04-29
 */
@RestController
@RequestMapping("/cms/catalog")
public class CmsCatalogController extends BaseController
{
    @Autowired
    private CmsCatalogService cmsCatalogService;

    /**
     * 查询栏目管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:catalog:list')")
    @GetMapping("/list")
    public AjaxResult list(CmsCatalog cmsCatalog)
    {
        List<CmsCatalog> list = cmsCatalogService.selectCmsCatalogList(cmsCatalog);
        return AjaxResult.success(list);
    }

    /**
     * 导出栏目管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:catalog:export')")
    @Log(title = "栏目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsCatalog cmsCatalog)
    {
        List<CmsCatalog> list = cmsCatalogService.selectCmsCatalogList(cmsCatalog);
        ExcelUtil<CmsCatalog> util = new ExcelUtil<CmsCatalog>(CmsCatalog.class);
        util.exportExcel(response, list, "栏目管理数据");
    }

    /**
     * 获取栏目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:catalog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmsCatalogService.selectCmsCatalogById(id));
    }

    /**
     * 新增栏目管理
     */
    @PreAuthorize("@ss.hasPermi('cms:catalog:add')")
    @Log(title = "栏目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsCatalog cmsCatalog)
    {
        return toAjax(cmsCatalogService.insertCmsCatalog(cmsCatalog));
    }

    /**
     * 修改栏目管理
     */
    @PreAuthorize("@ss.hasPermi('cms:catalog:edit')")
    @Log(title = "栏目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsCatalog cmsCatalog)
    {
        return toAjax(cmsCatalogService.updateCmsCatalog(cmsCatalog));
    }

    /**
     * 删除栏目管理
     */
    @PreAuthorize("@ss.hasPermi('cms:catalog:remove')")
    @Log(title = "栏目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmsCatalogService.deleteCmsCatalogByIds(ids));
    }




    @GetMapping("/treeselect")
    public AjaxResult treeselect(CmsCatalog catalog)
    {
        catalog.setVisible(CommonConstants.YES);
        List<CmsCatalog> depts = cmsCatalogService.selectCmsCatalogList(catalog);
        return AjaxResult.success(cmsCatalogService.buildTreeSelect(depts));
    }




}
