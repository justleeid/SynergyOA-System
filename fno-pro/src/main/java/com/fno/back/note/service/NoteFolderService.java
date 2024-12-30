package com.fno.back.note.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.note.mapper.NoteFolderMapper;
import com.fno.back.note.domain.NoteFolder;

/**
 * 文件夹Service业务层处理
 * 
 * @author fno
 * @date 2023-04-20
 */
@Service
public class NoteFolderService
{
    @Autowired
    private NoteFolderMapper noteFolderMapper;

    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    public NoteFolder selectNoteFolderById(Long id)
    {
        return noteFolderMapper.selectNoteFolderById(id);
    }

    /**
     * 查询文件夹列表
     * 
     * @param noteFolder 文件夹
     * @return 文件夹
     */
    public List<NoteFolder> selectNoteFolderList(NoteFolder noteFolder)
    {
        return noteFolderMapper.selectNoteFolderList(noteFolder);
    }

    /**
     * 新增文件夹
     * 
     * @param noteFolder 文件夹
     * @return 结果
     */
    public int insertNoteFolder(NoteFolder noteFolder)
    {
        noteFolder.setCreateTime(DateUtils.getNowDate());
        return noteFolderMapper.insertNoteFolder(noteFolder);
    }

    /**
     * 修改文件夹
     * 
     * @param noteFolder 文件夹
     * @return 结果
     */
    public int updateNoteFolder(NoteFolder noteFolder)
    {
        return noteFolderMapper.updateNoteFolder(noteFolder);
    }

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的文件夹主键
     * @return 结果
     */
    public int deleteNoteFolderByIds(Long[] ids)
    {
        return noteFolderMapper.deleteNoteFolderByIds(ids);
    }

    /**
     * 删除文件夹信息
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteNoteFolderById(Long id)
    {
        return noteFolderMapper.deleteNoteFolderById(id);
    }
}
