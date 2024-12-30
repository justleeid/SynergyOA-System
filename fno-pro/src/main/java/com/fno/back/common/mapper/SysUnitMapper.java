package com.fno.back.common.mapper;

import com.fno.back.common.domain.SysUnit;

import java.util.List;

/**
 * 计量单位Mapper接口
 * 
 * @author fno
 * @date 2022-10-20
 */
public interface SysUnitMapper
{
    /**
     * 查询计量单位
     * 
     * @param id 计量单位主键
     * @return 计量单位
     */
    public SysUnit selectUnitById(Long id);

    /**
     * 查询计量单位列表
     * 
     * @param sysUnit 计量单位
     * @return 计量单位集合
     */
    public List<SysUnit> selectUnitList(SysUnit sysUnit);

    /**
     * 新增计量单位
     * 
     * @param sysUnit 计量单位
     * @return 结果
     */
    public int insertUnit(SysUnit sysUnit);

    /**
     * 修改计量单位
     * 
     * @param sysUnit 计量单位
     * @return 结果
     */
    public int updateUnit(SysUnit sysUnit);

    /**
     * 删除计量单位
     * 
     * @param id 计量单位主键
     * @return 结果
     */
    public int deleteUnitById(Long id);

    /**
     * 批量删除计量单位
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUnitByIds(Long[] ids);
}
