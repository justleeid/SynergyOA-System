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
import com.fno.back.oa.domain.OaOfficeGoods;
import com.fno.back.oa.service.OaOfficeGoodsService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 办公用品Controller
 * 
 * @author fno
 * @date 2023-08-09
 */
@RestController
@RequestMapping("/oa/officeGoods")
public class OaOfficeGoodsController extends BaseController
{
    @Autowired
    private OaOfficeGoodsService oaOfficeGoodsService;

    /**
     * 查询办公用品列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoods:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaOfficeGoods oaOfficeGoods)
    {
        startPage();
        List<OaOfficeGoods> list = oaOfficeGoodsService.selectOaOfficeGoodsList(oaOfficeGoods);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('oa:officeGoods:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(OaOfficeGoods oaOfficeGoods)
    {
        List<OaOfficeGoods> list = oaOfficeGoodsService.selectOaOfficeGoodsList(oaOfficeGoods);
        return success(list);
    }

    /**
     * 导出办公用品列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoods:export')")
    @Log(title = "办公用品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaOfficeGoods oaOfficeGoods)
    {
        List<OaOfficeGoods> list = oaOfficeGoodsService.selectOaOfficeGoodsList(oaOfficeGoods);
        ExcelUtil<OaOfficeGoods> util = new ExcelUtil<OaOfficeGoods>(OaOfficeGoods.class);
        util.exportExcel(response, list, "办公用品数据");
    }

    /**
     * 获取办公用品详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaOfficeGoodsService.selectOaOfficeGoodsById(id));
    }

    /**
     * 新增办公用品
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoods:add')")
    @Log(title = "办公用品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaOfficeGoods oaOfficeGoods)
    {
        return toAjax(oaOfficeGoodsService.insertOaOfficeGoods(oaOfficeGoods));
    }

    /**
     * 修改办公用品
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoods:edit')")
    @Log(title = "办公用品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaOfficeGoods oaOfficeGoods)
    {
        return toAjax(oaOfficeGoodsService.updateOaOfficeGoods(oaOfficeGoods));
    }

    /**
     * 删除办公用品
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoods:remove')")
    @Log(title = "办公用品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaOfficeGoodsService.deleteOaOfficeGoodsByIds(ids));
    }
}
