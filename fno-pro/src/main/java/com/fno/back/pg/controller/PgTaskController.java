package com.fno.back.pg.controller;

import java.util.List;
import java.util.Map;
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
import com.fno.back.pg.domain.PgTask;
import com.fno.back.pg.service.PgTaskService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 任务迭代Controller
 * 
 * @author fno
 * @date 2023-04-15
 */
@RestController
@RequestMapping("/pg/task")
public class PgTaskController extends BaseController
{
    @Autowired
    private PgTaskService pgTaskService;

    /**
     * 查询任务迭代列表
     */
    @PreAuthorize("@ss.hasPermi('pg:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(PgTask pgTask)
    {
        startPage();
        List<PgTask> list = pgTaskService.selectPgTaskList(pgTask);
        return getDataTable(list);
    }

    /**
     * 导出任务迭代列表
     */
    @PreAuthorize("@ss.hasPermi('pg:task:export')")
    @Log(title = "任务迭代", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PgTask pgTask)
    {
        List<PgTask> list = pgTaskService.selectPgTaskList(pgTask);
        ExcelUtil<PgTask> util = new ExcelUtil<PgTask>(PgTask.class);
        util.exportExcel(response, list, "任务迭代数据");
    }

    /**
     * 获取任务迭代详细信息
     */
    @PreAuthorize("@ss.hasPermi('pg:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pgTaskService.selectPgTaskById(id));
    }

    /**
     * 新增任务迭代
     */
    @PreAuthorize("@ss.hasPermi('pg:task:add')")
    @Log(title = "任务迭代", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PgTask pgTask)
    {
        return toAjax(pgTaskService.insertPgTask(pgTask));
    }

    /**
     * 修改任务迭代
     */
    @PreAuthorize("@ss.hasPermi('pg:task:edit')")
    @Log(title = "任务迭代", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PgTask pgTask)
    {
        return toAjax(pgTaskService.updatePgTask(pgTask));
    }

    /**
     * 删除任务迭代
     */
    @PreAuthorize("@ss.hasPermi('pg:task:remove')")
    @Log(title = "任务迭代", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pgTaskService.deletePgTaskByIds(ids));
    }



    @RequestMapping("/getRoleChart/{id}")
    public AjaxResult getRoleChart(@PathVariable Long id){
        List<Map> list = pgTaskService.getRoleChart(id);
        return success(list);
    }


    @RequestMapping("/getPostChart/{id}")
    public AjaxResult getPostChart(@PathVariable Long id){
        List<Map> list = pgTaskService.getPostChart(id);
        return success(list);
    }
}
