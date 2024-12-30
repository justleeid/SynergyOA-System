package com.fno.api.note.controller;

import cn.hutool.core.util.StrUtil;
import com.fno.back.common.constant.CommonConstants;
import com.fno.back.note.domain.NoteCollect;
import com.fno.back.note.domain.NoteDoc;
import com.fno.back.note.domain.NoteFolder;
import com.fno.back.note.service.NoteCollectService;
import com.fno.back.note.service.NoteDocService;
import com.fno.back.note.service.NoteFolderService;
import com.fno.common.annotation.Anonymous;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @des
 * @author Ly
 * @date 2023/4/20
 */
@Controller
@RequestMapping("/note/file")
@ResponseBody
public class ApiNoteFileController extends BaseController {


    @Autowired
    private NoteDocService docService;
    @Autowired
    private NoteFolderService folderService;
    @Autowired
    private NoteCollectService collectService;


    /**
     * 获取我的文件信息
     *
     * @param id
     * @return 结果
     */
    @Anonymous
    @RequestMapping("/getFileList/{id}")
    public AjaxResult getFileList(@PathVariable("id") Long id)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //查文件夹
        NoteFolder q = new NoteFolder();
        q.setParentId(id);
        q.setIfDel(CommonConstants.NO);
        q.setUserId(user.getUserId());
        List<NoteFolder> folderList = folderService.selectNoteFolderList(q);
        //查笔记
        List<NoteDoc> docList = docService.selectNoteDocListAndCollect(user.getUserId(),id);
        Map<String,Object> data = new HashMap<>();
        data.put("folderList",folderList);
        data.put("docList",docList);
        return success(data);
    }

    /**
     * 回收站列表
     * @return 结果
     */
    @Anonymous
    @RequestMapping("/getDelList")
    public AjaxResult getDelList()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //查文件夹
        NoteFolder q = new NoteFolder();
        q.setIfDel(CommonConstants.YES);
        q.setUserId(user.getUserId());
        List<NoteFolder> folderList = folderService.selectNoteFolderList(q);
        //查笔记
        NoteDoc d = new NoteDoc();
        d.setUserId(user.getUserId());
        d.setIfDel(CommonConstants.YES);
        List<NoteDoc> docList = docService.selectNoteDocList(d);
        Map<String,Object> data = new HashMap<>();
        data.put("folderList",folderList);
        data.put("docList",docList);
        return success(data);
    }


    /**
     * 获取我的收藏
     * @return 结果
     */
    @Anonymous
    @RequestMapping("/getMyCollect")
    public AjaxResult getMyCollect()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<NoteDoc> docList = docService.getMyCollect(user.getUserId());
        return success(docList);
    }


    /****
     * 添加文件夹
     * @param folder
     * @return
     */
    @Anonymous
    @RequestMapping("/saveFolder")
    public AjaxResult saveFolder(@RequestBody NoteFolder folder)
    {
        if(StrUtil.isBlank(folder.getName())){
            return error("请输入文件夹名");
        }
        if(folder.getId()==null){
            SysUser user = SecurityUtils.getLoginUser().getUser();
            folder.setUserId(user.getUserId());
            folderService.insertNoteFolder(folder);
        }else{
            folderService.updateNoteFolder(folder);
        }
        return success();
    }


    /*****
     * 新增笔记
     * @param doc
     * @return
     */
    @Anonymous
    @RequestMapping("/saveDoc")
    public AjaxResult saveDoc(@RequestBody NoteDoc doc)
    {
        if(StrUtil.isBlank(doc.getTitle())){
            return error("请输入笔记名称");
        }
        if(doc.getId()==null){
            SysUser user = SecurityUtils.getLoginUser().getUser();
            doc.setUserId(user.getUserId());
            docService.insertNoteDoc(doc);
        }else{
            docService.updateNoteDoc(doc);
        }
        return success();
    }


    /*****
     * 保存笔记
     * @param doc
     * @return
     */
    @Anonymous
    @RequestMapping("/saveContent")
    public AjaxResult saveContent(@RequestBody NoteDoc doc){
        if(doc.getId()==null){
            return error("请选择需要保存的笔记！");
        }
        docService.updateNoteDoc(doc);
        return success();
    }


    /*****
     * 查看笔记详情
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/getDocDetail/{id}")
    public AjaxResult getDocDetail(@PathVariable("id") Long id){
        NoteDoc doc = docService.selectNoteDocById(id);
        return success(doc);
    }


    /*****
     * 收藏
     * @param docId
     * @return
     */
    @Anonymous
    @RequestMapping("/collectDoc/{docId}")
    public AjaxResult collectDoc(@PathVariable ("docId") Long docId){
        if(docId==null){
            return error("请选择需要收藏的笔记！");
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        NoteCollect c = new NoteCollect();
        c.setUserId(user.getUserId());
        c.setDocId(docId);
        collectService.insertNoteCollect(c);
        return success();
    }

    /*****
     * 取消收藏
     * @param docId
     * @return
     */
    @Anonymous
    @RequestMapping("/cancelCollect/{docId}")
    public AjaxResult cancelCollect(@PathVariable("docId") Long docId){
        if(docId==null){
            return error("请选择需要取消的笔记！");
        }
        collectService.cancelCollect(docId);
        return success();
    }





    /*****
     * 删除文件夹
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/delFolder/{id}")
    public AjaxResult delFolder(@PathVariable("id") Long id){
        if(id==null){
            return error("请选择需要删除的文件夹！");
        }
        NoteFolder f = new NoteFolder();
        f.setId(id);
        f.setIfDel(CommonConstants.YES);
        folderService.updateNoteFolder(f);
        return success();
    }




    /*****
     * 删除笔记
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/delDoc/{id}")
    public AjaxResult delDoc(@PathVariable("id") Long id){
        if(id==null){
            return error("请选择需要删除的笔记！");
        }
        NoteDoc doc = new NoteDoc();
        doc.setId(id);
        doc.setIfDel(CommonConstants.YES);
        docService.updateNoteDoc(doc);
        return success();
    }







    /*****
     * 恢复笔记
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/restoreDoc/{id}")
    public AjaxResult restoreDoc(@PathVariable("id") Long id){
        if(id==null){
            return error("请选择需要恢复的笔记！");
        }
        NoteDoc doc = new NoteDoc();
        doc.setId(id);
        doc.setIfDel(CommonConstants.NO);
        docService.updateNoteDoc(doc);
        return success();
    }

    /*****
     * 真实删除笔记
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/realDelDoc/{id}")
    public AjaxResult realDelDoc(@PathVariable("id") Long id){
        if(id==null){
            return error("请选择真实删除的笔记！");
        }
        NoteDoc doc = new NoteDoc();
        doc.setId(id);
        doc.setIfDel("D");
        docService.updateNoteDoc(doc);
        return success();
    }

    /*****
     * 恢复文件夹
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/restoreFolder/{id}")
    public AjaxResult restoreFolder(@PathVariable("id") Long id){
        if(id==null){
            return error("请选择需要恢复的文件夹！");
        }
        NoteFolder folder = new NoteFolder();
        folder.setId(id);
        folder.setIfDel(CommonConstants.NO);
        folderService.updateNoteFolder(folder);
        return success();
    }


    /*****
     * 真实删除文件夹
     * @param id
     * @return
     */
    @Anonymous
    @RequestMapping("/realDelFolder/{id}")
    public AjaxResult realDelFolder(@PathVariable("id") Long id){
        if(id==null){
            return error("请选择真实删除的文件夹！");
        }
        NoteFolder folder = new NoteFolder();
        folder.setId(id);
        folder.setIfDel("D");
        folderService.updateNoteFolder(folder);
        return success();
    }



















}
