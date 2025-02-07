package com.fno.back.oa.service;

import java.util.List;

import com.fno.back.common.service.SerialService;
import com.fno.back.common.service.SysMessageService;
import com.fno.back.common.constant.CommonConstants;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaSendTaskMapper;
import com.fno.back.oa.domain.OaSendTask;

/**
 * 任务分配Service业务层处理
 *
 */
@Service
public class OaSendTaskService
{
    @Autowired
    private OaSendTaskMapper oaSendTaskMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private SysMessageService sysMessageService;

    /**
     * 查询任务分配
     * 
     * @param id 任务分配主键
     * @return 任务分配
     */
    public OaSendTask selectOaSendTaskById(Long id)
    {
        return oaSendTaskMapper.selectOaSendTaskById(id);
    }

    /**
     * 查询任务分配列表
     * 
     * @param oaSendTask 任务分配
     * @return 任务分配
     */
    public List<OaSendTask> selectOaSendTaskList(OaSendTask oaSendTask)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaSendTask.setCreateBy(SecurityUtils.getUserId());
        }

        if(CommonConstants.YES.equals(oaSendTask.getForTrack())){
            oaSendTask.setForTrackId(SecurityUtils.getUserId());
        }

        oaSendTask.setTenantId(SecurityUtils.getTenantId());
        return oaSendTaskMapper.selectOaSendTaskList(oaSendTask);
    }

    /**
     * 新增任务分配
     * 
     * @param oaSendTask 任务分配
     * @return 结果
     */
    public int insertOaSendTask(OaSendTask oaSendTask)
    {
        oaSendTask.setCreateTime(DateUtils.getNowDate());
        oaSendTask.setTenantId(SecurityUtils.getTenantId());
        oaSendTask.setSendUserId(SecurityUtils.getUserId());
        oaSendTask.setSendNickName(SecurityUtils.getNickName());
        oaSendTask.setCreateBy(SecurityUtils.getUserId());
        return oaSendTaskMapper.insertOaSendTask(oaSendTask);
    }

    /**
     * 修改任务分配
     * 
     * @param oaSendTask 任务分配
     * @return 结果
     */
    public int updateOaSendTask(OaSendTask oaSendTask)
    {
        oaSendTask.setUpdateTime(DateUtils.getNowDate());
        return oaSendTaskMapper.updateOaSendTask(oaSendTask);
    }

    /**
     * 批量删除任务分配
     * 
     * @param ids 需要删除的任务分配主键
     * @return 结果
     */
    public int deleteOaSendTaskByIds(Long[] ids)
    {
        return oaSendTaskMapper.deleteOaSendTaskByIds(ids);
    }

    /**
     * 删除任务分配信息
     * 
     * @param id 任务分配主键
     * @return 结果
     */
    public int deleteOaSendTaskById(Long id)
    {
        return oaSendTaskMapper.deleteOaSendTaskById(id);
    }






}
