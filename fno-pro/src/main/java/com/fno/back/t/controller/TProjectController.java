package com.fno.back.t.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.fno.back.t.domain.TProject;
import com.fno.back.t.service.TProjectService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 项目Controller
 * 
 * @author fno
 * @date 2024-02-27
 */
@RestController
@Api(tags = "项目")
@RequestMapping("/t/project")
public class TProjectController extends BaseController
{
    @Autowired
    private TProjectService tProjectService;

    /**
     * 查询项目列表
     */
    @ApiOperation(value = "查询项目列表")
    @PreAuthorize("@ss.hasPermi('t:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(TProject tProject)
    {
        startPage();
        List<TProject> list = tProjectService.selectTProjectList(tProject);
        return getDataTable(list);
    }

    /**
     * 导出项目列表
     */
    @ApiOperation(value = "导出项目列表")
    @PreAuthorize("@ss.hasPermi('t:project:export')")
    @Log(title = "项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TProject tProject)
    {
        List<TProject> list = tProjectService.selectTProjectList(tProject);
        ExcelUtil<TProject> util = new ExcelUtil<TProject>(TProject.class);
        util.exportExcel(response, list, "项目数据");
    }

    /**
     * 获取项目详细信息
     */
    @ApiOperation(value = "获取项目详细信息")
    @PreAuthorize("@ss.hasPermi('t:project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tProjectService.selectTProjectById(id));
    }

    /**
     * 新增项目
     */
    @ApiOperation(value = "新增项目")
    @PreAuthorize("@ss.hasPermi('t:project:add')")
    @Log(title = "项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TProject tProject)
    {
        tProjectService.insertTProject(tProject);
        return success(tProject);
    }

    /**
     * 修改项目
     */
    @ApiOperation(value = "修改项目")
    @PreAuthorize("@ss.hasPermi('t:project:edit')")
    @Log(title = "项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TProject tProject)
    {
        tProjectService.updateTProject(tProject);
        return success(tProject);
    }

    /**
     * 删除项目
     */
    @ApiOperation(value = "删除项目")
    @PreAuthorize("@ss.hasPermi('t:project:remove')")
    @Log(title = "项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tProjectService.deleteTProjectByIds(ids));
    }





    /****
     * 查询所有的年份
     * @return
     */
    @ApiOperation(value = "报表查询所有的年份")
    @PostMapping("/getYear")
    public AjaxResult getYear()
    {
        return success(tProjectService.getYear());
    }


    /****
     * 查询所有的年份
     * @return
     */
    @ApiOperation(value = "报表查询所有的年份")
    @PostMapping("/getProjectCount")
    public AjaxResult getProjectCount()
    {
        return success(tProjectService.getProjectCount());
    }

}
