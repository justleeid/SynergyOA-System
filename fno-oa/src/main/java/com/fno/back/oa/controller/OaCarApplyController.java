package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.common.utils.SecurityUtils;
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
import com.fno.back.oa.domain.OaCarApply;
import com.fno.back.oa.service.OaCarApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 车辆申请Controller
 * 
 * @author fno
 * @date 2023-06-05
 */
@RestController
@RequestMapping("/oa/carApply")
public class OaCarApplyController extends BaseController
{
    @Autowired
    private OaCarApplyService oaCarApplyService;

    /**
     * 查询车辆申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:carApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaCarApply oaCarApply)
    {
        startPage();
        List<OaCarApply> list = oaCarApplyService.selectOaCarApplyList(oaCarApply);
        return getDataTable(list);
    }

    /**
     * 导出车辆申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:carApply:export')")
    @Log(title = "车辆申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaCarApply oaCarApply)
    {
        List<OaCarApply> list = oaCarApplyService.selectOaCarApplyList(oaCarApply);
        ExcelUtil<OaCarApply> util = new ExcelUtil<OaCarApply>(OaCarApply.class);
        util.exportExcel(response, list, "车辆申请数据");
    }

    /**
     * 获取车辆申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:carApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaCarApplyService.selectOaCarApplyById(id));
    }

    /**
     * 新增车辆申请
     */
    @PreAuthorize("@ss.hasPermi('oa:carApply:add')")
    @Log(title = "车辆申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaCarApply oaCarApply)
    {
        oaCarApplyService.insertOaCarApply(oaCarApply);
        return success(oaCarApply);
    }

    /**
     * 修改车辆申请
     */
    @PreAuthorize("@ss.hasPermi('oa:carApply:edit')")
    @Log(title = "车辆申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaCarApply oaCarApply)
    {
        oaCarApplyService.updateOaCarApply(oaCarApply);
        return success(oaCarApply);
    }

    /**
     * 删除车辆申请
     */
    @PreAuthorize("@ss.hasPermi('oa:carApply:remove')")
    @Log(title = "车辆申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaCarApplyService.deleteOaCarApplyByIds(ids));
    }
}
