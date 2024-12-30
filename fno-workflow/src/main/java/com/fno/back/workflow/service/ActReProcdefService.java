package com.fno.back.workflow.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fno.back.workflow.domain.ActReProcdef;
import com.fno.back.workflow.mapper.ActReProcdefMapper;
import com.fno.common.core.text.Convert;
import com.fno.back.common.util.Base64Util;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.constants.ModelDataJsonConstants;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;

@Transactional
@Service
public class ActReProcdefService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ActReProcdefMapper actReProcdefMapper;


    /**
     * 分页查询流程定义文件
     * @return
     */
    public List<ActReProcdef> listProcessDefinition(ActReProcdef actReProcdef) {
        actReProcdef.setTenantId(SecurityUtils.getTenantId());
        List<ActReProcdef> list = actReProcdefMapper.selectAllProcDefList(actReProcdef);
        for (ActReProcdef definition: list) {
            definition.setDescription("null".equals(definition.getDescription())?"":definition.getDescription());
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(definition.getDeploymentId()).singleResult();
            definition.setDeploymentTime(deployment.getDeploymentTime());
        }
        return list;
    }

    public void deployProcessDefinition(String filePath) throws FileNotFoundException {
        if (StringUtils.isNotBlank(filePath)) {
            if (filePath.endsWith(".zip")) {
                ZipInputStream inputStream = new ZipInputStream(new FileInputStream(filePath));
                repositoryService.createDeployment()
                        .addZipInputStream(inputStream)
                        .deploy();
            } else if (filePath.endsWith(".bpmn")) {
                repositoryService.createDeployment()
                        .addInputStream(filePath, new FileInputStream(filePath))
                        .deploy();
            }
        }
    }

    /*****
     * 删除
     * @param deploymentIds
     * @return
     * @throws Exception
     */
    public int deleteProcessDeploymentByIds(String deploymentIds) throws Exception {
        String[] deploymentIdsArr = Convert.toStrArray(deploymentIds);
        int counter = 0;
        for (String deploymentId: deploymentIdsArr) {
            List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery()
                    .deploymentId(deploymentId)
                    .list();
            if (!CollectionUtils.isEmpty(instanceList)) {
                // 存在流程实例的流程定义
                throw new Exception("删除失败，存在运行中的流程实例");
            }
            repositoryService.deleteDeployment(deploymentId, true); // true 表示级联删除引用，比如 act_ru_execution 数据
            counter++;
        }
        return counter;
    }

    /****
     * 挂起和激活
     * @param id
     * @param suspensionState
     */
    public void suspendOrActiveApply(String id, String suspensionState) {
        if ("1".equals(suspensionState)) {
            // 当流程定义被挂起时，已经发起的该流程定义的流程实例不受影响（如果选择级联挂起则流程实例也会被挂起）。
            // 当流程定义被挂起时，无法发起新的该流程定义的流程实例。
            // 直观变化：act_re_procdef 的 SUSPENSION_STATE_ 为 2
            repositoryService.suspendProcessDefinitionById(id);
        } else if ("2".equals(suspensionState)) {
            repositoryService.activateProcessDefinitionById(id);
        }
    }

    /****
     * 读取流程资源
     * @param processDefinitionId
     * @param resourceName
     * @return
     */
    public String readResourceImg(String processDefinitionId, String resourceName) throws IOException {
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
        return Base64Util.byte2Base64(output.toByteArray());
    }

    /***
     * 转化为模型
     * @param processDefinitionId
     * @throws UnsupportedEncodingException
     * @throws XMLStreamException
     */
    public void convertToModel(String processDefinitionId) throws UnsupportedEncodingException, XMLStreamException {
        org.flowable.engine.repository.ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).singleResult();
        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                processDefinition.getResourceName());
        XMLInputFactory xif = XMLInputFactory.newInstance();
        InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
        XMLStreamReader xtr = xif.createXMLStreamReader(in);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getName());
        //设置租户ID
        modelData.setTenantId(processDefinition.getTenantId());
        modelData.setCategory(processDefinition.getCategory());//.getDeploymentId());
        modelData.setDeploymentId(processDefinition.getDeploymentId());
        modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count()+1)));
        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());
        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
    }


    public List<ActReProcdef> selectAllFlowList(){
        return actReProcdefMapper.selectAllFlowList();
    }
}
