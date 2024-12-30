package com.fno.back.pg.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.pg.service.PgProjectService;
import com.fno.common.constant.Constants;
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
import com.fno.back.pg.domain.PgProject;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 项目管理Controller
 * 
 * @author fno
 * @date 2023-04-15
 */
@RestController
@RequestMapping("/pg/project")
public class PgProjectController extends BaseController
{
    @Autowired
    private PgProjectService pgProjectService;

    /**
     * 查询项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('pg:project:list')")
    @GetMapping("/list")
    public TableDataInfo list(PgProject pgProject)
    {
        startPage();
        List<PgProject> list = pgProjectService.selectPgProjectList(pgProject);
        return getDataTable(list);
    }


    @PreAuthorize("@ss.hasPermi('pg:project:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(PgProject pgProject)
    {
        List<PgProject> list = pgProjectService.selectPgProjectList(pgProject);
        return success("成功",list, Constants.RENDER_JSON_TYPE_ARR);
    }


    /**
     * 导出项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('pg:project:export')")
    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PgProject pgProject)
    {
        List<PgProject> list = pgProjectService.selectPgProjectList(pgProject);
        ExcelUtil<PgProject> util = new ExcelUtil<PgProject>(PgProject.class);
        util.exportExcel(response, list, "项目管理数据");
    }

    /**
     * 获取项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pg:project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pgProjectService.selectPgProjectById(id));
    }

    /**
     * 新增项目管理
     */
    @PreAuthorize("@ss.hasPermi('pg:project:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PgProject pgProject)
    {
        return toAjax(pgProjectService.insertPgProject(pgProject));
    }

    /**
     * 修改项目管理
     */
    @PreAuthorize("@ss.hasPermi('pg:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PgProject pgProject)
    {
        return toAjax(pgProjectService.updatePgProject(pgProject));
    }

    /**
     * 删除项目管理
     */
    @PreAuthorize("@ss.hasPermi('pg:project:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pgProjectService.deletePgProjectByIds(ids));
    }
}
