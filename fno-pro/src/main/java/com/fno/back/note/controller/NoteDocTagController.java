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
import com.fno.back.note.domain.NoteDocTag;
import com.fno.back.note.service.NoteDocTagService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 笔记标签关系Controller
 * 
 * @author fno
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/note/docTag")
public class NoteDocTagController extends BaseController
{
    @Autowired
    private NoteDocTagService noteDocTagService;

    /**
     * 查询笔记标签关系列表
     */
    @PreAuthorize("@ss.hasPermi('note:docTag:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteDocTag noteDocTag)
    {
        startPage();
        List<NoteDocTag> list = noteDocTagService.selectNoteDocTagList(noteDocTag);
        return getDataTable(list);
    }

    /**
     * 导出笔记标签关系列表
     */
    @PreAuthorize("@ss.hasPermi('note:docTag:export')")
    @Log(title = "笔记标签关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteDocTag noteDocTag)
    {
        List<NoteDocTag> list = noteDocTagService.selectNoteDocTagList(noteDocTag);
        ExcelUtil<NoteDocTag> util = new ExcelUtil<NoteDocTag>(NoteDocTag.class);
        util.exportExcel(response, list, "笔记标签关系数据");
    }

    /**
     * 获取笔记标签关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:docTag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(noteDocTagService.selectNoteDocTagById(id));
    }

    /**
     * 新增笔记标签关系
     */
    @PreAuthorize("@ss.hasPermi('note:docTag:add')")
    @Log(title = "笔记标签关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteDocTag noteDocTag)
    {
        return toAjax(noteDocTagService.insertNoteDocTag(noteDocTag));
    }

    /**
     * 修改笔记标签关系
     */
    @PreAuthorize("@ss.hasPermi('note:docTag:edit')")
    @Log(title = "笔记标签关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteDocTag noteDocTag)
    {
        return toAjax(noteDocTagService.updateNoteDocTag(noteDocTag));
    }

    /**
     * 删除笔记标签关系
     */
    @PreAuthorize("@ss.hasPermi('note:docTag:remove')")
    @Log(title = "笔记标签关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteDocTagService.deleteNoteDocTagByIds(ids));
    }
}
