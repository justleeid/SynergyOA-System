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
import com.fno.back.oa.domain.OaMeetingroomApply;
import com.fno.back.oa.service.OaMeetingroomApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 会议室申请Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/meetingroomApply")
public class OaMeetingroomApplyController extends BaseController
{
    @Autowired
    private OaMeetingroomApplyService oaMeetingroomApplyService;

    /**
     * 查询会议室申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroomApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaMeetingroomApply oaMeetingroomApply)
    {
        startPage();
        List<OaMeetingroomApply> list = oaMeetingroomApplyService.selectOaMeetingroomApplyList(oaMeetingroomApply);
        return getDataTable(list);
    }

    /**
     * 导出会议室申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroomApply:export')")
    @Log(title = "会议室申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaMeetingroomApply oaMeetingroomApply)
    {
        List<OaMeetingroomApply> list = oaMeetingroomApplyService.selectOaMeetingroomApplyList(oaMeetingroomApply);
        ExcelUtil<OaMeetingroomApply> util = new ExcelUtil<OaMeetingroomApply>(OaMeetingroomApply.class);
        util.exportExcel(response, list, "会议室申请数据");
    }

    /**
     * 获取会议室申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroomApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaMeetingroomApplyService.selectOaMeetingroomApplyById(id));
    }

    /**
     * 新增会议室申请
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroomApply:add')")
    @Log(title = "会议室申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaMeetingroomApply oaMeetingroomApply)
    {
        oaMeetingroomApplyService.insertOaMeetingroomApply(oaMeetingroomApply);
        return success(oaMeetingroomApply);
    }

    /**
     * 修改会议室申请
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroomApply:edit')")
    @Log(title = "会议室申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaMeetingroomApply oaMeetingroomApply)
    {
        oaMeetingroomApplyService.updateOaMeetingroomApply(oaMeetingroomApply);
        return success(oaMeetingroomApply);
    }

    /**
     * 删除会议室申请
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroomApply:remove')")
    @Log(title = "会议室申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaMeetingroomApplyService.deleteOaMeetingroomApplyByIds(ids));
    }
}
