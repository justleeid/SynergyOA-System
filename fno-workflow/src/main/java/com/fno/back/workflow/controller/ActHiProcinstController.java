package com.fno.back.workflow.controller;

import com.fno.back.workflow.service.ActHiProcinstService;
import com.fno.back.workflow.domain.ActHiProcinst;
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
 * 流程实例Controller
 * 
 * @author fno
 * @date 2023-08-08
 */
@RestController
@RequestMapping("/oa/procinst")
public class ActHiProcinstController extends BaseController
{
    @Autowired
    private ActHiProcinstService actHiProcinstService;

    /**
     * 查询流程实例列表
     */
    @PreAuthorize("@ss.hasPermi('oa:procinst:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActHiProcinst actHiProcinst)
    {
        startPage();
        List<ActHiProcinst> list = actHiProcinstService.selectActHiProcinstList(actHiProcinst);
        return getDataTable(list);
    }

    /**
     * 导出流程实例列表
     */
    @PreAuthorize("@ss.hasPermi('oa:procinst:export')")
    @Log(title = "流程实例", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActHiProcinst actHiProcinst)
    {
        List<ActHiProcinst> list = actHiProcinstService.selectActHiProcinstList(actHiProcinst);
        ExcelUtil<ActHiProcinst> util = new ExcelUtil<ActHiProcinst>(ActHiProcinst.class);
        util.exportExcel(response, list, "流程实例数据");
    }

    /**
     * 获取流程实例详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:procinst:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(actHiProcinstService.selectActHiProcinstById(id));
    }

    /**
     * 新增流程实例
     */
    @PreAuthorize("@ss.hasPermi('oa:procinst:add')")
    @Log(title = "流程实例", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActHiProcinst actHiProcinst)
    {
        return toAjax(actHiProcinstService.insertActHiProcinst(actHiProcinst));
    }

    /**
     * 修改流程实例
     */
    @PreAuthorize("@ss.hasPermi('oa:procinst:edit')")
    @Log(title = "流程实例", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActHiProcinst actHiProcinst)
    {
        return toAjax(actHiProcinstService.updateActHiProcinst(actHiProcinst));
    }

    /**
     * 删除流程实例
     */
    @PreAuthorize("@ss.hasPermi('oa:procinst:remove')")
    @Log(title = "流程实例", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(actHiProcinstService.deleteActHiProcinstByIds(ids));
    }
}
