package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.common.utils.SecurityUtils;
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
import com.fno.back.oa.domain.OaReimburseApply;
import com.fno.back.oa.service.OaReimburseApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 报销申请Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/reimburseApply")
public class OaReimburseApplyController extends BaseController
{
    @Autowired
    private OaReimburseApplyService oaReimburseApplyService;

    /**
     * 查询报销申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:reimburseApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaReimburseApply oaReimburseApply)
    {
        startPage();
        List<OaReimburseApply> list = oaReimburseApplyService.selectOaReimburseApplyList(oaReimburseApply);
        return getDataTable(list);
    }

    /**
     * 导出报销申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:reimburseApply:export')")
    @Log(title = "报销申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaReimburseApply oaReimburseApply)
    {
        List<OaReimburseApply> list = oaReimburseApplyService.selectOaReimburseApplyList(oaReimburseApply);
        ExcelUtil<OaReimburseApply> util = new ExcelUtil<OaReimburseApply>(OaReimburseApply.class);
        util.exportExcel(response, list, "报销申请数据");
    }

    /**
     * 获取报销申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:reimburseApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaReimburseApplyService.selectOaReimburseApplyById(id));
    }

    /**
     * 新增报销申请
     */
    @PreAuthorize("@ss.hasPermi('oa:reimburseApply:add')")
    @Log(title = "报销申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaReimburseApply oaReimburseApply)
    {
        oaReimburseApplyService.insertOaReimburseApply(oaReimburseApply);
        return success(oaReimburseApply);
    }

    /**
     * 修改报销申请
     */
    @PreAuthorize("@ss.hasPermi('oa:reimburseApply:edit')")
    @Log(title = "报销申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaReimburseApply oaReimburseApply)
    {
        oaReimburseApplyService.updateOaReimburseApply(oaReimburseApply);
        return success(oaReimburseApply);
    }

    /**
     * 删除报销申请
     */
    @PreAuthorize("@ss.hasPermi('oa:reimburseApply:remove')")
    @Log(title = "报销申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaReimburseApplyService.deleteOaReimburseApplyByIds(ids));
    }





}
