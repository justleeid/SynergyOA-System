package com.fno.back.workflow.controller;

import com.fno.back.workflow.service.ActHiTaskinstService;
import com.fno.back.workflow.domain.ActHiTaskinst;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 历史任务Controller
 *
 */
@RestController
@RequestMapping("/oa/actHiTaskInst")
public class FlowHiTaskinstController extends BaseController
{
    @Autowired
    private ActHiTaskinstService actHiTaskinstService;

    /**
     * 查询历史任务列表
     */
    @PreAuthorize("@ss.hasPermi('oa:actHiTaskInst:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActHiTaskinst actHiTaskinst)
    {
        startPage();
        Long tenantId = SecurityUtils.getTenantId();
        actHiTaskinst.setTenantId(tenantId);
        List<ActHiTaskinst> list = actHiTaskinstService.selectActHiTaskinstList(actHiTaskinst);
        return getDataTable(list);
    }

    /**
     * 导出历史任务列表
     */
    @PreAuthorize("@ss.hasPermi('oa:actHiTaskInst:export')")
    @Log(title = "历史任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActHiTaskinst actHiTaskinst)
    {
        Long tenantId = SecurityUtils.getTenantId();
        actHiTaskinst.setTenantId(tenantId);
        List<ActHiTaskinst> list = actHiTaskinstService.selectActHiTaskinstList(actHiTaskinst);
        ExcelUtil<ActHiTaskinst> util = new ExcelUtil<ActHiTaskinst>(ActHiTaskinst.class);
        util.exportExcel(response, list, "历史任务数据");
    }

    /**
     * 获取历史任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:actHiTaskInst:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(actHiTaskinstService.selectActHiTaskinstById(id));
    }

    /**
     * 新增历史任务
     */
    @PreAuthorize("@ss.hasPermi('oa:actHiTaskInst:add')")
    @Log(title = "历史任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActHiTaskinst actHiTaskinst)
    {
        Long tenantId = SecurityUtils.getTenantId();
        actHiTaskinst.setTenantId(tenantId);
        return toAjax(actHiTaskinstService.insertActHiTaskinst(actHiTaskinst));
    }

    /**
     * 修改历史任务
     */
    @PreAuthorize("@ss.hasPermi('oa:actHiTaskInst:edit')")
    @Log(title = "历史任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActHiTaskinst actHiTaskinst)
    {
        return toAjax(actHiTaskinstService.updateActHiTaskinst(actHiTaskinst));
    }

    /**
     * 删除历史任务
     */
    @PreAuthorize("@ss.hasPermi('oa:actHiTaskInst:remove')")
    @Log(title = "历史任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(actHiTaskinstService.deleteActHiTaskinstByIds(ids));
    }


    /*****
     * 获取任务执行次数
     * @return
     */
    @RequestMapping("/getTaskCishuReport")
    public TableDataInfo getTaskCishuReport(ActHiTaskinst actHiTaskinst){
        startPage();
        List<Map> list = actHiTaskinstService.getTaskCishuReport(actHiTaskinst);
        return getDataTable(list);
    }
}
