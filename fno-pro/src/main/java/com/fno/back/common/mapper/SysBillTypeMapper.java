package com.fno.back.common.mapper;

import com.fno.back.common.domain.SysBillType;

import java.util.List;

/**
 * 单据类型配置Mapper接口
 * 
 * @author fno
 * @date 2023-08-30
 */
public interface SysBillTypeMapper
{
    /**
     * 查询单据类型配置
     * 
     * @param id 单据类型配置主键
     * @return 单据类型配置
     */
    public SysBillType selectOaBillTypeById(Long id);


    public SysBillType selectOaBillTypeByBillType(String billType);


    public List<SysBillType> selectOaBillTypeListAll(SysBillType sysBillType);

    /**
     * 查询单据类型配置列表
     * 
     * @param sysBillType 单据类型配置
     * @return 单据类型配置集合
     */
    public List<SysBillType> selectOaBillTypeList(SysBillType sysBillType);

    /**
     * 新增单据类型配置
     * 
     * @param sysBillType 单据类型配置
     * @return 结果
     */
    public int insertOaBillType(SysBillType sysBillType);

    /**
     * 修改单据类型配置
     * 
     * @param sysBillType 单据类型配置
     * @return 结果
     */
    public int updateOaBillType(SysBillType sysBillType);

    /**
     * 删除单据类型配置
     * 
     * @param id 单据类型配置主键
     * @return 结果
     */
    public int deleteOaBillTypeById(Long id);

    /**
     * 批量删除单据类型配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaBillTypeByIds(Long[] ids);
}
