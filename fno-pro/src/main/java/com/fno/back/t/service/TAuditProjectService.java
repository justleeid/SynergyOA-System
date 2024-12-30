package com.fno.back.t.service;

import java.util.List;
import java.util.Map;

import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.t.mapper.TAuditProjectMapper;
import com.fno.back.t.domain.TAuditProject;
import com.fno.back.t.service.TAuditProjectService;

/**
 * 审核项目Service业务层处理
 *
 * @author fno
 * @date 2024-02-27
 */
@Service
public class TAuditProjectService
{
    @Autowired
    private TAuditProjectMapper tAuditProjectMapper;

    /**
     * 查询审核项目
     *
     * @param id 审核项目主键
     * @return 审核项目
     */
    public TAuditProject selectTAuditProjectById(Long id)
    {
        return tAuditProjectMapper.selectTAuditProjectById(id);
    }

    /**
     * 查询审核项目列表
     *
     * @param tAuditProject 审核项目
     * @return 审核项目
     */
    public List<TAuditProject> selectTAuditProjectList(TAuditProject tAuditProject)
    {
        return tAuditProjectMapper.selectTAuditProjectList(tAuditProject);
    }

    /**
     * 新增审核项目
     *
     * @param tAuditProject 审核项目
     * @return 结果
     */
    public int insertTAuditProject(TAuditProject tAuditProject)
    {
                tAuditProject.setCreateTime(DateUtils.getNowDate());
            return tAuditProjectMapper.insertTAuditProject(tAuditProject);
    }

    /**
     * 修改审核项目
     *
     * @param tAuditProject 审核项目
     * @return 结果
     */
    public int updateTAuditProject(TAuditProject tAuditProject)
    {
                tAuditProject.setUpdateTime(DateUtils.getNowDate());
        return tAuditProjectMapper.updateTAuditProject(tAuditProject);
    }

    /**
     * 批量删除审核项目
     *
     * @param ids 需要删除的审核项目主键
     * @return 结果
     */
    public int deleteTAuditProjectByIds(Long[] ids)
    {
        return tAuditProjectMapper.deleteTAuditProjectByIds(ids);
    }

    /**
     * 删除审核项目信息
     *
     * @param id 审核项目主键
     * @return 结果
     */
    public int deleteTAuditProjectById(Long id)
    {
        return tAuditProjectMapper.deleteTAuditProjectById(id);
    }



    /****
     * 查询所有年份
     * @return
     */
    public List<String> getYear(){
        return tAuditProjectMapper.getYear();
    }

    public List<Map> getProjectCount(){
        return tAuditProjectMapper.getProjectCount();
    }
}
