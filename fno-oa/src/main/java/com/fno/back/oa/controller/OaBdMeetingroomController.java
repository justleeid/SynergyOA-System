package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.common.constant.CommonConstants;
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
import com.fno.back.oa.domain.OaBdMeetingroom;
import com.fno.back.oa.service.OaBdMeetingroomService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 会议室管理Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/meetingroom")
public class OaBdMeetingroomController extends BaseController
{
    @Autowired
    private OaBdMeetingroomService oaBdMeetingroomService;

    /**
     * 查询会议室管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroom:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaBdMeetingroom oaBdMeetingroom)
    {
        startPage();
        List<OaBdMeetingroom> list = oaBdMeetingroomService.selectOaBdMeetingroomList(oaBdMeetingroom);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public AjaxResult listAll(OaBdMeetingroom oaBdMeetingroom)
    {
        oaBdMeetingroom.setStatus(CommonConstants.ABLE);
        List<OaBdMeetingroom> list = oaBdMeetingroomService.selectOaBdMeetingroomList(oaBdMeetingroom);
        return success(list);
    }

    /**
     * 导出会议室管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroom:export')")
    @Log(title = "会议室管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaBdMeetingroom oaBdMeetingroom)
    {
        List<OaBdMeetingroom> list = oaBdMeetingroomService.selectOaBdMeetingroomList(oaBdMeetingroom);
        ExcelUtil<OaBdMeetingroom> util = new ExcelUtil<OaBdMeetingroom>(OaBdMeetingroom.class);
        util.exportExcel(response, list, "会议室管理数据");
    }

    /**
     * 获取会议室管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroom:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaBdMeetingroomService.selectOaBdMeetingroomById(id));
    }

    /**
     * 新增会议室管理
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroom:add')")
    @Log(title = "会议室管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaBdMeetingroom oaBdMeetingroom)
    {
        oaBdMeetingroomService.insertOaBdMeetingroom(oaBdMeetingroom);
        return success(oaBdMeetingroom);
    }

    /**
     * 修改会议室管理
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroom:edit')")
    @Log(title = "会议室管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaBdMeetingroom oaBdMeetingroom)
    {
        oaBdMeetingroomService.updateOaBdMeetingroom(oaBdMeetingroom);
        return success(oaBdMeetingroom);
    }

    /**
     * 删除会议室管理
     */
    @PreAuthorize("@ss.hasPermi('oa:meetingroom:remove')")
    @Log(title = "会议室管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaBdMeetingroomService.deleteOaBdMeetingroomByIds(ids));
    }
}
