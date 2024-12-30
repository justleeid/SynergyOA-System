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
import com.fno.back.oa.domain.OaOfficeGoodsApply;
import com.fno.back.oa.service.OaOfficeGoodsApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 办公用品领用Controller
 * 
 * @author fno
 * @date 2023-08-09
 */
@RestController
@RequestMapping("/oa/officeGoodsApply")
public class OaOfficeGoodsApplyController extends BaseController
{
    @Autowired
    private OaOfficeGoodsApplyService oaOfficeGoodsApplyService;

    /**
     * 查询办公用品领用列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoodsApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        startPage();
        List<OaOfficeGoodsApply> list = oaOfficeGoodsApplyService.selectOaOfficeGoodsApplyList(oaOfficeGoodsApply);
        return getDataTable(list);
    }

    /**
     * 导出办公用品领用列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoodsApply:export')")
    @Log(title = "办公用品领用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        List<OaOfficeGoodsApply> list = oaOfficeGoodsApplyService.selectOaOfficeGoodsApplyList(oaOfficeGoodsApply);
        ExcelUtil<OaOfficeGoodsApply> util = new ExcelUtil<OaOfficeGoodsApply>(OaOfficeGoodsApply.class);
        util.exportExcel(response, list, "办公用品领用数据");
    }

    /**
     * 获取办公用品领用详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoodsApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaOfficeGoodsApplyService.selectOaOfficeGoodsApplyById(id));
    }

    /**
     * 新增办公用品领用
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoodsApply:add')")
    @Log(title = "办公用品领用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        oaOfficeGoodsApplyService.insertOaOfficeGoodsApply(oaOfficeGoodsApply);
        return success(oaOfficeGoodsApply);
    }

    /**
     * 修改办公用品领用
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoodsApply:edit')")
    @Log(title = "办公用品领用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        oaOfficeGoodsApplyService.updateOaOfficeGoodsApply(oaOfficeGoodsApply);
        return success(oaOfficeGoodsApply);
    }

    /**
     * 删除办公用品领用
     */
    @PreAuthorize("@ss.hasPermi('oa:officeGoodsApply:remove')")
    @Log(title = "办公用品领用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaOfficeGoodsApplyService.deleteOaOfficeGoodsApplyByIds(ids));
    }
}
