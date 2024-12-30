package com.fno.back.common.util;

import cn.hutool.json.JSONObject;
import com.fno.back.common.constant.CommonConstants;

import java.util.HashMap;
import java.util.Map;

/***
 * @des
 * @author Ly
 * @date 2022/7/17
 */

public class FlowUtil {

    /****
     * 组装通用申请的流程变量
     * @param title
     * @param userId
     * @param nickname
     * @param applytime
     * @param iscustomform
     * @return
     */
    public static Map<String,Object> buildFlowVars(String title, Long userId, String nickname, String applytime, String iscustomform,String appStatus){
        Map<String,Object> vars = new HashMap<>();
        JSONObject obj = new JSONObject();
        obj.set(CommonConstants.APPLY_VARS_TITLE,title);
        obj.set(CommonConstants.APPLY_VARS_USERID,userId);
        obj.set(CommonConstants.APPLY_VARS_NICKNAME,nickname);
        obj.set(CommonConstants.APPLY_VARS_APPLYTIME,applytime);
        obj.set(CommonConstants.APPLY_VARS_ISCUSTOMFORM, iscustomform);
        obj.set(CommonConstants.APPLY_VARS_APPSTATUS, appStatus);
        //obj.set(CommonConstants.APPLY_VARS_BUSINESSTABLENAME,businessTableName);
        //流程变量
        vars.put(CommonConstants.APPLY_VARS_ORDERINFO,obj.toString());//单据信息
        return vars;
    }

}
