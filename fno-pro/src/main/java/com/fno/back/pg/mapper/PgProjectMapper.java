package com.fno.back.pg.mapper;

import java.util.List;
import com.fno.back.pg.domain.PgProject;

/**
 * 项目管理Mapper接口
 * 
 * @author fno
 * @date 2023-04-15
 */
public interface PgProjectMapper 
{
    /**
     * 查询项目管理
     * 
     * @param id 项目管理主键
     * @return 项目管理
     */
    public PgProject selectPgProjectById(Long id);

    /**
     * 查询项目管理列表
     * 
     * @param pgProject 项目管理
     * @return 项目管理集合
     */
    public List<PgProject> selectPgProjectList(PgProject pgProject);

    /**
     * 新增项目管理
     * 
     * @param pgProject 项目管理
     * @return 结果
     */
    public int insertPgProject(PgProject pgProject);

    /**
     * 修改项目管理
     * 
     * @param pgProject 项目管理
     * @return 结果
     */
    public int updatePgProject(PgProject pgProject);

    /**
     * 删除项目管理
     * 
     * @param id 项目管理主键
     * @return 结果
     */
    public int deletePgProjectById(Long id);

    /**
     * 批量删除项目管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePgProjectByIds(Long[] ids);
}
