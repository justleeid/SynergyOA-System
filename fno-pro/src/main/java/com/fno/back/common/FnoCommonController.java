package com.fno.back.common;

import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * @des
 * @author Ly
 * @date 2021/7/11
 */
@Controller
@RequestMapping("/mobile/common")
@ResponseBody
public class FnoCommonController extends BaseController {


    @Autowired
    private ISysConfigService configService;

    /***
     * 根基key获取系统参数
     * @param key
     * @return
     */
    @PostMapping("/getSystemConfigValue")
    public AjaxResult getSystemConfigValue(String key) {
        String value = configService.selectConfigByKey(key);
        return success("成功", value);
    }


}
