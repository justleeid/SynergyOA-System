package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.oa.domain.OaWorkPlanItem;
import com.fno.back.oa.service.OaWorkPlanItemService;
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
import com.fno.back.oa.domain.OaWorkPlan;
import com.fno.back.oa.service.OaWorkPlanService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 工作计划Controller
 * 
 * @author fno
 * @date 2023-08-13
 */
@RestController
@RequestMapping("/oa/workPlan")
public class OaWorkPlanController extends BaseController
{
    @Autowired
    private OaWorkPlanService oaWorkPlanService;
    @Autowired
    private OaWorkPlanItemService oaWorkPlanItemService;

    /**
     * 查询工作计划列表
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlan:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaWorkPlan oaWorkPlan)
    {
        startPage();
        List<OaWorkPlan> list = oaWorkPlanService.selectOaWorkPlanList(oaWorkPlan);
        return getDataTable(list);
    }

    /**
     * 导出工作计划列表
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlan:export')")
    @Log(title = "工作计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaWorkPlan oaWorkPlan)
    {
        List<OaWorkPlan> list = oaWorkPlanService.selectOaWorkPlanList(oaWorkPlan);
        ExcelUtil<OaWorkPlan> util = new ExcelUtil<OaWorkPlan>(OaWorkPlan.class);
        util.exportExcel(response, list, "工作计划数据");
    }

    /**
     * 获取工作计划详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlan:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaWorkPlanService.selectOaWorkPlanById(id));
    }

    /**
     * 新增工作计划
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlan:add')")
    @Log(title = "工作计划", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaWorkPlan oaWorkPlan)
    {
        oaWorkPlanService.insertOaWorkPlan(oaWorkPlan);
        return success(oaWorkPlan);
    }

    /**
     * 修改工作计划
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlan:edit')")
    @Log(title = "工作计划", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaWorkPlan oaWorkPlan)
    {
        oaWorkPlanService.updateOaWorkPlan(oaWorkPlan);
        return success(oaWorkPlan);
    }

    /**
     * 删除工作计划
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlan:remove')")
    @Log(title = "工作计划", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaWorkPlanService.deleteOaWorkPlanByIds(ids));
    }



    @Log(title = "修改工作计划任务明细", businessType = BusinessType.UPDATE)
    @PutMapping("/updateItem")
    public AjaxResult updateItem(@RequestBody OaWorkPlanItem oaWorkPlanItem){
        return toAjax(oaWorkPlanItemService.updateOaWorkPlanItem(oaWorkPlanItem));
    }
}
