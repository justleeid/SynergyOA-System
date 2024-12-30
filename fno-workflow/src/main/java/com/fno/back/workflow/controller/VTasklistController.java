package com.fno.back.workflow.controller;

import com.fno.back.workflow.service.VTasklistService;
import com.fno.back.workflow.domain.CommitTask;
import com.fno.back.workflow.domain.FlowProcessInstance;
import com.fno.back.workflow.domain.VTasklist;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.StringUtils;
import com.fno.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 我的待办Controller
 * 
 * @author fno
 * @date 2022-07-16
 */
@RestController
@RequestMapping("/oa/myTask")
public class VTasklistController extends BaseController
{
    @Autowired
    private VTasklistService vTasklistService;


    /****
     * 查询首页顶部，未处理消息数量
     * @return
     */
    @GetMapping("/queryTodoTaskCount")
    public AjaxResult queryTodoTaskCount(){
        int c = vTasklistService.queryTodoTaskCount();
        return success(c);
    }

    /**
     * 查询我的待办列表
     */
    @PreAuthorize("@ss.hasPermi('oa:myTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(VTasklist vTasklist)
    {
        startPage();
        List<VTasklist> list = vTasklistService.selectVTasklistList(vTasklist);
        return getDataTable(list);
    }

    /**
     * 导出我的待办列表
     */
    @PreAuthorize("@ss.hasPermi('oa:myTask:export')")
    @Log(title = "我的待办", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VTasklist vTasklist)
    {
        List<VTasklist> list = vTasklistService.selectVTasklistList(vTasklist);
        ExcelUtil<VTasklist> util = new ExcelUtil<VTasklist>(VTasklist.class);
        util.exportExcel(response, list, "我的待办数据");
    }

    /**
     * 获取我的待办详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:myTask:query')")
    @GetMapping(value = "/{taskid}")
    public AjaxResult getInfo(@PathVariable("taskid") String taskid)
    {
        return AjaxResult.success(vTasklistService.selectVTasklistByTaskid(taskid));
    }


    /**
     * 查询审批历史
     */
    @PreAuthorize("@ss.hasPermi('oa:myTask:list')")
    @GetMapping("/getHisByInsId/{insId}")
    public AjaxResult getHisByInsId(@PathVariable("insId") String insId)
    {
        List<VTasklist> list = vTasklistService.selectHisByInsId(insId);
        return AjaxResult.success(list);
    }

    /****
     * 查看审批历史
     * @param flowProcessInstance
     * @return
     */
    @PostMapping("/getHisByBusinessId")
    public AjaxResult getHisByBusinessId(@RequestBody FlowProcessInstance flowProcessInstance)
    {
        List<VTasklist> list = vTasklistService.getHisByBusinessId(flowProcessInstance.getBusinessId(),flowProcessInstance.getBillType());
        return AjaxResult.success(list);
    }


    /**
     * 查询我的待办列表
     */
    @PreAuthorize("@ss.hasPermi('oa:myTask:list')")
    @GetMapping("/todoTaskList")
    public TableDataInfo todoTaskList(String defkey,String defname)
    {
        startPage();
        String key = StringUtils.isNotBlank(defkey)?defkey:"";
        String name = StringUtils.isNotBlank(defname)?defname:"";
        List<VTasklist> list = vTasklistService.selectTodoTaskList(getLoginUser().getUserId().toString(),key,name);
        return getDataTable(list);
    }


    /****
     * 查询我的已办任务列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('oa:myTask:list')")
    @GetMapping("/haveDoneTaskList")
    public TableDataInfo selectHaveDoneTaskList(String defkey,String defname){
        startPage();
        String key = StringUtils.isNotBlank(defkey)?defkey:"";
        String name = StringUtils.isNotBlank(defname)?defname:"";
        List<Map> list = vTasklistService.selectHaveDoneTaskList(getLoginUser().getUserId().toString(),key,name);
        return getDataTable(list);
    }



    /******
     * 提交任务
     * @param
     * @return
     */
    @PostMapping(value = "/commitTask")
    public AjaxResult commitTask(@RequestBody CommitTask task){
        vTasklistService.commitTask(task);
        return success();
    }


    /******
     * 驳回任务
     * @param
     * @return
     */
    @PostMapping(value = "/rejectTask")
    public AjaxResult rejectTask(@RequestBody CommitTask task){
        vTasklistService.rejectTask(task.getTaskId(),task.getInsId(),task.getComment());
        return success();
    }

}
