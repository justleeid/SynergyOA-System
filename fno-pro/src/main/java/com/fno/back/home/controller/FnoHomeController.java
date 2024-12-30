package com.fno.back.home.controller;

import com.fno.back.home.service.HomeService;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 * @des
 * @author Ly
 * @date 2023/6/4
 */
@RestController
@RequestMapping("/home")
public class FnoHomeController extends BaseController {

    @Autowired
    private HomeService homeService;

    /****
     * 首页数量统计
     * @return
     */
    @GetMapping("/getItemCountData")
    public AjaxResult getItemCountData(){
        Map<String,Long> map = homeService.getItemCountData();
        return success(map);
    }

    @GetMapping("/queryLastUpdateList")
    public AjaxResult queryLastUpdateList(){
        return success(homeService.queryLastUpdateList());
    }
}
