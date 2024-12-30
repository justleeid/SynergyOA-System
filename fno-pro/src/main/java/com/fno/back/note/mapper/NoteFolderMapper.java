package com.fno.back.note.mapper;

import java.util.List;
import com.fno.back.note.domain.NoteFolder;

/**
 * 文件夹Mapper接口
 * 
 * @author fno
 * @date 2023-04-20
 */
public interface NoteFolderMapper 
{
    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    public NoteFolder selectNoteFolderById(Long id);

    /**
     * 查询文件夹列表
     * 
     * @param noteFolder 文件夹
     * @return 文件夹集合
     */
    public List<NoteFolder> selectNoteFolderList(NoteFolder noteFolder);

    /**
     * 新增文件夹
     * 
     * @param noteFolder 文件夹
     * @return 结果
     */
    public int insertNoteFolder(NoteFolder noteFolder);

    /**
     * 修改文件夹
     * 
     * @param noteFolder 文件夹
     * @return 结果
     */
    public int updateNoteFolder(NoteFolder noteFolder);

    /**
     * 删除文件夹
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteNoteFolderById(Long id);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteFolderByIds(Long[] ids);
}
