package com.fno.api.cms.controller;

import com.fno.common.annotation.Anonymous;
import com.fno.common.constant.Constants;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.domain.model.LoginBody;
import com.fno.common.core.domain.model.LoginUser;
import com.fno.common.exception.ServiceException;
import com.fno.common.exception.user.UserPasswordNotMatchException;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.MessageUtils;
import com.fno.common.utils.ServletUtils;
import com.fno.common.utils.ip.IpUtils;
import com.fno.framework.manager.AsyncManager;
import com.fno.framework.manager.factory.AsyncFactory;
import com.fno.framework.web.service.TokenService;
import com.fno.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 * @des
 * @author Ly
 * @date 2023/4/5
 */
@RestController
@RequestMapping("/cms")
public class ApiCmsLoginController extends BaseController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private ISysUserService userService;
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 前台用户登录
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @Anonymous
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public String login(String username, String password)
    {
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }


    public void recordLoginInfo(Long userId)
    {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Anonymous
    @PostMapping("/getInfo")
    public AjaxResult getInfo()
    {
        //SysUser user = SecurityUtils.getLoginUser().getUser();
        SysUser u = userService.selectUserById(getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", u);
        return ajax;
    }


}
