package com.fno.back.pg.mapper;

import java.util.List;
import com.fno.back.pg.domain.PgCodeManage;

/**
 * 代码管理Mapper接口
 * 
 * @author fno
 * @date 2023-04-15
 */
public interface PgCodeManageMapper 
{
    /**
     * 查询代码管理
     * 
     * @param id 代码管理主键
     * @return 代码管理
     */
    public PgCodeManage selectPgCodeManageById(Long id);

    /**
     * 查询代码管理列表
     * 
     * @param pgCodeManage 代码管理
     * @return 代码管理集合
     */
    public List<PgCodeManage> selectPgCodeManageList(PgCodeManage pgCodeManage);

    /**
     * 新增代码管理
     * 
     * @param pgCodeManage 代码管理
     * @return 结果
     */
    public int insertPgCodeManage(PgCodeManage pgCodeManage);

    /**
     * 修改代码管理
     * 
     * @param pgCodeManage 代码管理
     * @return 结果
     */
    public int updatePgCodeManage(PgCodeManage pgCodeManage);

    /**
     * 删除代码管理
     * 
     * @param id 代码管理主键
     * @return 结果
     */
    public int deletePgCodeManageById(Long id);

    /**
     * 批量删除代码管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePgCodeManageByIds(Long[] ids);
}
