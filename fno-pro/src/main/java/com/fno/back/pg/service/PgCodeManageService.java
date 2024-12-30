package com.fno.back.pg.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.pg.mapper.PgCodeManageMapper;
import com.fno.back.pg.domain.PgCodeManage;

/**
 * 代码管理Service业务层处理
 * 
 * @author fno
 * @date 2023-04-15
 */
@Service
public class PgCodeManageService
{
    @Autowired
    private PgCodeManageMapper pgCodeManageMapper;

    /**
     * 查询代码管理
     * 
     * @param id 代码管理主键
     * @return 代码管理
     */
    public PgCodeManage selectPgCodeManageById(Long id)
    {
        return pgCodeManageMapper.selectPgCodeManageById(id);
    }

    /**
     * 查询代码管理列表
     * 
     * @param pgCodeManage 代码管理
     * @return 代码管理
     */
    public List<PgCodeManage> selectPgCodeManageList(PgCodeManage pgCodeManage)
    {
        return pgCodeManageMapper.selectPgCodeManageList(pgCodeManage);
    }

    /**
     * 新增代码管理
     * 
     * @param pgCodeManage 代码管理
     * @return 结果
     */
    public int insertPgCodeManage(PgCodeManage pgCodeManage)
    {
        pgCodeManage.setCreateTime(DateUtils.getNowDate());
        return pgCodeManageMapper.insertPgCodeManage(pgCodeManage);
    }

    /**
     * 修改代码管理
     * 
     * @param pgCodeManage 代码管理
     * @return 结果
     */
    public int updatePgCodeManage(PgCodeManage pgCodeManage)
    {
        pgCodeManage.setUpdateTime(DateUtils.getNowDate());
        return pgCodeManageMapper.updatePgCodeManage(pgCodeManage);
    }

    /**
     * 批量删除代码管理
     * 
     * @param ids 需要删除的代码管理主键
     * @return 结果
     */
    public int deletePgCodeManageByIds(Long[] ids)
    {
        return pgCodeManageMapper.deletePgCodeManageByIds(ids);
    }

    /**
     * 删除代码管理信息
     * 
     * @param id 代码管理主键
     * @return 结果
     */
    public int deletePgCodeManageById(Long id)
    {
        return pgCodeManageMapper.deletePgCodeManageById(id);
    }
}
