package com.fno.back.note.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.note.mapper.NoteDocMapper;
import com.fno.back.note.domain.NoteDoc;

/**
 * 笔记Service业务层处理
 * 
 * @author fno
 * @date 2023-04-20
 */
@Service
public class NoteDocService
{
    @Autowired
    private NoteDocMapper noteDocMapper;

    /**
     * 查询笔记
     * 
     * @param id 笔记主键
     * @return 笔记
     */
    public NoteDoc selectNoteDocById(Long id)
    {
        return noteDocMapper.selectNoteDocById(id);
    }

    /**
     * 查询笔记列表
     * 
     * @param noteDoc 笔记
     * @return 笔记
     */
    public List<NoteDoc> selectNoteDocList(NoteDoc noteDoc)
    {
        return noteDocMapper.selectNoteDocList(noteDoc);
    }


    /*****
     * 查询我的文件，绑定是否收藏
     * @return
     */
    public List<NoteDoc> selectNoteDocListAndCollect(Long userId,Long folderId){
        return noteDocMapper.selectNoteDocListAndCollect(userId,folderId);
    }



    /**
     * 新增笔记
     * 
     * @param noteDoc 笔记
     * @return 结果
     */
    public int insertNoteDoc(NoteDoc noteDoc)
    {
        noteDoc.setCreateTime(DateUtils.getNowDate());
        return noteDocMapper.insertNoteDoc(noteDoc);
    }

    /**
     * 修改笔记
     * 
     * @param noteDoc 笔记
     * @return 结果
     */
    public int updateNoteDoc(NoteDoc noteDoc)
    {
        return noteDocMapper.updateNoteDoc(noteDoc);
    }

    /**
     * 批量删除笔记
     * 
     * @param ids 需要删除的笔记主键
     * @return 结果
     */
    public int deleteNoteDocByIds(Long[] ids)
    {
        return noteDocMapper.deleteNoteDocByIds(ids);
    }

    /**
     * 删除笔记信息
     * 
     * @param id 笔记主键
     * @return 结果
     */
    public int deleteNoteDocById(Long id)
    {
        return noteDocMapper.deleteNoteDocById(id);
    }


    /****
     * 查询我的收藏
     * @param id
     * @return
     */
    public List<NoteDoc> getMyCollect(Long userId){
        return noteDocMapper.getMyCollect(userId);
    }
}
