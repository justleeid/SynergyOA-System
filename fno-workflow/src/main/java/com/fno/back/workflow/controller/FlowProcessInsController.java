package com.fno.back.workflow.controller;

import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.back.workflow.domain.FlowProcessInstance;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @des
 * @author Ly
 * @date 2023/5/31
 */
@RestController
@RequestMapping("/oa/processInstance")
public class FlowProcessInsController extends BaseController {

    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;

    /*****
     * 提交流程
     * @param ins
     * @return
     * @throws Exception
     */
    @RequestMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody FlowProcessInstance ins) throws Exception {
        flowProcessInstanceService.submitFlow(ins);
        return success();
    }


    /*****
     * 取回流程
     * @param ins
     * @return
     * @throws Exception
     */
    @RequestMapping("/cancleFlow")
    public AjaxResult cancleFlow(@RequestBody FlowProcessInstance ins) throws Exception {
        flowProcessInstanceService.cancleFlow(ins);
        return success();
    }


    /****
     * 清空所有流程实例
     * @return
     */
    @RequestMapping("/deleteAllProcessInstance")
    public AjaxResult deleteAllProcessInstance(){
        flowProcessInstanceService.deleteAllProcessInstance();
        return success();
    }




    /****
     * 清空所有流程实例
     * @return
     */
    @RequestMapping("/deleteProcessInstanceById")
    public AjaxResult deleteProcessInstanceById(String insId){
        flowProcessInstanceService.deleteProcessInstanceById(insId);
        return success();
    }





}
