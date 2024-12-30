package com.fno.back.common.controller;

import com.fno.back.common.domain.SysUnit;
import com.fno.back.common.service.SysUnitService;
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
 * 计量单位Controller
 * 
 * @author fno
 * @date 2022-10-20
 */
@RestController
@RequestMapping("/erp/common/unit")
public class SysUnitController extends BaseController
{
    @Autowired
    private SysUnitService sysUnitService;

    /**
     * 查询计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('common:unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUnit sysUnit)
    {
        startPage();
        List<SysUnit> list = sysUnitService.selectUnitList(sysUnit);
        return getDataTable(list);
    }

    /**
     * 查询计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('common:unit:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(SysUnit sysUnit)
    {
        List<SysUnit> list = sysUnitService.selectUnitList(sysUnit);
        return success(list);
    }

    @PreAuthorize("@ss.hasPermi('common:unit:list')")
    @GetMapping("/listNoPage")
    public AjaxResult listNoPage(SysUnit sysUnit)
    {
        List<SysUnit> list = sysUnitService.selectUnitList(sysUnit);
        return AjaxResult.success(list);
    }

    /**
     * 导出计量单位列表
     */
    @PreAuthorize("@ss.hasPermi('common:unit:export')")
    @Log(title = "计量单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUnit sysUnit)
    {
        List<SysUnit> list = sysUnitService.selectUnitList(sysUnit);
        ExcelUtil<SysUnit> util = new ExcelUtil<SysUnit>(SysUnit.class);
        util.exportExcel(response, list, "计量单位数据");
    }

    /**
     * 获取计量单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('common:unit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysUnitService.selectUnitById(id));
    }

    /**
     * 新增计量单位
     */
    @PreAuthorize("@ss.hasPermi('common:unit:add')")
    @Log(title = "计量单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUnit sysUnit)
    {
        return toAjax(sysUnitService.insertUnit(sysUnit));
    }

    /**
     * 修改计量单位
     */
    @PreAuthorize("@ss.hasPermi('common:unit:edit')")
    @Log(title = "计量单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUnit sysUnit)
    {
        return toAjax(sysUnitService.updateUnit(sysUnit));
    }

    /**
     * 删除计量单位
     */
    @PreAuthorize("@ss.hasPermi('common:unit:remove')")
    @Log(title = "计量单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysUnitService.deleteUnitByIds(ids));
    }
}
