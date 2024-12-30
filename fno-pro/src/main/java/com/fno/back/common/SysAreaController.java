package com.fno.back.common;

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
import com.fno.back.common.domain.SysArea;
import com.fno.back.common.service.SysAreaService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 省市字典Controller
 * 
 * @author fno
 * @date 2023-10-12
 */
@RestController
@RequestMapping("/common/sysArea")
public class SysAreaController extends BaseController
{
    @Autowired
    private SysAreaService sysAreaService;

    /**
     * 查询省市字典列表
     */
    @PreAuthorize("@ss.hasPermi('common:sysArea:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysArea sysArea)
    {
        startPage();
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        return getDataTable(list);
    }

    /**
     * 导出省市字典列表
     */
    @PreAuthorize("@ss.hasPermi('common:sysArea:export')")
    @Log(title = "省市字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysArea sysArea)
    {
        List<SysArea> list = sysAreaService.selectSysAreaList(sysArea);
        ExcelUtil<SysArea> util = new ExcelUtil<SysArea>(SysArea.class);
        util.exportExcel(response, list, "省市字典数据");
    }

    /**
     * 获取省市字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('common:sysArea:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysAreaService.selectSysAreaById(id));
    }

    /**
     * 新增省市字典
     */
    @PreAuthorize("@ss.hasPermi('common:sysArea:add')")
    @Log(title = "省市字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysArea sysArea)
    {
        sysAreaService.insertSysArea(sysArea);
        return success(sysArea);
    }

    /**
     * 修改省市字典
     */
    @PreAuthorize("@ss.hasPermi('common:sysArea:edit')")
    @Log(title = "省市字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysArea sysArea)
    {
        sysAreaService.updateSysArea(sysArea);
        return success(sysArea);
    }

    /**
     * 删除省市字典
     */
    @PreAuthorize("@ss.hasPermi('common:sysArea:remove')")
    @Log(title = "省市字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysAreaService.deleteSysAreaByIds(ids));
    }
}
