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
import com.fno.back.oa.domain.OaTicketApply;
import com.fno.back.oa.service.OaTicketApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 车票申请Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/ticketApply")
public class OaTicketApplyController extends BaseController
{
    @Autowired
    private OaTicketApplyService oaTicketApplyService;

    /**
     * 查询车票申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:ticketApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaTicketApply oaTicketApply)
    {
        startPage();
        List<OaTicketApply> list = oaTicketApplyService.selectOaTicketApplyList(oaTicketApply);
        return getDataTable(list);
    }

    /**
     * 导出车票申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:ticketApply:export')")
    @Log(title = "车票申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaTicketApply oaTicketApply)
    {
        List<OaTicketApply> list = oaTicketApplyService.selectOaTicketApplyList(oaTicketApply);
        ExcelUtil<OaTicketApply> util = new ExcelUtil<OaTicketApply>(OaTicketApply.class);
        util.exportExcel(response, list, "车票申请数据");
    }

    /**
     * 获取车票申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:ticketApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaTicketApplyService.selectOaTicketApplyById(id));
    }

    /**
     * 新增车票申请
     */
    @PreAuthorize("@ss.hasPermi('oa:ticketApply:add')")
    @Log(title = "车票申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaTicketApply oaTicketApply)
    {
        oaTicketApplyService.insertOaTicketApply(oaTicketApply);
        return success(oaTicketApply);
    }

    /**
     * 修改车票申请
     */
    @PreAuthorize("@ss.hasPermi('oa:ticketApply:edit')")
    @Log(title = "车票申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaTicketApply oaTicketApply)
    {
        oaTicketApplyService.updateOaTicketApply(oaTicketApply);
        return success(oaTicketApply);
    }

    /**
     * 删除车票申请
     */
    @PreAuthorize("@ss.hasPermi('oa:ticketApply:remove')")
    @Log(title = "车票申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaTicketApplyService.deleteOaTicketApplyByIds(ids));
    }
}
