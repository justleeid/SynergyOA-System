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
import com.fno.back.t.domain.TEnterprise;
import com.fno.back.t.service.TEnterpriseService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 企业Controller
 * 
 * @author fno
 * @date 2024-02-27
 */
@RestController
@Api(tags = "企业")
@RequestMapping("/t/enterprise")
public class TEnterpriseController extends BaseController
{
    @Autowired
    private TEnterpriseService tEnterpriseService;

    /**
     * 查询企业列表
     */
    @ApiOperation(value = "查询企业列表")
    @PreAuthorize("@ss.hasPermi('t:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEnterprise tEnterprise)
    {
        startPage();
        List<TEnterprise> list = tEnterpriseService.selectTEnterpriseList(tEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出企业列表
     */
    @ApiOperation(value = "导出企业列表")
    @PreAuthorize("@ss.hasPermi('t:enterprise:export')")
    @Log(title = "企业", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TEnterprise tEnterprise)
    {
        List<TEnterprise> list = tEnterpriseService.selectTEnterpriseList(tEnterprise);
        ExcelUtil<TEnterprise> util = new ExcelUtil<TEnterprise>(TEnterprise.class);
        util.exportExcel(response, list, "企业数据");
    }

    /**
     * 获取企业详细信息
     */
    @ApiOperation(value = "获取企业详细信息")
    @PreAuthorize("@ss.hasPermi('t:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tEnterpriseService.selectTEnterpriseById(id));
    }

    /**
     * 新增企业
     */
    @ApiOperation(value = "新增企业")
    @PreAuthorize("@ss.hasPermi('t:enterprise:add')")
    @Log(title = "企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TEnterprise tEnterprise)
    {
        tEnterpriseService.insertTEnterprise(tEnterprise);
        return success(tEnterprise);
    }

    /**
     * 修改企业
     */
    @ApiOperation(value = "修改企业")
    @PreAuthorize("@ss.hasPermi('t:enterprise:edit')")
    @Log(title = "企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TEnterprise tEnterprise)
    {
        tEnterpriseService.updateTEnterprise(tEnterprise);
        return success(tEnterprise);
    }

    /**
     * 删除企业
     */
    @ApiOperation(value = "删除企业")
    @PreAuthorize("@ss.hasPermi('t:enterprise:remove')")
    @Log(title = "企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tEnterpriseService.deleteTEnterpriseByIds(ids));
    }


    /****
     * 查询所有的年份
     * @return
     */
    @ApiOperation(value = "报表查询所有的年份")
    @PostMapping("/getYear")
    public AjaxResult getYear()
    {
        return success(tEnterpriseService.getYear());
    }


    /*****
     * 报表查询数据
     * @return
     */
    @ApiOperation(value = "报表查询所有的数据")
    @PostMapping("/getData")
    public AjaxResult getData()
    {
        return success(tEnterpriseService.getData());
    }

    @ApiOperation(value = "报表查询所有的数据")
    @PostMapping("/getPTData")
    public AjaxResult getPTData()
    {
        return success(tEnterpriseService.getPTData());
    }


    @ApiOperation(value = "报表查询所有的数据")
    @PostMapping("/getPlaceData")
    public AjaxResult getPlaceData()
    {
        return success(tEnterpriseService.getPlaceData());
    }
}
