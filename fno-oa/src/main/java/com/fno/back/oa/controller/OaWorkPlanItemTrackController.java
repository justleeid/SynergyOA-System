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
import com.fno.back.oa.domain.OaWorkPlanItemTrack;
import com.fno.back.oa.service.OaWorkPlanItemTrackService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 进度跟踪Controller
 * 
 * @author fno
 * @date 2023-08-18
 */
@RestController
@RequestMapping("/oa/workPlanItemTrack")
public class OaWorkPlanItemTrackController extends BaseController
{
    @Autowired
    private OaWorkPlanItemTrackService oaWorkPlanItemTrackService;

    /**
     * 查询进度跟踪列表
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        startPage();
        List<OaWorkPlanItemTrack> list = oaWorkPlanItemTrackService.selectOaWorkPlanItemTrackList(oaWorkPlanItemTrack);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:list')")
    @GetMapping("/all")
    public AjaxResult listAll(OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        List<OaWorkPlanItemTrack> list = oaWorkPlanItemTrackService.selectOaWorkPlanItemTrackList(oaWorkPlanItemTrack);
        return success(list);
    }

    /**
     * 导出进度跟踪列表
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:export')")
    @Log(title = "进度跟踪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        List<OaWorkPlanItemTrack> list = oaWorkPlanItemTrackService.selectOaWorkPlanItemTrackList(oaWorkPlanItemTrack);
        ExcelUtil<OaWorkPlanItemTrack> util = new ExcelUtil<OaWorkPlanItemTrack>(OaWorkPlanItemTrack.class);
        util.exportExcel(response, list, "进度跟踪数据");
    }

    /**
     * 获取进度跟踪详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaWorkPlanItemTrackService.selectOaWorkPlanItemTrackById(id));
    }

    /**
     * 新增进度跟踪
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:add')")
    @Log(title = "进度跟踪", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        return toAjax(oaWorkPlanItemTrackService.insertOaWorkPlanItemTrack(oaWorkPlanItemTrack));
    }

    /**
     * 修改进度跟踪
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:edit')")
    @Log(title = "进度跟踪", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaWorkPlanItemTrack oaWorkPlanItemTrack)
    {
        return toAjax(oaWorkPlanItemTrackService.updateOaWorkPlanItemTrack(oaWorkPlanItemTrack));
    }

    /**
     * 删除进度跟踪
     */
    @PreAuthorize("@ss.hasPermi('oa:workPlanItemTrack:remove')")
    @Log(title = "进度跟踪", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaWorkPlanItemTrackService.deleteOaWorkPlanItemTrackByIds(ids));
    }
}
