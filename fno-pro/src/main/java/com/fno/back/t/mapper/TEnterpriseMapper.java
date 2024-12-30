package com.fno.back.t.mapper;

import java.util.List;
import java.util.Map;

import com.fno.back.t.domain.TEnterprise;

/**
 * 企业Mapper接口
 * 
 * @author fno
 * @date 2024-02-27
 */
public interface TEnterpriseMapper 
{
    /**
     * 查询企业
     * 
     * @param id 企业主键
     * @return 企业
     */
    public TEnterprise selectTEnterpriseById(Long id);

    /**
     * 查询企业列表
     * 
     * @param tEnterprise 企业
     * @return 企业集合
     */
    public List<TEnterprise> selectTEnterpriseList(TEnterprise tEnterprise);

    /**
     * 新增企业
     * 
     * @param tEnterprise 企业
     * @return 结果
     */
    public int insertTEnterprise(TEnterprise tEnterprise);

    /**
     * 修改企业
     * 
     * @param tEnterprise 企业
     * @return 结果
     */
    public int updateTEnterprise(TEnterprise tEnterprise);

    /**
     * 删除企业
     * 
     * @param id 企业主键
     * @return 结果
     */
    public int deleteTEnterpriseById(Long id);

    /**
     * 批量删除企业
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTEnterpriseByIds(Long[] ids);


    public List<String> getYear();

    public List<Map> getData();

    public List<Map> getPlaceData();

    public List<Map> getPTData();
}
