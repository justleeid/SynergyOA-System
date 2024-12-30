package com.fno.back.cms.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.fno.back.cms.domain.CmsContent;
import com.fno.back.cms.service.CmsContentService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 内容管理Controller
 * 
 * @author fno
 * @date 2023-04-29
 */
@RestController
@RequestMapping("/cms/content")
public class CmsContentController extends BaseController
{
    @Autowired
    private CmsContentService cmsContentService;

    /**
     * 查询内容管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmsContent cmsContent)
    {
        startPage();
        List<CmsContent> list = cmsContentService.selectCmsContentList(cmsContent);
        return getDataTable(list);
    }

    /**
     * 导出内容管理列表
     */
    @PreAuthorize("@ss.hasPermi('cms:content:export')")
    @Log(title = "内容管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmsContent cmsContent)
    {
        List<CmsContent> list = cmsContentService.selectCmsContentList(cmsContent);
        ExcelUtil<CmsContent> util = new ExcelUtil<CmsContent>(CmsContent.class);
        util.exportExcel(response, list, "内容管理数据");
    }

    /**
     * 获取内容管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('cms:content:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cmsContentService.selectCmsContentById(id));
    }

    /**
     * 新增内容管理
     */
    @PreAuthorize("@ss.hasPermi('cms:content:add')")
    @Log(title = "内容管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmsContent cmsContent)
    {
        return toAjax(cmsContentService.insertCmsContent(cmsContent));
    }

    /**
     * 修改内容管理
     */
    @PreAuthorize("@ss.hasPermi('cms:content:edit')")
    @Log(title = "内容管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmsContent cmsContent)
    {
        return toAjax(cmsContentService.updateCmsContent(cmsContent));
    }

    /**
     * 删除内容管理
     */
    @PreAuthorize("@ss.hasPermi('cms:content:remove')")
    @Log(title = "内容管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmsContentService.deleteCmsContentByIds(ids));
    }
}
