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
import com.fno.back.note.domain.NoteDoc;
import com.fno.back.note.service.NoteDocService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 笔记Controller
 * 
 * @author fno
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/note/doc")
public class NoteDocController extends BaseController
{
    @Autowired
    private NoteDocService noteDocService;

    /**
     * 查询笔记列表
     */
    @PreAuthorize("@ss.hasPermi('note:doc:list')")
    @GetMapping("/list")
    public TableDataInfo list(NoteDoc noteDoc)
    {
        startPage();
        List<NoteDoc> list = noteDocService.selectNoteDocList(noteDoc);
        return getDataTable(list);
    }

    /**
     * 导出笔记列表
     */
    @PreAuthorize("@ss.hasPermi('note:doc:export')")
    @Log(title = "笔记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteDoc noteDoc)
    {
        List<NoteDoc> list = noteDocService.selectNoteDocList(noteDoc);
        ExcelUtil<NoteDoc> util = new ExcelUtil<NoteDoc>(NoteDoc.class);
        util.exportExcel(response, list, "笔记数据");
    }

    /**
     * 获取笔记详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:doc:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(noteDocService.selectNoteDocById(id));
    }

    /**
     * 新增笔记
     */
    @PreAuthorize("@ss.hasPermi('note:doc:add')")
    @Log(title = "笔记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteDoc noteDoc)
    {
        return toAjax(noteDocService.insertNoteDoc(noteDoc));
    }

    /**
     * 修改笔记
     */
    @PreAuthorize("@ss.hasPermi('note:doc:edit')")
    @Log(title = "笔记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteDoc noteDoc)
    {
        return toAjax(noteDocService.updateNoteDoc(noteDoc));
    }

    /**
     * 删除笔记
     */
    @PreAuthorize("@ss.hasPermi('note:doc:remove')")
    @Log(title = "笔记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteDocService.deleteNoteDocByIds(ids));
    }
}
