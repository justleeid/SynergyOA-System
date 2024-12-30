package com.fno.back.common.controller;

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
import com.fno.back.common.domain.SysMessage;
import com.fno.back.common.service.SysMessageService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 站内信Controller
 * 
 * @author fno
 * @date 2023-07-31
 */
@RestController
@RequestMapping("/common/sysMessage")
public class SysMessageController extends BaseController
{
    @Autowired
    private SysMessageService sysMessageService;


    /****
     * 查询首页顶部，未处理消息数量
     * @return
     */
    @GetMapping("/queryTopBarMessageCount")
    public AjaxResult queryTopBarMessageCount(){
        int c = sysMessageService.queryTopBarMessageCount();
        return success(c);
    }




    /**
     * 查询站内信列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysMessage sysMessage)
    {
        startPage();
        List<SysMessage> list = sysMessageService.selectSysMessageList(sysMessage);
        return getDataTable(list);
    }

    /**
     * 导出站内信列表
     */
    @PreAuthorize("@ss.hasPermi('common:sysMessage:export')")
    @Log(title = "站内信", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMessage sysMessage)
    {
        List<SysMessage> list = sysMessageService.selectSysMessageList(sysMessage);
        ExcelUtil<SysMessage> util = new ExcelUtil<SysMessage>(SysMessage.class);
        util.exportExcel(response, list, "站内信数据");
    }

    /**
     * 获取站内信详细信息
     */
    @PreAuthorize("@ss.hasPermi('common:sysMessage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMessageService.selectSysMessageById(id));
    }

    /**
     * 新增站内信
     */
    @PreAuthorize("@ss.hasPermi('common:sysMessage:add')")
    @Log(title = "站内信", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMessage sysMessage)
    {
        return toAjax(sysMessageService.insertSysMessage(sysMessage));
    }

    /**
     * 修改站内信
     */
    @PreAuthorize("@ss.hasPermi('common:sysMessage:edit')")
    @Log(title = "站内信", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMessage sysMessage)
    {
        return toAjax(sysMessageService.updateSysMessage(sysMessage));
    }

    /**
     * 阅读
     */
    @GetMapping("/read/{id}")
    public AjaxResult read(@PathVariable("id") Long id)
    {
        return toAjax(sysMessageService.readSysMessage(id));
    }

    /**
     * 删除站内信
     */
    @PreAuthorize("@ss.hasPermi('common:sysMessage:remove')")
    @Log(title = "站内信", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMessageService.deleteSysMessageByIds(ids));
    }


    /****
     * 全部设置为已读
     * @return
     */
    @PostMapping("/allMessageRead")
    public AjaxResult allMessageRead(){
        sysMessageService.allMessageRead();
        return success();
    }
}
