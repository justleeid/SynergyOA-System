package com.fno.back.oa.controller;

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
import com.fno.back.oa.domain.OaSendTask;
import com.fno.back.oa.service.OaSendTaskService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 任务分配Controller
 * 
 * @author fno
 * @date 2023-08-19
 */
@RestController
@RequestMapping("/oa/sendTask")
public class OaSendTaskController extends BaseController
{
    @Autowired
    private OaSendTaskService oaSendTaskService;

    /**
     * 查询任务分配列表
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaSendTask oaSendTask)
    {
        startPage();
        List<OaSendTask> list = oaSendTaskService.selectOaSendTaskList(oaSendTask);
        return getDataTable(list);
    }

    /**
     * 导出任务分配列表
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTask:export')")
    @Log(title = "任务分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaSendTask oaSendTask)
    {
        List<OaSendTask> list = oaSendTaskService.selectOaSendTaskList(oaSendTask);
        ExcelUtil<OaSendTask> util = new ExcelUtil<OaSendTask>(OaSendTask.class);
        util.exportExcel(response, list, "任务分配数据");
    }

    /**
     * 获取任务分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaSendTaskService.selectOaSendTaskById(id));
    }

    /**
     * 新增任务分配
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTask:add')")
    @Log(title = "任务分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaSendTask oaSendTask)
    {
        oaSendTaskService.insertOaSendTask(oaSendTask);
        return success(oaSendTask);
    }

    /**
     * 修改任务分配
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTask:edit')")
    @Log(title = "任务分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaSendTask oaSendTask)
    {
        oaSendTaskService.updateOaSendTask(oaSendTask);
        return success(oaSendTask);
    }

    /**
     * 删除任务分配
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTask:remove')")
    @Log(title = "任务分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaSendTaskService.deleteOaSendTaskByIds(ids));
    }
}
