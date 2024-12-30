package com.fno.back.note.service;

import com.fno.back.note.domain.NoteCollect;
import com.fno.back.note.mapper.NoteCollectMapper;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收藏夹Service业务层处理
 * 
 * @author fno
 * @date 2023-04-20
 */
@Service
public class NoteCollectService
{
    @Autowired
    private NoteCollectMapper noteCollectMapper;

    /**
     * 查询收藏夹
     * 
     * @param id 收藏夹主键
     * @return 收藏夹
     */
    public NoteCollect selectNoteCollectById(Long id)
    {
        return noteCollectMapper.selectNoteCollectById(id);
    }

    /**
     * 查询收藏夹列表
     * 
     * @param noteCollect 收藏夹
     * @return 收藏夹
     */
    public List<NoteCollect> selectNoteCollectList(NoteCollect noteCollect)
    {
        return noteCollectMapper.selectNoteCollectList(noteCollect);
    }

    /**
     * 新增收藏夹
     * 
     * @param noteCollect 收藏夹
     * @return 结果
     */
    public int insertNoteCollect(NoteCollect noteCollect)
    {
        noteCollect.setCreateTime(DateUtils.getNowDate());
        return noteCollectMapper.insertNoteCollect(noteCollect);
    }

    /**
     * 修改收藏夹
     * 
     * @param noteCollect 收藏夹
     * @return 结果
     */
    public int updateNoteCollect(NoteCollect noteCollect)
    {
        return noteCollectMapper.updateNoteCollect(noteCollect);
    }

    /**
     * 批量删除收藏夹
     * 
     * @param ids 需要删除的收藏夹主键
     * @return 结果
     */
    public int deleteNoteCollectByIds(Long[] ids)
    {
        return noteCollectMapper.deleteNoteCollectByIds(ids);
    }

    /**
     * 删除收藏夹信息
     * 
     * @param id 收藏夹主键
     * @return 结果
     */
    public int deleteNoteCollectById(Long id)
    {
        return noteCollectMapper.deleteNoteCollectById(id);
    }


    /*****
     * 取消收藏
     * @param docId
     */
    public void cancelCollect(Long docId){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        NoteCollect c = new NoteCollect();
        c.setUserId(user.getUserId());
        c.setDocId(docId);

        List<NoteCollect> list = noteCollectMapper.selectNoteCollectList(c);

        for(NoteCollect collect:list){
            noteCollectMapper.deleteNoteCollectById(collect.getId());
        }

    }
}
