package com.fno.framework.web.service;

import com.fno.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.domain.model.LoginUser;
import com.fno.common.enums.UserStatus;
import com.fno.common.exception.ServiceException;
import com.fno.common.utils.StringUtils;
import com.fno.system.service.ISysUserService;

import java.util.Map;

/**
 * 用户验证处理
 *
 * @author ry
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
//        String tenantName = null;
//        if(!username.contains(":")){
//            log.info("用户登录：用户名格式不正确.{}", username);
//            throw new ServiceException("用户名：" + username + " 不正确.未确定所属公司");
//        }else{
//            String[] arr = username.split(":");
//            tenantName = arr[0];
//            username = arr[1];
//        }
//        Map tenant = userService.selectCompanyTenantByName(tenantName);
//        if(tenant==null||tenant.get("id")==null){
//            log.info("公司不存在："+tenantName);
//            throw new ServiceException("公司不存在：" + tenantName + " 不存在");
//        }
//        Long tenantId = Long.parseLong(tenant.get("id").toString());
        //从这儿开始改
        SysUser user = userService.selectUserByUserName(username);
       // SysUser user = userService.selectUserByUserName(username, tenantId);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        //然后再通过租户id查询公司名称
        String tenantName = userService.selectCompanyTenantById(user.getTenantId()).get("name").toString();
        user.setTenantName(tenantName);
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getUserId(),user.getNickName(), user.getTenantId(),user.getTenantName(), user.getDeptId(), user,user.getIsAdmin(),user.getIsSuperadmin(), permissionService.getMenuPermission(user));
    }
}
