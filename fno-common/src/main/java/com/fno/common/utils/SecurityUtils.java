package com.fno.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fno.common.constant.HttpStatus;
import com.fno.common.core.domain.model.LoginUser;
import com.fno.common.exception.ServiceException;

/**
 * 安全服务工具类
 * 
 * @author ry
 */
public class SecurityUtils
{
    /**
     * 用户ID
     **/
    public static Long getUserId()
    {
        try
        {
            return getLoginUser().getUserId();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
        }
    }

    public static String getNickName()
    {
        try
        {
            return getLoginUser().getNickName();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户昵称异常", HttpStatus.UNAUTHORIZED);
        }
    }

    public static boolean isAdmin(){
        try
        {
            boolean result  = false;
            LoginUser loginUser = getLoginUser();
            if("Y".equals(loginUser.getIsAdmin())){
                result = true;
            }
            //if("Y".equals(loginUser.getIsSuperadmin())){
            //    result = true;
            //}
            return result;
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户是否是管理员异常", HttpStatus.UNAUTHORIZED);
        }
    }

    public static boolean isSuperAdmin(){
        try
        {
            boolean result  = false;
            LoginUser loginUser = getLoginUser();
            if("Y".equals(loginUser.getIsSuperadmin())){
                result = true;
            }
            return result;
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户是否是超级管理员异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /****
     * 获取租户ID
     * @return
     */
    public static Long getTenantId()
    {
        try
        {
            return getLoginUser().getTenantId();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取公司租户ID异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取部门ID
     **/
    public static Long getDeptId()
    {
        try
        {
            return getLoginUser().getDeptId();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取部门ID异常", HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * 获取用户账户
     **/
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new ServiceException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
