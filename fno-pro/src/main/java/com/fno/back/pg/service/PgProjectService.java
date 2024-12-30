package com.fno.back.pg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.pg.mapper.PgProjectMapper;
import com.fno.back.pg.domain.PgProject;

/**
 * 项目管理Service业务层处理
 * 
 * @author fno
 * @date 2023-04-15
 */
@Service
public class PgProjectService
{
    @Autowired
    private PgProjectMapper pgProjectMapper;

    /**
     * 查询项目管理
     * 
     * @param id 项目管理主键
     * @return 项目管理
     */
    public PgProject selectPgProjectById(Long id)
    {
        return pgProjectMapper.selectPgProjectById(id);
    }

    /**
     * 查询项目管理列表
     * 
     * @param pgProject 项目管理
     * @return 项目管理
     */
    public List<PgProject> selectPgProjectList(PgProject pgProject)
    {
        return pgProjectMapper.selectPgProjectList(pgProject);
    }

    /**
     * 新增项目管理
     * 
     * @param pgProject 项目管理
     * @return 结果
     */
    public int insertPgProject(PgProject pgProject)
    {
        return pgProjectMapper.insertPgProject(pgProject);
    }

    /**
     * 修改项目管理
     * 
     * @param pgProject 项目管理
     * @return 结果
     */
    public int updatePgProject(PgProject pgProject)
    {
        return pgProjectMapper.updatePgProject(pgProject);
    }

    /**
     * 批量删除项目管理
     * 
     * @param ids 需要删除的项目管理主键
     * @return 结果
     */
    public int deletePgProjectByIds(Long[] ids)
    {
        return pgProjectMapper.deletePgProjectByIds(ids);
    }

    /**
     * 删除项目管理信息
     * 
     * @param id 项目管理主键
     * @return 结果
     */
    public int deletePgProjectById(Long id)
    {
        return pgProjectMapper.deletePgProjectById(id);
    }
}
