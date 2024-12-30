package com.fno.back.t.mapper;

import java.util.List;
import java.util.Map;

import com.fno.back.t.domain.TAuditProject;

/**
 * 审核项目Mapper接口
 * 
 * @author fno
 * @date 2024-02-27
 */
public interface TAuditProjectMapper 
{
    /**
     * 查询审核项目
     * 
     * @param id 审核项目主键
     * @return 审核项目
     */
    public TAuditProject selectTAuditProjectById(Long id);

    /**
     * 查询审核项目列表
     * 
     * @param tAuditProject 审核项目
     * @return 审核项目集合
     */
    public List<TAuditProject> selectTAuditProjectList(TAuditProject tAuditProject);

    /**
     * 新增审核项目
     * 
     * @param tAuditProject 审核项目
     * @return 结果
     */
    public int insertTAuditProject(TAuditProject tAuditProject);

    /**
     * 修改审核项目
     * 
     * @param tAuditProject 审核项目
     * @return 结果
     */
    public int updateTAuditProject(TAuditProject tAuditProject);

    /**
     * 删除审核项目
     * 
     * @param id 审核项目主键
     * @return 结果
     */
    public int deleteTAuditProjectById(Long id);

    /**
     * 批量删除审核项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTAuditProjectByIds(Long[] ids);


    public List<String> getYear();


    public List<Map> getProjectCount();


}
