package com.fno.back.common.mapper;

import java.util.List;
import com.fno.back.common.domain.SysArea;

/**
 * 省市字典Mapper接口
 * 
 * @author fno
 * @date 2023-10-12
 */
public interface SysAreaMapper 
{
    /**
     * 查询省市字典
     * 
     * @param id 省市字典主键
     * @return 省市字典
     */
    public SysArea selectSysAreaById(String id);

    /**
     * 查询省市字典列表
     * 
     * @param sysArea 省市字典
     * @return 省市字典集合
     */
    public List<SysArea> selectSysAreaList(SysArea sysArea);

    /**
     * 新增省市字典
     * 
     * @param sysArea 省市字典
     * @return 结果
     */
    public int insertSysArea(SysArea sysArea);

    /**
     * 修改省市字典
     * 
     * @param sysArea 省市字典
     * @return 结果
     */
    public int updateSysArea(SysArea sysArea);

    /**
     * 删除省市字典
     * 
     * @param id 省市字典主键
     * @return 结果
     */
    public int deleteSysAreaById(String id);

    /**
     * 批量删除省市字典
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAreaByIds(String[] ids);
}
