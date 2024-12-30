package com.fno.back.workflow.controller;

import cn.hutool.core.util.ArrayUtil;
import com.fno.back.workflow.service.ActReProcdefService;
import com.fno.back.workflow.domain.ActReProcdef;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 流程定义Controller
 * 
 * @author fno
 * @date 2022-07-07
 */
@RestController
@RequestMapping("/oa/definition")
public class FlowReProcdefController extends BaseController
{
    @Autowired
    private ActReProcdefService actReProcdefService;
    @Autowired
    private RepositoryService repositoryService;
    /**
     * 查询流程定义列表
     */
    @PreAuthorize("@ss.hasPermi('oa:definition:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActReProcdef actReProcdef)
    {
        startPage();
        List<ActReProcdef> list = actReProcdefService.listProcessDefinition(actReProcdef);
        return getDataTable(list);
    }


    /**
     * 删除流程定义
     */
    @PreAuthorize("@ss.hasPermi('oa:definition:remove')")
    @Log(title = "流程定义", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {

        try {
            return toAjax(actReProcdefService.deleteProcessDeploymentByIds(ArrayUtil.join(ids,",")));
        }
        catch (Exception e) {
            return error(e.getMessage());
        }
    }


    /*****
     * 激活挂起
     * @param actReProcdef
     * @return
     */
    @PostMapping( "/suspendOrActiveApply")
    @ResponseBody
    public AjaxResult suspendOrActiveApply(@RequestBody ActReProcdef actReProcdef) {
        actReProcdefService.suspendOrActiveApply(actReProcdef.getId(), actReProcdef.getSuspensionState().toString());
        return success();
    }


    /**
     * 读取流程资源
     *
     * @param processDefinitionId 流程定义ID
     * @param resourceName        资源名称
     */
    @RequestMapping(value = "/readResourceImg")
    public AjaxResult readResourceImg(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName)
            throws Exception {
        String imgBase64 = actReProcdefService.readResourceImg(processDefinitionId,resourceName);
        return AjaxResult.success("操作成功", imgBase64);
    }

    /**
     * 读取流程资源
     *
     * @param processDefinitionId 流程定义ID
     * @param resourceName        资源名称
     */
    @RequestMapping(value = "/readResourceXml")
    public AjaxResult readResourceXml(@RequestParam("pdid") String processDefinitionId, @RequestParam("resourceName") String resourceName)
            throws Exception {
        ProcessDefinitionQuery pdq = repositoryService.createProcessDefinitionQuery();
        org.flowable.engine.repository.ProcessDefinition pd = pdq.processDefinitionId(processDefinitionId).singleResult();
        // 通过接口读取
        InputStream resourceAsStream = repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            output.write(b, 0, len);
        }
        return AjaxResult.success("操作成功", output.toString());
    }



    /**
     * 转换流程定义为模型
     * @param processDefinitionId
     * @return
     * @throws UnsupportedEncodingException
     * @throws XMLStreamException
     */
    @PostMapping(value = "/convert2Model")
    @ResponseBody
    public AjaxResult convertToModel(@RequestParam("processDefinitionId") String processDefinitionId)
            throws UnsupportedEncodingException, XMLStreamException {
        actReProcdefService.convertToModel(processDefinitionId);
        return success();
    }


    /****
     * 获取所有流程
     * @return
     */
    @PostMapping("getAllFlowList")
    public AjaxResult getAllFlowList(){
        List<ActReProcdef> data = actReProcdefService.selectAllFlowList();
        return success("获取成功",data);
    }
}
