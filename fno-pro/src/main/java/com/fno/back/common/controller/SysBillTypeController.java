package com.fno.back.common.controller;

import com.fno.back.common.domain.SysBillType;
import com.fno.back.common.service.SysBillTypeService;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 单据类型配置Controller
 * 
 * @author fno
 * @date 2023-08-30
 */
@RestController
@RequestMapping("/oa/billType")
public class SysBillTypeController extends BaseController
{
    @Autowired
    private SysBillTypeService sysBillTypeService;

    /**
     * 查询单据类型配置列表
     */
    @PreAuthorize("@ss.hasPermi('oa:billType:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBillType sysBillType)
    {
        startPage();
        List<SysBillType> list = sysBillTypeService.selectOaBillTypeList(sysBillType);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public AjaxResult listAll(SysBillType sysBillType)
    {
        List<SysBillType> list = sysBillTypeService.selectOaBillTypeListAll(sysBillType);
        return success(list);
    }

    /**
     * 导出单据类型配置列表
     */
    @PreAuthorize("@ss.hasPermi('oa:billType:export')")
    @Log(title = "单据类型配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysBillType sysBillType)
    {
        List<SysBillType> list = sysBillTypeService.selectOaBillTypeList(sysBillType);
        ExcelUtil<SysBillType> util = new ExcelUtil<SysBillType>(SysBillType.class);
        util.exportExcel(response, list, "单据类型配置数据");
    }

    /**
     * 获取单据类型配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:billType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysBillTypeService.selectOaBillTypeById(id));
    }

    /**
     * 新增单据类型配置
     */
    @PreAuthorize("@ss.hasPermi('oa:billType:add')")
    @Log(title = "单据类型配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysBillType sysBillType)
    {
        return toAjax(sysBillTypeService.insertOaBillType(sysBillType));
    }

    /**
     * 修改单据类型配置
     */
    @PreAuthorize("@ss.hasPermi('oa:billType:edit')")
    @Log(title = "单据类型配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysBillType sysBillType)
    {
        return toAjax(sysBillTypeService.updateOaBillType(sysBillType));
    }

    /**
     * 删除单据类型配置
     */
    @PreAuthorize("@ss.hasPermi('oa:billType:remove')")
    @Log(title = "单据类型配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysBillTypeService.deleteOaBillTypeByIds(ids));
    }
}
