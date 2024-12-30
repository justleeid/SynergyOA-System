package com.fno.back.note.mapper;

import java.util.List;
import com.fno.back.note.domain.NoteCollect;

/**
 * 收藏夹Mapper接口
 * 
 * @author fno
 * @date 2023-04-20
 */
public interface NoteCollectMapper 
{
    /**
     * 查询收藏夹
     * 
     * @param id 收藏夹主键
     * @return 收藏夹
     */
    public NoteCollect selectNoteCollectById(Long id);

    /**
     * 查询收藏夹列表
     * 
     * @param noteCollect 收藏夹
     * @return 收藏夹集合
     */
    public List<NoteCollect> selectNoteCollectList(NoteCollect noteCollect);

    /**
     * 新增收藏夹
     * 
     * @param noteCollect 收藏夹
     * @return 结果
     */
    public int insertNoteCollect(NoteCollect noteCollect);

    /**
     * 修改收藏夹
     * 
     * @param noteCollect 收藏夹
     * @return 结果
     */
    public int updateNoteCollect(NoteCollect noteCollect);

    /**
     * 删除收藏夹
     * 
     * @param id 收藏夹主键
     * @return 结果
     */
    public int deleteNoteCollectById(Long id);

    /**
     * 批量删除收藏夹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteCollectByIds(Long[] ids);
}
