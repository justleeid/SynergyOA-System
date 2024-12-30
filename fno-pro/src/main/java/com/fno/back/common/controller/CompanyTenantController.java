package com.fno.back.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.common.constant.UserConstants;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.enums.BusinessType;
import com.fno.back.common.domain.CompanyTenant;
import com.fno.back.common.service.CompanyTenantService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 公司管理-租户Controller
 * 
 * @author fno
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/tenant/companyTenant")
public class CompanyTenantController extends BaseController
{
    @Autowired
    private CompanyTenantService companyTenantService;
    @Autowired
    private ISysUserService userService;

    /**
     * 查询公司管理-租户列表
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyTenant companyTenant)
    {
        startPage();
        //获取当前登录用户
        SysUser user = SecurityUtils.getLoginUser().getUser();
        if (user.getIsSuperadmin().equals("Y"))
            companyTenant.setAdminName("admin");
        List<CompanyTenant> list = companyTenantService.selectCompanyTenantList(companyTenant);
        return getDataTable(list);
    }

    /**
     * 导出公司管理-租户列表
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:export')")
    @Log(title = "公司管理-租户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompanyTenant companyTenant)
    {
        List<CompanyTenant> list = companyTenantService.selectCompanyTenantList(companyTenant);
        ExcelUtil<CompanyTenant> util = new ExcelUtil<CompanyTenant>(CompanyTenant.class);
        util.exportExcel(response, list, "公司管理-租户数据");
    }

    /**
     * 获取公司管理-租户详细信息
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(companyTenantService.selectCompanyTenantById(id));
    }


    /**
     * 获取我的公司信息
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:query')")
    @GetMapping(value = "/getMyCompanyTenant")
    public AjaxResult getInfo()
    {
        return AjaxResult.success(companyTenantService.selectCompanyTenantById(SecurityUtils.getTenantId()));
    }

    /**
     * 新增公司管理-租户
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:add')")
    @Log(title = "公司管理-租户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyTenant companyTenant)
    {
        return toAjax(companyTenantService.insertCompanyTenant(companyTenant));
    }

    /**
     * 修改公司管理-租户
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:edit')")
    @Log(title = "公司管理-租户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyTenant companyTenant)
    {
        return toAjax(companyTenantService.updateCompanyTenant(companyTenant));
    }

    /**
     * 删除公司管理-租户
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:remove')")
    @Log(title = "公司管理-租户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(companyTenantService.deleteCompanyTenantByIds(ids));
    }




    /**
     * 初始化企业
     */
    @PreAuthorize("@ss.hasPermi('tenant:companyTenant:edit')")
    @Log(title = "初始化企业", businessType = BusinessType.UPDATE)
    @PutMapping(value = "initCompany")
    public AjaxResult initCompany(@RequestBody CompanyTenant companyTenant)
    {
        //先从参数中取出信息
        String userName = companyTenant.getName();
        //这里加个账号校验判断
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(userName,false)))
        {
            return AjaxResult.error("账号'" + userName + "'已经存在，不能重复");
        }
        companyTenantService.initCompany(companyTenant.getId(),companyTenant.getName());
        return success();
    }
}
