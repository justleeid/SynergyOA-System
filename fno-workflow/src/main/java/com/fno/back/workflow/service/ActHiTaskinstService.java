package com.fno.back.workflow.service;

import com.fno.back.workflow.domain.ActHiTaskinst;
import com.fno.back.workflow.mapper.ActHiTaskinstMapper;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 历史任务Service业务层处理
 * 
 * @author fno
 * @date 2023-05-14
 */
@Service
public class ActHiTaskinstService
{
    @Autowired
    private ActHiTaskinstMapper actHiTaskinstMapper;

    /**
     * 查询历史任务
     * 
     * @param id 历史任务主键
     * @return 历史任务
     */
    public ActHiTaskinst selectActHiTaskinstById(String id)
    {
        return actHiTaskinstMapper.selectActHiTaskinstById(id);
    }

    /**
     * 查询历史任务列表
     * 
     * @param actHiTaskinst 历史任务
     * @return 历史任务
     */
    public List<ActHiTaskinst> selectActHiTaskinstList(ActHiTaskinst actHiTaskinst)
    {
        return actHiTaskinstMapper.selectActHiTaskinstList(actHiTaskinst);
    }

    /**
     * 新增历史任务
     * 
     * @param actHiTaskinst 历史任务
     * @return 结果
     */
    public int insertActHiTaskinst(ActHiTaskinst actHiTaskinst)
    {
        return actHiTaskinstMapper.insertActHiTaskinst(actHiTaskinst);
    }

    /**
     * 修改历史任务
     * 
     * @param actHiTaskinst 历史任务
     * @return 结果
     */
    public int updateActHiTaskinst(ActHiTaskinst actHiTaskinst)
    {
        return actHiTaskinstMapper.updateActHiTaskinst(actHiTaskinst);
    }

    /**
     * 批量删除历史任务
     * 
     * @param ids 需要删除的历史任务主键
     * @return 结果
     */
    public int deleteActHiTaskinstByIds(String[] ids)
    {
        return actHiTaskinstMapper.deleteActHiTaskinstByIds(ids);
    }

    /**
     * 删除历史任务信息
     * 
     * @param id 历史任务主键
     * @return 结果
     */
    public int deleteActHiTaskinstById(String id)
    {
        return actHiTaskinstMapper.deleteActHiTaskinstById(id);
    }


    /*****
     * 获取任务次数统计
     * @return
     */
    public List<Map> getTaskCishuReport(ActHiTaskinst actHiTaskinst){
        Long tenantId = SecurityUtils.getTenantId();
        actHiTaskinst.setTenantId(tenantId);
        return actHiTaskinstMapper.getTaskCishuReport(actHiTaskinst);
    }
}
