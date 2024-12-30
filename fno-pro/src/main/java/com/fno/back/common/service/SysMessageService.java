package com.fno.back.common.service;

import com.fno.back.common.domain.SysMessage;
import com.fno.back.common.mapper.SysMessageMapper;
//import com.fno.back.workflow.domain.VTasklist;
//import com.fno.back.oa.service.VTasklistService;
import com.fno.back.common.constant.CommonConstants;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 站内信Service业务层处理
 * 
 * @author fno
 * @date 2023-07-31
 */
@Service
public class SysMessageService
{
    @Autowired
    private SysMessageMapper sysMessageMapper;
    //@Autowired
    //private VTasklistService vTasklistService;


    /****
     * 查询首页消息中心数量
     * @return
     */
    public int queryTopBarMessageCount(){
        //待办任务数量
        //int count = this.queryTodoTaskCount();
        int count = 0;
        //未读消息中心数量
        Long userId = SecurityUtils.getUserId();
        SysMessage sysMessage = new SysMessage();
        sysMessage.setReceiveUserId(userId);
        sysMessage.setIfRead(CommonConstants.NO);
        sysMessage.setTenantId(SecurityUtils.getTenantId());
        List<SysMessage> l = sysMessageMapper.selectSysMessageList(sysMessage);
        if(l!=null){
            count = count + l.size();
        }
        return count;
    }

    /****
     * 待办任务数量
     * @return
     */
    //public int queryTodoTaskCount(){
    //    int count = 0;
    //    String userId = SecurityUtils.getUserId().toString();
    //    List<VTasklist> list = vTasklistService.selectTodoTaskList(userId,"","");
    //    if(list!=null){
    //        count = count + list.size();
    //    }
    //    return count;
    //}



    /**
     * 查询站内信
     * 
     * @param id 站内信主键
     * @return 站内信
     */
    public SysMessage selectSysMessageById(Long id)
    {
        return sysMessageMapper.selectSysMessageById(id);
    }

    /**
     * 查询站内信列表
     * 
     * @param sysMessage 站内信
     * @return 站内信
     */
    public List<SysMessage> selectSysMessageList(SysMessage sysMessage)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        //if(!SecurityUtils.isAdmin()){
            sysMessage.setReceiveUserId(SecurityUtils.getUserId());
        //}
        sysMessage.setTenantId(SecurityUtils.getTenantId());
        return sysMessageMapper.selectSysMessageList(sysMessage);
    }

    /**
     * 新增站内信
     * 
     * @param sysMessage 站内信
     * @return 结果
     */
    public int insertSysMessage(SysMessage sysMessage)
    {
        sysMessage.setTenantId(SecurityUtils.getTenantId());
        return sysMessageMapper.insertSysMessage(sysMessage);
    }

    /**
     * 修改站内信
     *
     * @param sysMessage 站内信
     * @return 结果
     */
    public int updateSysMessage(SysMessage sysMessage)
    {
        return sysMessageMapper.updateSysMessage(sysMessage);
    }


    /**
     * 修改站内信
     *
     * @param id 主键
     * @return 结果
     */
    public int readSysMessage(Long id)
    {
        SysMessage sysMessage = new SysMessage();
        sysMessage.setId(id);
        sysMessage.setIfRead(CommonConstants.YES);
        return sysMessageMapper.updateSysMessage(sysMessage);
    }

    /**
     * 批量删除站内信
     * 
     * @param ids 需要删除的站内信主键
     * @return 结果
     */
    public int deleteSysMessageByIds(Long[] ids)
    {
        return sysMessageMapper.deleteSysMessageByIds(ids);
    }

    /**
     * 删除站内信信息
     * 
     * @param id 站内信主键
     * @return 结果
     */
    public int deleteSysMessageById(Long id)
    {
        return sysMessageMapper.deleteSysMessageById(id);
    }


    public void allMessageRead(){
        Long userId = SecurityUtils.getUserId();
        sysMessageMapper.allMessageRead(userId);
    }
}
