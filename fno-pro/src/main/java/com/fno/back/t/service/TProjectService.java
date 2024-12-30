package com.fno.back.t.service;

import java.util.List;
import java.util.Map;

import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.t.mapper.TProjectMapper;
import com.fno.back.t.domain.TProject;
import com.fno.back.t.service.TProjectService;

/**
 * 项目Service业务层处理
 *
 * @author fno
 * @date 2024-02-27
 */
@Service
public class TProjectService
{
    @Autowired
    private TProjectMapper tProjectMapper;

    /**
     * 查询项目
     *
     * @param id 项目主键
     * @return 项目
     */
    public TProject selectTProjectById(Long id)
    {
        return tProjectMapper.selectTProjectById(id);
    }

    /**
     * 查询项目列表
     *
     * @param tProject 项目
     * @return 项目
     */
    public List<TProject> selectTProjectList(TProject tProject)
    {
        return tProjectMapper.selectTProjectList(tProject);
    }

    /**
     * 新增项目
     *
     * @param tProject 项目
     * @return 结果
     */
    public int insertTProject(TProject tProject)
    {
                tProject.setCreateTime(DateUtils.getNowDate());
            return tProjectMapper.insertTProject(tProject);
    }

    /**
     * 修改项目
     *
     * @param tProject 项目
     * @return 结果
     */
    public int updateTProject(TProject tProject)
    {
                tProject.setUpdateTime(DateUtils.getNowDate());
        return tProjectMapper.updateTProject(tProject);
    }

    /**
     * 批量删除项目
     *
     * @param ids 需要删除的项目主键
     * @return 结果
     */
    public int deleteTProjectByIds(Long[] ids)
    {
        return tProjectMapper.deleteTProjectByIds(ids);
    }

    /**
     * 删除项目信息
     *
     * @param id 项目主键
     * @return 结果
     */
    public int deleteTProjectById(Long id)
    {
        return tProjectMapper.deleteTProjectById(id);
    }




    /****
     * 查询所有年份
     * @return
     */
    public List<String> getYear(){
        return tProjectMapper.getYear();
    }

    public List<Map> getProjectCount(){
        return tProjectMapper.getProjectCount();
    }



}
