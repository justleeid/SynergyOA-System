package com.fno.back.common.mapper;

import java.util.List;
import com.fno.back.common.domain.CompanyTenant;

/**
 * 公司管理-租户Mapper接口
 * 
 * @author fno
 * @date 2023-08-15
 */
public interface CompanyTenantMapper 
{
    /**
     * 查询公司管理-租户
     * 
     * @param id 公司管理-租户主键
     * @return 公司管理-租户
     */
    public CompanyTenant selectCompanyTenantById(Long id);

    /**
     * 查询公司管理-租户列表
     * 
     * @param companyTenant 公司管理-租户
     * @return 公司管理-租户集合
     */
    public List<CompanyTenant> selectCompanyTenantList(CompanyTenant companyTenant);

    /**
     * 新增公司管理-租户
     * 
     * @param companyTenant 公司管理-租户
     * @return 结果
     */
    public int insertCompanyTenant(CompanyTenant companyTenant);

    /**
     * 修改公司管理-租户
     * 
     * @param companyTenant 公司管理-租户
     * @return 结果
     */
    public int updateCompanyTenant(CompanyTenant companyTenant);

    /**
     * 删除公司管理-租户
     * 
     * @param id 公司管理-租户主键
     * @return 结果
     */
    public int deleteCompanyTenantById(Long id);

    /**
     * 批量删除公司管理-租户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyTenantByIds(Long[] ids);
}
