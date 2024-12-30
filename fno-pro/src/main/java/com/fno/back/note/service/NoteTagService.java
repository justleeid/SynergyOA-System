package com.fno.back.note.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.note.mapper.NoteTagMapper;
import com.fno.back.note.domain.NoteTag;

/**
 * 标签Service业务层处理
 * 
 * @author fno
 * @date 2023-04-20
 */
@Service
public class NoteTagService
{
    @Autowired
    private NoteTagMapper noteTagMapper;

    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public NoteTag selectNoteTagById(Long id)
    {
        return noteTagMapper.selectNoteTagById(id);
    }

    /**
     * 查询标签列表
     * 
     * @param noteTag 标签
     * @return 标签
     */
    public List<NoteTag> selectNoteTagList(NoteTag noteTag)
    {
        return noteTagMapper.selectNoteTagList(noteTag);
    }

    /**
     * 新增标签
     * 
     * @param noteTag 标签
     * @return 结果
     */
    public int insertNoteTag(NoteTag noteTag)
    {
        noteTag.setCreateTime(DateUtils.getNowDate());
        return noteTagMapper.insertNoteTag(noteTag);
    }

    /**
     * 修改标签
     * 
     * @param noteTag 标签
     * @return 结果
     */
    public int updateNoteTag(NoteTag noteTag)
    {
        return noteTagMapper.updateNoteTag(noteTag);
    }

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    public int deleteNoteTagByIds(Long[] ids)
    {
        return noteTagMapper.deleteNoteTagByIds(ids);
    }

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteNoteTagById(Long id)
    {
        return noteTagMapper.deleteNoteTagById(id);
    }
}
