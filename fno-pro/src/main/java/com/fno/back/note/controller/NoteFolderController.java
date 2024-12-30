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
import com.fno.back.note.domain.NoteFolder;
import com.fno.back.note.service.NoteFolderService;
import com.fno.common.utils.poi.ExcelUtil;

/**
 * 文件夹Controller
 * 
 * @author fno
 * @date 2023-04-20
 */
@RestController
@RequestMapping("/note/folder")
public class NoteFolderController extends BaseController
{
    @Autowired
    private NoteFolderService noteFolderService;

    /**
     * 查询文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:folder:list')")
    @GetMapping("/list")
    public AjaxResult list(NoteFolder noteFolder)
    {
        List<NoteFolder> list = noteFolderService.selectNoteFolderList(noteFolder);
        return AjaxResult.success(list);
    }

    /**
     * 导出文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('note:folder:export')")
    @Log(title = "文件夹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NoteFolder noteFolder)
    {
        List<NoteFolder> list = noteFolderService.selectNoteFolderList(noteFolder);
        ExcelUtil<NoteFolder> util = new ExcelUtil<NoteFolder>(NoteFolder.class);
        util.exportExcel(response, list, "文件夹数据");
    }

    /**
     * 获取文件夹详细信息
     */
    @PreAuthorize("@ss.hasPermi('note:folder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(noteFolderService.selectNoteFolderById(id));
    }

    /**
     * 新增文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:folder:add')")
    @Log(title = "文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NoteFolder noteFolder)
    {
        return toAjax(noteFolderService.insertNoteFolder(noteFolder));
    }

    /**
     * 修改文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:folder:edit')")
    @Log(title = "文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NoteFolder noteFolder)
    {
        return toAjax(noteFolderService.updateNoteFolder(noteFolder));
    }

    /**
     * 删除文件夹
     */
    @PreAuthorize("@ss.hasPermi('note:folder:remove')")
    @Log(title = "文件夹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(noteFolderService.deleteNoteFolderByIds(ids));
    }
}
