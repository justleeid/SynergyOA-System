package com.fno.back.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.common.mapper.SysAreaMapper;
import com.fno.back.common.domain.SysArea;

/**
 * 省市字典Service业务层处理
 * 
 * @author fno
 * @date 2023-10-12
 */
@Service
public class SysAreaService
{
    @Autowired
    private SysAreaMapper sysAreaMapper;

    /**
     * 查询省市字典
     * 
     * @param id 省市字典主键
     * @return 省市字典
     */
    public SysArea selectSysAreaById(String id)
    {
        return sysAreaMapper.selectSysAreaById(id);
    }

    /**
     * 查询省市字典列表
     * 
     * @param sysArea 省市字典
     * @return 省市字典
     */
    public List<SysArea> selectSysAreaList(SysArea sysArea)
    {
        return sysAreaMapper.selectSysAreaList(sysArea);
    }

    /**
     * 新增省市字典
     * 
     * @param sysArea 省市字典
     * @return 结果
     */
    public int insertSysArea(SysArea sysArea)
    {
        return sysAreaMapper.insertSysArea(sysArea);
    }

    /**
     * 修改省市字典
     * 
     * @param sysArea 省市字典
     * @return 结果
     */
    public int updateSysArea(SysArea sysArea)
    {
        return sysAreaMapper.updateSysArea(sysArea);
    }

    /**
     * 批量删除省市字典
     * 
     * @param ids 需要删除的省市字典主键
     * @return 结果
     */
    public int deleteSysAreaByIds(String[] ids)
    {
        return sysAreaMapper.deleteSysAreaByIds(ids);
    }

    /**
     * 删除省市字典信息
     * 
     * @param id 省市字典主键
     * @return 结果
     */
    public int deleteSysAreaById(String id)
    {
        return sysAreaMapper.deleteSysAreaById(id);
    }
}
