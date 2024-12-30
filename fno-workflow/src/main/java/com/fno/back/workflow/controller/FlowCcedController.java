package com.fno.back.workflow.controller;

import com.fno.back.workflow.domain.FlowCced;
import com.fno.back.workflow.service.FlowCcedService;
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
 * 抄送我的Controller
 * 
 * @author fno
 * @date 2023-08-04
 */
@RestController
@RequestMapping("/oa/flowCced")
public class FlowCcedController extends BaseController
{
    @Autowired
    private FlowCcedService flowCcedService;

    /**
     * 查询抄送我的列表
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:list')")
    @GetMapping("/list")
    public TableDataInfo list(FlowCced oaFlowCced)
    {
        startPage();
        List<FlowCced> list = flowCcedService.selectOaFlowCcedList(oaFlowCced);
        return getDataTable(list);
    }

    /**
     * 查询抄送我的列表
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:list')")
    @GetMapping("/listToMe")
    public TableDataInfo listToMe(FlowCced oaFlowCced)
    {
        startPage();
        List<FlowCced> list = flowCcedService.selectCcedListToMe(oaFlowCced);
        return getDataTable(list);
    }


    /**
     * 计算抄送数量
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:list')")
    @GetMapping("/countCced")
    public AjaxResult countCced(FlowCced oaFlowCced)
    {
        int count = flowCcedService.countCced(oaFlowCced);
        return success(count);
    }

    /**
     * 导出抄送我的列表
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:export')")
    @Log(title = "抄送我的", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FlowCced flowCced)
    {
        List<FlowCced> list = flowCcedService.selectOaFlowCcedList(flowCced);
        ExcelUtil<FlowCced> util = new ExcelUtil<FlowCced>(FlowCced.class);
        util.exportExcel(response, list, "抄送我的数据");
    }

    /**
     * 获取抄送我的详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(flowCcedService.selectOaFlowCcedById(id));
    }

    /**
     * 新增抄送我的
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:add')")
    @Log(title = "抄送我的", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FlowCced oaFlowCced)
    {
        return toAjax(flowCcedService.insertOaFlowCced(oaFlowCced));
    }

    /**
     * 修改抄送我的
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:edit')")
    @Log(title = "抄送我的", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FlowCced oaFlowCced)
    {
        return toAjax(flowCcedService.updateOaFlowCced(oaFlowCced));
    }

    /**
     * 删除抄送我的
     */
    @PreAuthorize("@ss.hasPermi('oa:flowCced:remove')")
    @Log(title = "抄送我的", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(flowCcedService.deleteOaFlowCcedByIds(ids));
    }
}
