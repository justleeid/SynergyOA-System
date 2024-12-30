package com.fno.web.controller.monitor;

import java.util.*;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fno.common.annotation.Log;
import com.fno.common.constant.CacheConstants;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.domain.model.LoginUser;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.core.redis.RedisCache;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.StringUtils;
import com.fno.system.domain.SysUserOnline;
import com.fno.system.service.ISysUserOnlineService;

/**
 * 在线用户监控
 * 
 * @author ry
 */
@RestController
@RequestMapping("/monitor/online")
public class SysUserOnlineController extends BaseController
{
    @Autowired
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private RedisCache redisCache;

    @PreAuthorize("@ss.hasPermi('monitor:online:list')")
    @GetMapping("/list")
    public TableDataInfo list(String ipaddr, String userName)
    {
        Collection<String> keys = redisCache.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys)
        {
            LoginUser user = new LoginUser();
            if(redisCache.getCacheObject(key) instanceof LoginUser){
                user = redisCache.getCacheObject(key);
            }else if(redisCache.getCacheObject(key) instanceof JSONObject){
                JSONObject obj = redisCache.getCacheObject(key);
                user.setBrowser(obj.getString("browser"));
                user.setDeptId(obj.getLong("deptId"));
                user.setExpireTime(obj.getLong("expireTime"));
                user.setIpaddr(obj.getString("ipaddr"));
                user.setLoginLocation(obj.getString("loginLocation"));
                user.setLoginTime(obj.getLong("loginTime"));
                user.setOs(obj.getString("os"));
                user.setPermissions((Set<String>)obj.get("permissions"));
                user.setToken(obj.getString("token"));
                user.setUserId(obj.getLong("userId"));
            }

            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
                }
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
                }
            }
            else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser()))
            {
                if (StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            }
            else
            {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(userOnlineList);
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId)
    {
        redisCache.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + tokenId);
        return AjaxResult.success();
    }
}
