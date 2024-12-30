package com.fno.back.note.mapper;

import java.util.List;
import com.fno.back.note.domain.NoteDocTag;

/**
 * 笔记标签关系Mapper接口
 * 
 * @author fno
 * @date 2023-04-20
 */
public interface NoteDocTagMapper 
{
    /**
     * 查询笔记标签关系
     * 
     * @param id 笔记标签关系主键
     * @return 笔记标签关系
     */
    public NoteDocTag selectNoteDocTagById(Long id);

    /**
     * 查询笔记标签关系列表
     * 
     * @param noteDocTag 笔记标签关系
     * @return 笔记标签关系集合
     */
    public List<NoteDocTag> selectNoteDocTagList(NoteDocTag noteDocTag);

    /**
     * 新增笔记标签关系
     * 
     * @param noteDocTag 笔记标签关系
     * @return 结果
     */
    public int insertNoteDocTag(NoteDocTag noteDocTag);

    /**
     * 修改笔记标签关系
     * 
     * @param noteDocTag 笔记标签关系
     * @return 结果
     */
    public int updateNoteDocTag(NoteDocTag noteDocTag);

    /**
     * 删除笔记标签关系
     * 
     * @param id 笔记标签关系主键
     * @return 结果
     */
    public int deleteNoteDocTagById(Long id);

    /**
     * 批量删除笔记标签关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteDocTagByIds(Long[] ids);
}
