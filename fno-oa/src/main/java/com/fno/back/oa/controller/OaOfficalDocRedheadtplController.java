package com.fno.back.oa.controller;

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
import com.fno.back.oa.domain.OaOfficalDocRedheadtpl;
import com.fno.back.oa.service.OaOfficalDocRedheadtplService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 套红模版Controller
 * 
 * @author fno
 * @date 2023-08-20
 */
@RestController
@RequestMapping("/oa/officeDocRedheadtpl")
public class OaOfficalDocRedheadtplController extends BaseController
{
    @Autowired
    private OaOfficalDocRedheadtplService oaOfficalDocRedheadtplService;

    /**
     * 查询套红模版列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        startPage();
        List<OaOfficalDocRedheadtpl> list = oaOfficalDocRedheadtplService.selectOaOfficalDocRedheadtplList(oaOfficalDocRedheadtpl);
        return getDataTable(list);
    }

    /**
     * 查询套红模版列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        List<OaOfficalDocRedheadtpl> list = oaOfficalDocRedheadtplService.selectOaOfficalDocRedheadtplList(oaOfficalDocRedheadtpl);
        return success(list);
    }

    /**
     * 导出套红模版列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:export')")
    @Log(title = "套红模版", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        List<OaOfficalDocRedheadtpl> list = oaOfficalDocRedheadtplService.selectOaOfficalDocRedheadtplList(oaOfficalDocRedheadtpl);
        ExcelUtil<OaOfficalDocRedheadtpl> util = new ExcelUtil<OaOfficalDocRedheadtpl>(OaOfficalDocRedheadtpl.class);
        util.exportExcel(response, list, "套红模版数据");
    }

    /**
     * 获取套红模版详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaOfficalDocRedheadtplService.selectOaOfficalDocRedheadtplById(id));
    }

    /**
     * 新增套红模版
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:add')")
    @Log(title = "套红模版", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        return toAjax(oaOfficalDocRedheadtplService.insertOaOfficalDocRedheadtpl(oaOfficalDocRedheadtpl));
    }

    /**
     * 修改套红模版
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:edit')")
    @Log(title = "套红模版", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        return toAjax(oaOfficalDocRedheadtplService.updateOaOfficalDocRedheadtpl(oaOfficalDocRedheadtpl));
    }

    /**
     * 删除套红模版
     */
    @PreAuthorize("@ss.hasPermi('oa:officeDocRedheadtpl:remove')")
    @Log(title = "套红模版", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaOfficalDocRedheadtplService.deleteOaOfficalDocRedheadtplByIds(ids));
    }
}
