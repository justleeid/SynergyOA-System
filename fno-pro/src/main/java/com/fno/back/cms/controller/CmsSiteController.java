package com.fno.back.cms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.fno.back.cms.domain.CmsSite;
import com.fno.back.cms.service.CmsSiteService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 站点管理Controller
 * 
 * @author fno
 * @date 2024-01-29
 */
@RestController
@Api(tags = "站点管理")
@RequestMapping("/cms/cmsSite")
public class CmsSiteController extends BaseController
{
    @Autowired
    private CmsSiteService cmsSiteService;


    /**
     * 查询站点管理列表
     */
    @ApiOperation(value = "查询站点管理列表")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmsSite cmsSite)
    {
        startPage();
        List<CmsSite> list = cmsSiteService.selectCmsSiteList(cmsSite);
        return getDataTable(list);
    }


    /**
     * 查询站点管理列表
     */
    @ApiOperation(value = "查询站点管理列表")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(CmsSite cmsSite)
    {
        List<CmsSite> list = cmsSiteService.selectCmsSiteList(cmsSite);
        return success(list);
    }

    /**
     * 导出站点管理列表
     */
    @ApiOperation(value = "导出站点管理列表")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:export')")
    @Log(title = "站点管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsSite cmsSite)
    {
        List<CmsSite> list = cmsSiteService.selectCmsSiteList(cmsSite);
        ExcelUtil<CmsSite> util = new ExcelUtil<CmsSite>(CmsSite.class);
        util.exportExcel(response, list, "站点管理数据");
    }

    /**
     * 获取站点管理详细信息
     */
    @ApiOperation(value = "获取站点管理详细信息")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmsSiteService.selectCmsSiteById(id));
    }

    /**
     * 新增站点管理
     */
    @ApiOperation(value = "新增站点管理")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:add')")
    @Log(title = "站点管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsSite cmsSite)
    {
        cmsSiteService.insertCmsSite(cmsSite);
        return success(cmsSite);
    }

    /**
     * 修改站点管理
     */
    @ApiOperation(value = "修改站点管理")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:edit')")
    @Log(title = "站点管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsSite cmsSite)
    {
        cmsSiteService.updateCmsSite(cmsSite);
        return success(cmsSite);
    }

    /**
     * 删除站点管理
     */
    @ApiOperation(value = "删除站点管理")
    @PreAuthorize("@ss.hasPermi('cms:cmsSite:remove')")
    @Log(title = "站点管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmsSiteService.deleteCmsSiteByIds(ids));
    }
}
