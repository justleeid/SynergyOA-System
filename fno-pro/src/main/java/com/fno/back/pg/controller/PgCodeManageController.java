package com.fno.back.pg.controller;

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
import com.fno.back.pg.domain.PgCodeManage;
import com.fno.back.pg.service.PgCodeManageService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 代码管理Controller
 * 
 * @author fno
 * @date 2023-04-15
 */
@RestController
@RequestMapping("/pg/code")
public class PgCodeManageController extends BaseController
{
    @Autowired
    private PgCodeManageService pgCodeManageService;

    /**
     * 查询代码管理列表
     */
    @PreAuthorize("@ss.hasPermi('pg:code:list')")
    @GetMapping("/list")
    public TableDataInfo list(PgCodeManage pgCodeManage)
    {
        startPage();
        List<PgCodeManage> list = pgCodeManageService.selectPgCodeManageList(pgCodeManage);
        return getDataTable(list);
    }

    /**
     * 导出代码管理列表
     */
    @PreAuthorize("@ss.hasPermi('pg:code:export')")
    @Log(title = "代码管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PgCodeManage pgCodeManage)
    {
        List<PgCodeManage> list = pgCodeManageService.selectPgCodeManageList(pgCodeManage);
        ExcelUtil<PgCodeManage> util = new ExcelUtil<PgCodeManage>(PgCodeManage.class);
        util.exportExcel(response, list, "代码管理数据");
    }

    /**
     * 获取代码管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('pg:code:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pgCodeManageService.selectPgCodeManageById(id));
    }

    /**
     * 新增代码管理
     */
    @PreAuthorize("@ss.hasPermi('pg:code:add')")
    @Log(title = "代码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PgCodeManage pgCodeManage)
    {
        return toAjax(pgCodeManageService.insertPgCodeManage(pgCodeManage));
    }

    /**
     * 修改代码管理
     */
    @PreAuthorize("@ss.hasPermi('pg:code:edit')")
    @Log(title = "代码管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PgCodeManage pgCodeManage)
    {
        return toAjax(pgCodeManageService.updatePgCodeManage(pgCodeManage));
    }

    /**
     * 删除代码管理
     */
    @PreAuthorize("@ss.hasPermi('pg:code:remove')")
    @Log(title = "代码管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pgCodeManageService.deletePgCodeManageByIds(ids));
    }
}
