package com.fno.back.common.service;

import java.util.Date;
import java.util.List;

import com.fno.back.common.constant.CommonConstants;
import com.fno.common.core.domain.entity.SysDept;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.exception.ServiceException;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.mapper.SysDeptMapper;
import com.fno.system.service.ISysDictTypeService;
import com.fno.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.common.mapper.CompanyTenantMapper;
import com.fno.back.common.domain.CompanyTenant;
import org.springframework.transaction.annotation.Transactional;

/**
 * 公司管理-租户Service业务层处理
 * 
 * @author fno
 * @date 2023-08-15
 */
@Service
public class CompanyTenantService
{
    @Autowired
    private CompanyTenantMapper companyTenantMapper;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDictTypeService dictTypeService;
    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 查询公司管理-租户
     * 
     * @param id 公司管理-租户主键
     * @return 公司管理-租户
     */
    public CompanyTenant selectCompanyTenantById(Long id)
    {
        return companyTenantMapper.selectCompanyTenantById(id);
    }

    /**
     * 查询公司管理-租户列表
     * 
     * @param companyTenant 公司管理-租户
     * @return 公司管理-租户
     */
    public List<CompanyTenant> selectCompanyTenantList(CompanyTenant companyTenant)
    {
        return companyTenantMapper.selectCompanyTenantList(companyTenant);
    }

    /**
     * 新增公司管理-租户
     * 
     * @param companyTenant 公司管理-租户
     * @return 结果
     */
    public int insertCompanyTenant(CompanyTenant companyTenant)
    {
        companyTenant.setCreateTime(DateUtils.getNowDate());
        return companyTenantMapper.insertCompanyTenant(companyTenant);
    }

    /**
     * 修改公司管理-租户
     * 
     * @param companyTenant 公司管理-租户
     * @return 结果
     */
    public int updateCompanyTenant(CompanyTenant companyTenant)
    {
        return companyTenantMapper.updateCompanyTenant(companyTenant);
    }

    /**
     * 批量删除公司管理-租户
     * 
     * @param ids 需要删除的公司管理-租户主键
     * @return 结果
     */
    public int deleteCompanyTenantByIds(Long[] ids)
    {
        return companyTenantMapper.deleteCompanyTenantByIds(ids);
    }

    /**
     * 删除公司管理-租户信息
     * 
     * @param id 公司管理-租户主键
     * @return 结果
     */
    public int deleteCompanyTenantById(Long id)
    {
        return companyTenantMapper.deleteCompanyTenantById(id);
    }


    /****
     * 初始化企业
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void initCompany(Long id,String adminName){
        CompanyTenant c = companyTenantMapper.selectCompanyTenantById(id);
        if(c==null){
            throw new ServiceException("初始化企业不存在");
        }
        if(CommonConstants.YES.equals(c.getIfInit())){
            throw new ServiceException("企业已经初始化过了");
        }
        //判断管理员账号是否已经存在
        SysUser user = sysUserService.selectUserByUserName(adminName);
        if (user != null){
            throw new ServiceException(adminName+"已经存在，不能重复");
        }
        // 初始化部门
        SysDept d = new SysDept();
        d.setTenantId(id);
        d.setDeptName(c.getName());
        d.setParentId(new Long(0));
        d.setAncestors("0");
        d.setCreateTime(new Date());
        deptMapper.insertDept(d);
        // 新增用户
        SysUser admin = new SysUser();
        admin.setUserName(adminName);
        admin.setPassword(SecurityUtils.encryptPassword("123456"));
        admin.setTenantId(id);
        admin.setNickName("企业管理员");
        admin.setDeptId(d.getDeptId());
        admin.setIsAdmin(CommonConstants.YES);
        admin.setIsSuperadmin(CommonConstants.NO);
        admin.setCreateTime(new Date());
        admin.setCreateBy(new Long(1));
        // 绑定角色
        Long[] roleIds = new Long[]{new Long(1)};
        admin.setRoleIds(roleIds);
        sysUserService.insertUser(admin);
        // 修改企业状态
        CompanyTenant companyTenant = new CompanyTenant();
        companyTenant.setId(id);
        companyTenant.setIfInit(CommonConstants.YES);
        companyTenant.setAdminName(adminName);
        companyTenantMapper.updateCompanyTenant(companyTenant);
    }
}
