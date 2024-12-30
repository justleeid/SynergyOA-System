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
import com.fno.back.oa.domain.OaDutyApply;
import com.fno.back.oa.service.OaDutyApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 假勤申请Controller
 * 
 * @author fno
 * @date 2023-06-01
 */
@RestController
@RequestMapping("/oa/dutyApply")
public class OaDutyApplyController extends BaseController
{
    @Autowired
    private OaDutyApplyService oaDutyApplyService;

    /**
     * 查询假勤申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:dutyApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaDutyApply oaDutyApply)
    {
        startPage();
        List<OaDutyApply> list = oaDutyApplyService.selectOaDutyApplyList(oaDutyApply);
        return getDataTable(list);
    }

    /**
     * 导出假勤申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:dutyApply:export')")
    @Log(title = "假勤申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaDutyApply oaDutyApply)
    {
        List<OaDutyApply> list = oaDutyApplyService.selectOaDutyApplyList(oaDutyApply);
        ExcelUtil<OaDutyApply> util = new ExcelUtil<OaDutyApply>(OaDutyApply.class);
        util.exportExcel(response, list, "假勤申请数据");
    }

    /**
     * 获取假勤申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:dutyApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaDutyApplyService.selectOaDutyApplyById(id));
    }

    /**
     * 新增假勤申请
     */
    @PreAuthorize("@ss.hasPermi('oa:dutyApply:add')")
    @Log(title = "假勤申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaDutyApply oaDutyApply)
    {
        oaDutyApplyService.insertOaDutyApply(oaDutyApply);
        return success(oaDutyApply);
    }

    /**
     * 修改假勤申请
     */
    @PreAuthorize("@ss.hasPermi('oa:dutyApply:edit')")
    @Log(title = "假勤申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaDutyApply oaDutyApply)
    {
        oaDutyApplyService.updateOaDutyApply(oaDutyApply);
        return success(oaDutyApply);
    }

    /**
     * 删除假勤申请
     */
    @PreAuthorize("@ss.hasPermi('oa:dutyApply:remove')")
    @Log(title = "假勤申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaDutyApplyService.deleteOaDutyApplyByIds(ids));
    }




}
