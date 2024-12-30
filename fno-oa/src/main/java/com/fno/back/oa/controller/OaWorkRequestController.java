package com.fno.back.oa.controller;

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
import com.fno.back.oa.domain.OaWorkRequest;
import com.fno.back.oa.service.OaWorkRequestService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 工作请示Controller
 * 
 * @author fno
 * @date 2024-03-04
 */
@RestController
@Api(tags = "工作请示")
@RequestMapping("/oa/workRequest")
public class OaWorkRequestController extends BaseController
{
    @Autowired
    private OaWorkRequestService oaWorkRequestService;

    /**
     * 查询工作请示列表
     */
    @ApiOperation(value = "查询工作请示列表")
    @PreAuthorize("@ss.hasPermi('oa:workRequest:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaWorkRequest oaWorkRequest)
    {
        startPage();
        List<OaWorkRequest> list = oaWorkRequestService.selectOaWorkRequestList(oaWorkRequest);
        return getDataTable(list);
    }

    /**
     * 导出工作请示列表
     */
    @ApiOperation(value = "导出工作请示列表")
    @PreAuthorize("@ss.hasPermi('oa:workRequest:export')")
    @Log(title = "工作请示", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaWorkRequest oaWorkRequest)
    {
        List<OaWorkRequest> list = oaWorkRequestService.selectOaWorkRequestList(oaWorkRequest);
        ExcelUtil<OaWorkRequest> util = new ExcelUtil<OaWorkRequest>(OaWorkRequest.class);
        util.exportExcel(response, list, "工作请示数据");
    }

    /**
     * 获取工作请示详细信息
     */
    @ApiOperation(value = "获取工作请示详细信息")
    @PreAuthorize("@ss.hasPermi('oa:workRequest:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaWorkRequestService.selectOaWorkRequestById(id));
    }

    /**
     * 新增工作请示
     */
    @ApiOperation(value = "新增工作请示")
    @PreAuthorize("@ss.hasPermi('oa:workRequest:add')")
    @Log(title = "工作请示", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaWorkRequest oaWorkRequest)
    {
        oaWorkRequestService.insertOaWorkRequest(oaWorkRequest);
        return success(oaWorkRequest);
    }

    /**
     * 修改工作请示
     */
    @ApiOperation(value = "修改工作请示")
    @PreAuthorize("@ss.hasPermi('oa:workRequest:edit')")
    @Log(title = "工作请示", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaWorkRequest oaWorkRequest)
    {
        oaWorkRequestService.updateOaWorkRequest(oaWorkRequest);
        return success(oaWorkRequest);
    }

    /**
     * 删除工作请示
     */
    @ApiOperation(value = "删除工作请示")
    @PreAuthorize("@ss.hasPermi('oa:workRequest:remove')")
    @Log(title = "工作请示", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaWorkRequestService.deleteOaWorkRequestByIds(ids));
    }
}
