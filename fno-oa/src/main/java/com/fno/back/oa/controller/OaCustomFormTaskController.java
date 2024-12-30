package com.fno.back.oa.controller;

import com.fno.back.oa.domain.OaCustomFormApply;
import com.fno.back.oa.service.OaTaskService;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 我的待办Controller
 *
 * @author fno
 * @date 2022-07-16
 */
@RestController
@RequestMapping("/oa/oaCustomFormTask")
public class OaCustomFormTaskController extends BaseController
{
    @Autowired
    private OaTaskService oaTaskService;

    /**
     * 获取我的待办详细信息
     */
    @GetMapping(value = "/getCustomFormByTaskId/{taskId}")
    public AjaxResult getCustomFormByTaskId(@PathVariable("taskId") String taskId)
    {

        OaCustomFormApply oaCustomFormApply =  oaTaskService.getCustomFormByTaskId(taskId);

        return AjaxResult.success(oaCustomFormApply);
    }

}
