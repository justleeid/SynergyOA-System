package com.fno.back.note.mapper;

import java.util.List;
import com.fno.back.note.domain.NoteTag;

/**
 * 标签Mapper接口
 * 
 * @author fno
 * @date 2023-04-20
 */
public interface NoteTagMapper 
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public NoteTag selectNoteTagById(Long id);

    /**
     * 查询标签列表
     * 
     * @param noteTag 标签
     * @return 标签集合
     */
    public List<NoteTag> selectNoteTagList(NoteTag noteTag);

    /**
     * 新增标签
     * 
     * @param noteTag 标签
     * @return 结果
     */
    public int insertNoteTag(NoteTag noteTag);

    /**
     * 修改标签
     * 
     * @param noteTag 标签
     * @return 结果
     */
    public int updateNoteTag(NoteTag noteTag);

    /**
     * 删除标签
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteNoteTagById(Long id);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteTagByIds(Long[] ids);
}
