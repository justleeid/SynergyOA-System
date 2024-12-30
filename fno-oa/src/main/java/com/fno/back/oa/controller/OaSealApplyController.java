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
import com.fno.back.oa.domain.OaSealApply;
import com.fno.back.oa.service.OaSealApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 公章使用Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/sealApply")
public class OaSealApplyController extends BaseController
{
    @Autowired
    private OaSealApplyService oaSealApplyService;

    /**
     * 查询公章使用列表
     */
    @PreAuthorize("@ss.hasPermi('oa:sealApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaSealApply oaSealApply)
    {
        startPage();
        List<OaSealApply> list = oaSealApplyService.selectOaSealApplyList(oaSealApply);
        return getDataTable(list);
    }

    /**
     * 导出公章使用列表
     */
    @PreAuthorize("@ss.hasPermi('oa:sealApply:export')")
    @Log(title = "公章使用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaSealApply oaSealApply)
    {
        List<OaSealApply> list = oaSealApplyService.selectOaSealApplyList(oaSealApply);
        ExcelUtil<OaSealApply> util = new ExcelUtil<OaSealApply>(OaSealApply.class);
        util.exportExcel(response, list, "公章使用数据");
    }

    /**
     * 获取公章使用详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:sealApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaSealApplyService.selectOaSealApplyById(id));
    }

    /**
     * 新增公章使用
     */
    @PreAuthorize("@ss.hasPermi('oa:sealApply:add')")
    @Log(title = "公章使用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaSealApply oaSealApply)
    {
        oaSealApplyService.insertOaSealApply(oaSealApply);
        return success(oaSealApply);
    }

    /**
     * 修改公章使用
     */
    @PreAuthorize("@ss.hasPermi('oa:sealApply:edit')")
    @Log(title = "公章使用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaSealApply oaSealApply)
    {
        oaSealApplyService.updateOaSealApply(oaSealApply);
        return success(oaSealApply);
    }

    /**
     * 删除公章使用
     */
    @PreAuthorize("@ss.hasPermi('oa:sealApply:remove')")
    @Log(title = "公章使用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaSealApplyService.deleteOaSealApplyByIds(ids));
    }
}
