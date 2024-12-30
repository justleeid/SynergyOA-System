package com.fno.back.cam.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.cam.domain.CamMessageNotice;
import com.fno.back.cam.service.CamMessageNoticeService;
import com.fno.common.utils.poi.ExcelUtil;
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
import com.fno.common.core.page.TableDataInfo;

/**
 * 视频监控提醒Controller
 * 
 * @author fno
 * @date 2023-04-08
 */
@RestController
@RequestMapping("/cam/notice")
public class CamMessageNoticeController extends BaseController
{
    @Autowired
    private CamMessageNoticeService camMessageNoticeService;

    /**
     * 查询视频监控提醒列表
     */
    @PreAuthorize("@ss.hasPermi('cam:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CamMessageNotice camMessageNotice)
    {
        startPage();
        List<CamMessageNotice> list = camMessageNoticeService.selectCamMessageNoticeList(camMessageNotice);
        return getDataTable(list);
    }

    /**
     * 导出视频监控提醒列表
     */
    @PreAuthorize("@ss.hasPermi('cam:notice:export')")
    @Log(title = "视频监控提醒", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CamMessageNotice camMessageNotice)
    {
        List<CamMessageNotice> list = camMessageNoticeService.selectCamMessageNoticeList(camMessageNotice);
        ExcelUtil<CamMessageNotice> util = new ExcelUtil<CamMessageNotice>(CamMessageNotice.class);
        util.exportExcel(response, list, "视频监控提醒数据");
    }

    /**
     * 获取视频监控提醒详细信息
     */
    @PreAuthorize("@ss.hasPermi('cam:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(camMessageNoticeService.selectCamMessageNoticeById(id));
    }

    /**
     * 新增视频监控提醒
     */
    @PreAuthorize("@ss.hasPermi('cam:notice:add')")
    @Log(title = "视频监控提醒", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CamMessageNotice camMessageNotice)
    {
        return toAjax(camMessageNoticeService.insertCamMessageNotice(camMessageNotice));
    }

    /**
     * 修改视频监控提醒
     */
    @PreAuthorize("@ss.hasPermi('cam:notice:edit')")
    @Log(title = "视频监控提醒", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CamMessageNotice camMessageNotice)
    {
        return toAjax(camMessageNoticeService.updateCamMessageNotice(camMessageNotice));
    }

    /**
     * 删除视频监控提醒
     */
    @PreAuthorize("@ss.hasPermi('cam:notice:remove')")
    @Log(title = "视频监控提醒", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(camMessageNoticeService.deleteCamMessageNoticeByIds(ids));
    }
}
