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
import com.fno.back.oa.domain.OaHotelApply;
import com.fno.back.oa.service.OaHotelApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 酒店申请Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/hotelApply")
public class OaHotelApplyController extends BaseController
{
    @Autowired
    private OaHotelApplyService oaHotelApplyService;

    /**
     * 查询酒店申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:hotelApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaHotelApply oaHotelApply)
    {
        startPage();
        List<OaHotelApply> list = oaHotelApplyService.selectOaHotelApplyList(oaHotelApply);
        return getDataTable(list);
    }

    /**
     * 导出酒店申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:hotelApply:export')")
    @Log(title = "酒店申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaHotelApply oaHotelApply)
    {
        List<OaHotelApply> list = oaHotelApplyService.selectOaHotelApplyList(oaHotelApply);
        ExcelUtil<OaHotelApply> util = new ExcelUtil<OaHotelApply>(OaHotelApply.class);
        util.exportExcel(response, list, "酒店申请数据");
    }

    /**
     * 获取酒店申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:hotelApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaHotelApplyService.selectOaHotelApplyById(id));
    }

    /**
     * 新增酒店申请
     */
    @PreAuthorize("@ss.hasPermi('oa:hotelApply:add')")
    @Log(title = "酒店申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaHotelApply oaHotelApply)
    {
        oaHotelApplyService.insertOaHotelApply(oaHotelApply);
        return success(oaHotelApply);
    }

    /**
     * 修改酒店申请
     */
    @PreAuthorize("@ss.hasPermi('oa:hotelApply:edit')")
    @Log(title = "酒店申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaHotelApply oaHotelApply)
    {
        oaHotelApplyService.updateOaHotelApply(oaHotelApply);
        return success(oaHotelApply);
    }

    /**
     * 删除酒店申请
     */
    @PreAuthorize("@ss.hasPermi('oa:hotelApply:remove')")
    @Log(title = "酒店申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaHotelApplyService.deleteOaHotelApplyByIds(ids));
    }
}
