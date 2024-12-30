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
import com.fno.back.t.domain.TAuditProject;
import com.fno.back.t.service.TAuditProjectService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 审核项目Controller
 * 
 * @author fno
 * @date 2024-02-27
 */
@RestController
@Api(tags = "审核项目")
@RequestMapping("/t/auditProject")
public class TAuditProjectController extends BaseController
{
    @Autowired
    private TAuditProjectService tAuditProjectService;

    /**
     * 查询审核项目列表
     */
    @ApiOperation(value = "查询审核项目列表")
    @PreAuthorize("@ss.hasPermi('t:auditProject:list')")
    @GetMapping("/list")
    public TableDataInfo list(TAuditProject tAuditProject)
    {
        startPage();
        List<TAuditProject> list = tAuditProjectService.selectTAuditProjectList(tAuditProject);
        return getDataTable(list);
    }

    /**
     * 导出审核项目列表
     */
    @ApiOperation(value = "导出审核项目列表")
    @PreAuthorize("@ss.hasPermi('t:auditProject:export')")
    @Log(title = "审核项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TAuditProject tAuditProject)
    {
        List<TAuditProject> list = tAuditProjectService.selectTAuditProjectList(tAuditProject);
        ExcelUtil<TAuditProject> util = new ExcelUtil<TAuditProject>(TAuditProject.class);
        util.exportExcel(response, list, "审核项目数据");
    }

    /**
     * 获取审核项目详细信息
     */
    @ApiOperation(value = "获取审核项目详细信息")
    @PreAuthorize("@ss.hasPermi('t:auditProject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tAuditProjectService.selectTAuditProjectById(id));
    }

    /**
     * 新增审核项目
     */
    @ApiOperation(value = "新增审核项目")
    @PreAuthorize("@ss.hasPermi('t:auditProject:add')")
    @Log(title = "审核项目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TAuditProject tAuditProject)
    {
        tAuditProjectService.insertTAuditProject(tAuditProject);
        return success(tAuditProject);
    }

    /**
     * 修改审核项目
     */
    @ApiOperation(value = "修改审核项目")
    @PreAuthorize("@ss.hasPermi('t:auditProject:edit')")
    @Log(title = "审核项目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TAuditProject tAuditProject)
    {
        tAuditProjectService.updateTAuditProject(tAuditProject);
        return success(tAuditProject);
    }

    /**
     * 删除审核项目
     */
    @ApiOperation(value = "删除审核项目")
    @PreAuthorize("@ss.hasPermi('t:auditProject:remove')")
    @Log(title = "审核项目", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tAuditProjectService.deleteTAuditProjectByIds(ids));
    }




    /****
     * 查询所有的年份
     * @return
     */
    @ApiOperation(value = "报表查询所有的年份")
    @PostMapping("/getYear")
    public AjaxResult getYear()
    {
        return success(tAuditProjectService.getYear());
    }


    /****
     * 查询所有的年份
     * @return
     */
    @ApiOperation(value = "报表查询所有的年份")
    @PostMapping("/getAuditProjectCount")
    public AjaxResult getAuditProjectCount()
    {
        return success(tAuditProjectService.getProjectCount());
    }




}
