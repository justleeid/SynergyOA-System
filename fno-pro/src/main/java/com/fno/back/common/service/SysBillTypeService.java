package com.fno.back.common.service;

import com.fno.back.common.domain.SysBillType;
import com.fno.back.common.mapper.SysBillTypeMapper;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 单据类型配置Service业务层处理
 * 
 * @author fno
 * @date 2023-08-30
 */
@Service
public class SysBillTypeService
{
    @Autowired
    private SysBillTypeMapper sysBillTypeMapper;

    /**
     * 查询单据类型配置
     * 
     * @param id 单据类型配置主键
     * @return 单据类型配置
     */
    public SysBillType selectOaBillTypeById(Long id)
    {
        return sysBillTypeMapper.selectOaBillTypeById(id);
    }


    /****
     * 根据单据类型编码查询
     * @param billType
     * @return
     */
    public SysBillType selectOaBillTypeByBillType(String billType)
    {
        return sysBillTypeMapper.selectOaBillTypeByBillType(billType);
    }

    /**
     * 查询单据类型配置列表
     * 
     * @param sysBillType 单据类型配置
     * @return 单据类型配置
     */
    public List<SysBillType> selectOaBillTypeList(SysBillType sysBillType)
    {
        //sysBillType.setTenantId(SecurityUtils.getTenantId());
        return sysBillTypeMapper.selectOaBillTypeList(sysBillType);
    }

    public List<SysBillType> selectOaBillTypeListAll(SysBillType sysBillType)
    {
        //sysBillType.setTenantId(SecurityUtils.getTenantId());
        return sysBillTypeMapper.selectOaBillTypeListAll(sysBillType);
    }

    /**
     * 新增单据类型配置
     * 
     * @param sysBillType 单据类型配置
     * @return 结果
     */
    public int insertOaBillType(SysBillType sysBillType)
    {
        sysBillType.setCreateTime(DateUtils.getNowDate());
        //sysBillType.setTenantId(SecurityUtils.getTenantId());
        sysBillType.setCreateBy(SecurityUtils.getUserId());
        return sysBillTypeMapper.insertOaBillType(sysBillType);
    }

    /**
     * 修改单据类型配置
     * 
     * @param sysBillType 单据类型配置
     * @return 结果
     */
    public int updateOaBillType(SysBillType sysBillType)
    {
        sysBillType.setUpdateTime(DateUtils.getNowDate());
        return sysBillTypeMapper.updateOaBillType(sysBillType);
    }

    /**
     * 批量删除单据类型配置
     * 
     * @param ids 需要删除的单据类型配置主键
     * @return 结果
     */
    public int deleteOaBillTypeByIds(Long[] ids)
    {
        return sysBillTypeMapper.deleteOaBillTypeByIds(ids);
    }

    /**
     * 删除单据类型配置信息
     * 
     * @param id 单据类型配置主键
     * @return 结果
     */
    public int deleteOaBillTypeById(Long id)
    {
        return sysBillTypeMapper.deleteOaBillTypeById(id);
    }
}
