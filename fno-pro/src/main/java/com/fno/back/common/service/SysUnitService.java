package com.fno.back.common.service;

import com.fno.back.common.domain.SysUnit;
import com.fno.back.common.mapper.SysUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 计量单位Service业务层处理
 * 
 * @author fno
 * @date 2022-10-20
 */
@Service
public class SysUnitService
{
    @Autowired
    private SysUnitMapper unitMapper;

    /**
     * 查询计量单位
     * 
     * @param id 计量单位主键
     * @return 计量单位
     */
    public SysUnit selectUnitById(Long id)
    {
        return unitMapper.selectUnitById(id);
    }

    /**
     * 查询计量单位列表
     * 
     * @param sysUnit 计量单位
     * @return 计量单位
     */
    public List<SysUnit> selectUnitList(SysUnit sysUnit)
    {
        return unitMapper.selectUnitList(sysUnit);
    }

    /**
     * 新增计量单位
     * 
     * @param sysUnit 计量单位
     * @return 结果
     */
    public int insertUnit(SysUnit sysUnit)
    {
        return unitMapper.insertUnit(sysUnit);
    }

    /**
     * 修改计量单位
     * 
     * @param sysUnit 计量单位
     * @return 结果
     */
    public int updateUnit(SysUnit sysUnit)
    {
        return unitMapper.updateUnit(sysUnit);
    }

    /**
     * 批量删除计量单位
     * 
     * @param ids 需要删除的计量单位主键
     * @return 结果
     */
    public int deleteUnitByIds(Long[] ids)
    {
        return unitMapper.deleteUnitByIds(ids);
    }

    /**
     * 删除计量单位信息
     * 
     * @param id 计量单位主键
     * @return 结果
     */
    public int deleteUnitById(Long id)
    {
        return unitMapper.deleteUnitById(id);
    }
}
