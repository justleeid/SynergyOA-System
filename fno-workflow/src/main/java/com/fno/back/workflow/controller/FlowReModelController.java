package com.fno.back.workflow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.fno.back.workflow.service.ActReModelService;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.persistence.entity.ModelEntityImpl;
import org.flowable.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程图Controller
 * 
 * @author fno
 * @date 2022-07-08
 */
@RestController
@RequestMapping("/oa/model")
public class FlowReModelController extends BaseController
{

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ActReModelService actReModelService;
    @Autowired
    private ObjectMapper objectMapper;

    @PreAuthorize("@ss.hasPermi('oa:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(ModelEntityImpl modelEntity) {
        startPage();
        Page<Model> list = actReModelService.list(modelEntity);
        return getDataTable(list);
    }


    /**
     * 创建模型
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public AjaxResult create(@RequestParam("name") String name, @RequestParam("key") String key, @RequestParam("category") String category,
                             @RequestParam(value = "description", required = false) String description) {
        try {
            actReModelService.create(name,key,category,description);
            return success( "创建模型成功");
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
        }
        return error();
    }

    /**
     * 根据Model部署流程
     */
    @RequestMapping(value = "/deploy/{modelId}")
    public AjaxResult deploy(@PathVariable("modelId") String modelId) {
        try {
            actReModelService.deploy(modelId);
            return success("部署成功");
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
        }
        return error("部署失败");
    }




    /**
     * 删除流程图
     */
    @PreAuthorize("@ss.hasPermi('oa:model:remove')")
    @Log(title = "流程图", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        try {
            actReModelService.remove(ids);
            return success();
        }catch (Exception e) {
            return error("删除失败");
        }
    }

    /****
     * 获取流程图数据xml
     * @param modelId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('oa:model:edit')")
    @Log(title = "流程图")
    @PostMapping("/getModelDetail")
    public AjaxResult getModelDetail(String modelId){
        Map<String, Object> data = actReModelService.getModelDetail(modelId);
        return success("查询成功",data);
    }




    //@Log(title = "流程图", businessType = BusinessType.DELETE)
    //@PostMapping("/getModelDetailByKey")
    //public AjaxResult getModelDetailByKey(String key){
    //    Map<String, Object> data = actReModelService.getModelDetailByKey(key);
    //    return success("查询成功",data);
    //}

    /****
     * 更新
     * @param modelId
     * @param values
     */
    @RequestMapping(value="/save/{modelId}", method = RequestMethod.POST)
    public AjaxResult saveModel(@PathVariable String modelId, @RequestBody LinkedHashMap<String,Object> values) {
        actReModelService.saveModel(modelId,values);
        return success();
    }


    /***
     * 获取能选择的用户列表
     * @param username
     * @return
     */
    @PostMapping(value="/getModelDesignerUserList")
    public AjaxResult getModelDesignerUserList(String username){
        List data = actReModelService.getModelDesignerUserList(username);
        return success("获取成功",data);
    }


    /***
     * 获取能选择的职位列表
     * @param username
     * @return
     */
    @PostMapping(value="/getModelDesignerPostList")
    public AjaxResult getModelDesignerPostList(String username){
        List data = actReModelService.getModelDesignerPostList(username);
        return success("获取成功",data);
    }
}
