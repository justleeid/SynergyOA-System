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
import com.fno.back.oa.domain.OaSchedule;
import com.fno.back.oa.service.OaScheduleService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 日程管理Controller
 * 
 * @author fno
 * @date 2023-09-25
 */
@RestController
@RequestMapping("/oa/oaSchedule")
public class OaScheduleController extends BaseController
{
    @Autowired
    private OaScheduleService oaScheduleService;

    /**
     * 查询日程管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaSchedule oaSchedule)
    {
        startPage();
        List<OaSchedule> list = oaScheduleService.selectOaScheduleList(oaSchedule);
        return getDataTable(list);
    }

    /**
     * 查询日程管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(OaSchedule oaSchedule)
    {
        List<OaSchedule> list = oaScheduleService.selectOaScheduleList(oaSchedule);
        return success(list);
    }

    /**
     * 导出日程管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:export')")
    @Log(title = "日程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaSchedule oaSchedule)
    {
        List<OaSchedule> list = oaScheduleService.selectOaScheduleList(oaSchedule);
        ExcelUtil<OaSchedule> util = new ExcelUtil<OaSchedule>(OaSchedule.class);
        util.exportExcel(response, list, "日程管理数据");
    }

    /**
     * 获取日程管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaScheduleService.selectOaScheduleById(id));
    }

    /**
     * 新增日程管理
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:add')")
    @Log(title = "日程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaSchedule oaSchedule)
    {
        oaScheduleService.insertOaSchedule(oaSchedule);
        return success(oaSchedule);
    }

    /**
     * 修改日程管理
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:edit')")
    @Log(title = "日程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaSchedule oaSchedule)
    {
        oaScheduleService.updateOaSchedule(oaSchedule);
        return success(oaSchedule);
    }

    /**
     * 删除日程管理
     */
    @PreAuthorize("@ss.hasPermi('oa:oaSchedule:remove')")
    @Log(title = "日程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaScheduleService.deleteOaScheduleByIds(ids));
    }
}
