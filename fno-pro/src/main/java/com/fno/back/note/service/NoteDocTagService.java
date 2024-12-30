package com.fno.back.note.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.note.mapper.NoteDocTagMapper;
import com.fno.back.note.domain.NoteDocTag;

/**
 * 笔记标签关系Service业务层处理
 * 
 * @author fno
 * @date 2023-04-20
 */
@Service
public class NoteDocTagService
{
    @Autowired
    private NoteDocTagMapper noteDocTagMapper;

    /**
     * 查询笔记标签关系
     * 
     * @param id 笔记标签关系主键
     * @return 笔记标签关系
     */
    public NoteDocTag selectNoteDocTagById(Long id)
    {
        return noteDocTagMapper.selectNoteDocTagById(id);
    }

    /**
     * 查询笔记标签关系列表
     * 
     * @param noteDocTag 笔记标签关系
     * @return 笔记标签关系
     */
    public List<NoteDocTag> selectNoteDocTagList(NoteDocTag noteDocTag)
    {
        return noteDocTagMapper.selectNoteDocTagList(noteDocTag);
    }

    /**
     * 新增笔记标签关系
     * 
     * @param noteDocTag 笔记标签关系
     * @return 结果
     */
    public int insertNoteDocTag(NoteDocTag noteDocTag)
    {
        return noteDocTagMapper.insertNoteDocTag(noteDocTag);
    }

    /**
     * 修改笔记标签关系
     * 
     * @param noteDocTag 笔记标签关系
     * @return 结果
     */
    public int updateNoteDocTag(NoteDocTag noteDocTag)
    {
        return noteDocTagMapper.updateNoteDocTag(noteDocTag);
    }

    /**
     * 批量删除笔记标签关系
     * 
     * @param ids 需要删除的笔记标签关系主键
     * @return 结果
     */
    public int deleteNoteDocTagByIds(Long[] ids)
    {
        return noteDocTagMapper.deleteNoteDocTagByIds(ids);
    }

    /**
     * 删除笔记标签关系信息
     * 
     * @param id 笔记标签关系主键
     * @return 结果
     */
    public int deleteNoteDocTagById(Long id)
    {
        return noteDocTagMapper.deleteNoteDocTagById(id);
    }
}
