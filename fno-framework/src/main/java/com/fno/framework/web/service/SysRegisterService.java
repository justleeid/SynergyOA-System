package com.fno.framework.web.service;

import com.fno.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fno.common.constant.CacheConstants;
import com.fno.common.constant.Constants;
import com.fno.common.constant.UserConstants;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.domain.model.RegisterBody;
import com.fno.common.core.redis.RedisCache;
import com.fno.common.exception.user.CaptchaException;
import com.fno.common.exception.user.CaptchaExpireException;
import com.fno.common.utils.MessageUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import com.fno.framework.manager.AsyncManager;
import com.fno.framework.manager.factory.AsyncFactory;
import com.fno.system.service.ISysConfigService;
import com.fno.system.service.ISysUserService;

import java.util.Map;

/**
 * 注册校验方法
 * 
 * @author ry
 */
@Component
public class SysRegisterService
{
    private static final Logger log = LoggerFactory.getLogger(SysRegisterService.class);

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
            Map tenant = userService.selectCompanyTenantByName(registerBody.getTenantName());
            if(tenant==null||tenant.get("id")==null){
                log.info("公司不存在："+registerBody.getTenantName());
                throw new ServiceException("公司不存在：" + registerBody.getTenantName());
            }else{
                String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();
                boolean captchaOnOff = configService.selectCaptchaOnOff();
                // 验证码开关
                if (captchaOnOff)
                {
                    validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
                }

                if (StringUtils.isEmpty(username))
                {
                    msg = "用户名不能为空";
                }
                else if (StringUtils.isEmpty(password))
                {
                    msg = "用户密码不能为空";
                }
                else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                        || username.length() > UserConstants.USERNAME_MAX_LENGTH)
                {
                    msg = "账户长度必须在2到20个字符之间";
                }
                else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                        || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
                {
                    msg = "密码长度必须在5到20个字符之间";
                }
                else if (userService.selectUserByUserName(username,Long.parseLong(tenant.get("id").toString()))!=null)
                {
                    msg = "保存用户'" + username + "'失败，注册账号已存在";
                }
                else
                {
                    SysUser sysUser = new SysUser();
                    sysUser.setTenantId(Long.parseLong(tenant.get("id").toString()));
                    sysUser.setUserName(username);
                    sysUser.setNickName(username);
                    sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
                    boolean regFlag = userService.registerUser(sysUser);
                    if (!regFlag)
                    {
                        msg = "注册失败,请联系系统管理人员";
                    }
                    else
                    {
                        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.REGISTER,
                                MessageUtils.message("user.register.success")));
                    }
                }
                return msg;
            }
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
