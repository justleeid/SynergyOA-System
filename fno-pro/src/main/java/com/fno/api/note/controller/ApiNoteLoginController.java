package com.fno.api.note.controller;

import com.fno.api.note.domain.ModifyPassword;
import com.fno.api.note.service.NoteLoginService;
import com.fno.common.annotation.Anonymous;
import com.fno.common.constant.Constants;
import com.fno.common.constant.UserConstants;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.domain.model.LoginBody;
import com.fno.common.exception.user.UserPasswordNotMatchException;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * @des
 * @author Ly
 * @date 2023/4/20
 */
@Controller
@RequestMapping("/note")
@ResponseBody
public class ApiNoteLoginController extends BaseController {


    @Autowired
    private NoteLoginService loginService;
    @Autowired
    private ISysUserService userService;

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
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Anonymous
    @PostMapping("/getInfo")
    public AjaxResult getInfo()
    {
        SysUser u = userService.selectUserById(getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", u);
        return ajax;
    }


    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Anonymous
    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUser user)
    {

        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName(),false)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'注册失败，账号已存在");
        }
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setNickName("云笔记匿名用户");
        return toAjax(userService.insertUser(user));

    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Anonymous
    @PostMapping("/savePassword")
    public AjaxResult savePassword(@RequestBody ModifyPassword modifyPassword){

        SysUser user = SecurityUtils.getLoginUser().getUser();


        try{
            loginService.validPassword(user.getUserName(), modifyPassword.getOldPassword());
        }catch(UserPasswordNotMatchException e){
            return error("老密码不正确");
        }catch (Exception e){
            return error("修改密码失败");
        }

        SysUser u = new SysUser();
        u.setUserId(user.getUserId());
        u.setPassword(SecurityUtils.encryptPassword(modifyPassword.getNewPassword()));
        userService.updateUser(u);

        return success();
    }

}
