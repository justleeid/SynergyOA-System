package com.fno.back.note.controller;

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
import com.fno.back.note.domain.NoteCollect;
import com.fno.back.note.service.NoteCollectService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 收藏夹Controller
 * 
 * @author fno
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/note/collect")
public class NoteCollectController extends BaseController
{
    @Autowired
    private NoteCollectService noteCollectService;

    /**
     * 查询收藏夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteCollect noteCollect)
    {
        startPage();
        List<NoteCollect> list = noteCollectService.selectNoteCollectList(noteCollect);
        return getDataTable(list);
    }

    /**
     * 导出收藏夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:collect:export')")
    @Log(title = "收藏夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteCollect noteCollect)
    {
        List<NoteCollect> list = noteCollectService.selectNoteCollectList(noteCollect);
        ExcelUtil<NoteCollect> util = new ExcelUtil<NoteCollect>(NoteCollect.class);
        util.exportExcel(response, list, "收藏夹数据");
    }

    /**
     * 获取收藏夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(noteCollectService.selectNoteCollectById(id));
    }

    /**
     * 新增收藏夹
     */
    @PreAuthorize("@ss.hasPermi('note:collect:add')")
    @Log(title = "收藏夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteCollect noteCollect)
    {
        return toAjax(noteCollectService.insertNoteCollect(noteCollect));
    }

    /**
     * 修改收藏夹
     */
    @PreAuthorize("@ss.hasPermi('note:collect:edit')")
    @Log(title = "收藏夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteCollect noteCollect)
    {
        return toAjax(noteCollectService.updateNoteCollect(noteCollect));
    }

    /**
     * 删除收藏夹
     */
    @PreAuthorize("@ss.hasPermi('note:collect:remove')")
    @Log(title = "收藏夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteCollectService.deleteNoteCollectByIds(ids));
    }
}
