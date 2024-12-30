package com.fno.back.workflow.controller;

import com.fno.back.workflow.service.FlowImgService;
import com.fno.back.workflow.domain.FlowProcessInstance;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @des
 * @author Ly
 * @date 2022/7/16
 */
@RestController
@RequestMapping("/oa/flowImg")
public class FlowImgController extends BaseController {

    @Autowired
    private FlowImgService flowImgService;

    /****
     * 获取流程图-图片---会渲染进度--高亮显示
     * @param insId
     * @return
     */
    @Log(title = "获取流程图-图片")
    @PostMapping("/getFlowImgByInsId")
    public AjaxResult getFlowImgByInsId(String insId) throws Exception {
        String base64Img = flowImgService.generateImageByProcInstId(insId);
        return AjaxResult.success("查询成功", base64Img);
    }


    /****
     * 获取流程图-图片
     * @param insId
     * @return
     */
    @Log(title = "获取流程图-图片")
    @PostMapping("/getDefImgByInsId")
    public AjaxResult getDefImgByInsId(String insId) throws Exception {
        String base64Img = flowImgService.getDefImgByInsId(insId);
        return AjaxResult.success("查询成功", base64Img);
    }



    /****
     * 获取流程图-图片
     * @param flowProcessInstance
     * @return
     */
    @Log(title = "获取流程图-图片")
    @PostMapping("/getDefImgByBusinessId")
    public AjaxResult getDefImgByBusinessId(@RequestBody FlowProcessInstance flowProcessInstance) throws Exception {
        String base64Img = flowImgService.getDefImgByBusinessId(flowProcessInstance.getBusinessId(),flowProcessInstance.getBillType());
        return AjaxResult.success("查询成功", base64Img);
    }



}
