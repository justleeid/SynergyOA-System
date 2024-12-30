package com.fno.back.note.mapper;

import java.util.List;
import com.fno.back.note.domain.NoteDoc;
import org.apache.ibatis.annotations.Param;

/**
 * 笔记Mapper接口
 * 
 * @author fno
 * @date 2023-04-20
 */
public interface NoteDocMapper 
{
    /**
     * 查询笔记
     * 
     * @param id 笔记主键
     * @return 笔记
     */
    public NoteDoc selectNoteDocById(Long id);

    /**
     * 查询笔记列表
     * 
     * @param noteDoc 笔记
     * @return 笔记集合
     */
    public List<NoteDoc> selectNoteDocList(NoteDoc noteDoc);


    /****
     * 获取我的文件，绑定收藏
     * @param userId
     * @param folderId
     * @return
     */
    public List<NoteDoc> selectNoteDocListAndCollect(@Param("userId") Long userId,@Param("folderId") Long folderId);


    /**
     * 新增笔记
     * 
     * @param noteDoc 笔记
     * @return 结果
     */
    public int insertNoteDoc(NoteDoc noteDoc);

    /**
     * 修改笔记
     * 
     * @param noteDoc 笔记
     * @return 结果
     */
    public int updateNoteDoc(NoteDoc noteDoc);

    /**
     * 删除笔记
     * 
     * @param id 笔记主键
     * @return 结果
     */
    public int deleteNoteDocById(Long id);

    /**
     * 批量删除笔记
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteDocByIds(Long[] ids);


    /*****
     * 获取我的收藏
     * @param userId
     * @return
     */
    public List<NoteDoc> getMyCollect(Long userId);
}
