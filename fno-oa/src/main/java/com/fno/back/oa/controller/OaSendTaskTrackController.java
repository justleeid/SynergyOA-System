package com.fno.back.oa.controller;

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
import com.fno.back.oa.domain.OaSendTaskTrack;
import com.fno.back.oa.service.OaSendTaskTrackService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 任务跟踪Controller
 * 
 * @author fno
 * @date 2023-08-20
 */
@RestController
@RequestMapping("/oa/sendTaskTrack")
public class OaSendTaskTrackController extends BaseController
{
    @Autowired
    private OaSendTaskTrackService oaSendTaskTrackService;

    /**
     * 查询任务跟踪列表
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaSendTaskTrack oaSendTaskTrack)
    {
        startPage();
        List<OaSendTaskTrack> list = oaSendTaskTrackService.selectOaSendTaskTrackList(oaSendTaskTrack);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(OaSendTaskTrack oaSendTaskTrack)
    {
        List<OaSendTaskTrack> list = oaSendTaskTrackService.selectOaSendTaskTrackList(oaSendTaskTrack);
        return success(list);
    }

    /**
     * 导出任务跟踪列表
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:export')")
    @Log(title = "任务跟踪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaSendTaskTrack oaSendTaskTrack)
    {
        List<OaSendTaskTrack> list = oaSendTaskTrackService.selectOaSendTaskTrackList(oaSendTaskTrack);
        ExcelUtil<OaSendTaskTrack> util = new ExcelUtil<OaSendTaskTrack>(OaSendTaskTrack.class);
        util.exportExcel(response, list, "任务跟踪数据");
    }

    /**
     * 获取任务跟踪详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaSendTaskTrackService.selectOaSendTaskTrackById(id));
    }

    /**
     * 新增任务跟踪
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:add')")
    @Log(title = "任务跟踪", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaSendTaskTrack oaSendTaskTrack)
    {
        return toAjax(oaSendTaskTrackService.insertOaSendTaskTrack(oaSendTaskTrack));
    }

    /**
     * 修改任务跟踪
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:edit')")
    @Log(title = "任务跟踪", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaSendTaskTrack oaSendTaskTrack)
    {
        return toAjax(oaSendTaskTrackService.updateOaSendTaskTrack(oaSendTaskTrack));
    }

    /**
     * 删除任务跟踪
     */
    @PreAuthorize("@ss.hasPermi('oa:sendTaskTrack:remove')")
    @Log(title = "任务跟踪", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaSendTaskTrackService.deleteOaSendTaskTrackByIds(ids));
    }
}
