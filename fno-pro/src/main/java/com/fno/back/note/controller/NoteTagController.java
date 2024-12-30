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
import com.fno.back.note.domain.NoteTag;
import com.fno.back.note.service.NoteTagService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 标签Controller
 * 
 * @author fno
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/note/tag")
public class NoteTagController extends BaseController
{
    @Autowired
    private NoteTagService noteTagService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("@ss.hasPermi('note:tag:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteTag noteTag)
    {
        startPage();
        List<NoteTag> list = noteTagService.selectNoteTagList(noteTag);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    @PreAuthorize("@ss.hasPermi('note:tag:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteTag noteTag)
    {
        List<NoteTag> list = noteTagService.selectNoteTagList(noteTag);
        ExcelUtil<NoteTag> util = new ExcelUtil<NoteTag>(NoteTag.class);
        util.exportExcel(response, list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:tag:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(noteTagService.selectNoteTagById(id));
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('note:tag:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteTag noteTag)
    {
        return toAjax(noteTagService.insertNoteTag(noteTag));
    }

    /**
     * 修改标签
     */
    @PreAuthorize("@ss.hasPermi('note:tag:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteTag noteTag)
    {
        return toAjax(noteTagService.updateNoteTag(noteTag));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('note:tag:remove')")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteTagService.deleteNoteTagByIds(ids));
    }
}
