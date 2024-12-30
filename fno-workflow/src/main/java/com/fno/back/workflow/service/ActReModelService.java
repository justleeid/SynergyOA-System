package com.fno.back.workflow.service;


import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import com.fno.common.annotation.DataScope;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.page.PageDomain;
import com.fno.common.core.page.TableSupport;
import com.fno.back.common.service.base.BaseService;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.domain.SysPost;
import com.fno.system.mapper.SysPostMapper;
import com.fno.system.mapper.SysRoleMapper;
import com.fno.system.mapper.SysUserMapper;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.editor.constants.ModelDataJsonConstants;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.persistence.entity.ModelEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.engine.repository.ModelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;

import static org.flowable.editor.constants.ModelDataJsonConstants.MODEL_NAME;

/**
 * 自定义单Service业务层处理
 *
 * @author fno
 * @date 2022-07-05
 */
@Service
public class ActReModelService extends BaseService {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPostMapper sysPostMapper;
    /****
     * 获取列表
     * @param modelEntity
     * @return
     */
    public Page<Model> list(ModelEntityImpl modelEntity) {
        ModelQuery modelQuery = repositoryService.createModelQuery();
        modelQuery.orderByLastUpdateTime().desc();
        //租户ID
        modelQuery.modelTenantId(SecurityUtils.getTenantId().toString());
        // 条件过滤
        if (com.fno.common.utils.StringUtils.isNotBlank(modelEntity.getKey())) {
            modelQuery.modelKey(modelEntity.getKey());
        }
        if (com.fno.common.utils.StringUtils.isNotBlank(modelEntity.getName())) {
            modelQuery.modelNameLike("%" + modelEntity.getName() + "%");
        }
        if (com.fno.common.utils.StringUtils.isNotBlank(modelEntity.getCategory())) {
            modelQuery.modelCategory(modelEntity.getCategory());
        }
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        List<Model> resultList = modelQuery.listPage((pageNum - 1) * pageSize, pageSize);
        Page<Model> list = new Page<>();
        list.addAll(resultList);
        list.setTotal(modelQuery.count());
        list.setPageNum(pageNum);
        list.setPageSize(pageSize);
        return list;
    }


    /**
     * 创建模型
     */
    public void create(String name, String key,String category, String description) {
        try {
            //ObjectMapper objectMapper = new ObjectMapper();
            //ObjectNode editorNode = objectMapper.createObjectNode();
            //editorNode.put("id", "canvas");
            //editorNode.put("resourceId", "canvas");
            //ObjectNode stencilSetNode = objectMapper.createObjectNode();
            //stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            //editorNode.put("stencilset", stencilSetNode);
            //ObjectNode modelObjectNode = objectMapper.createObjectNode();
            //modelObjectNode.put(MODEL_NAME, name);
            //modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            //description = StringUtils.defaultString(description);
            //modelObjectNode.put(MODEL_DESCRIPTION, description);
            JSONObject metaObj = new JSONObject();
            metaObj.set(ModelDataJsonConstants.MODEL_NAME,name);
            metaObj.set(ModelDataJsonConstants.MODEL_REVISION,1);
            metaObj.set(ModelDataJsonConstants.MODEL_DESCRIPTION,StringUtils.defaultString(description));
            String xml  = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                                "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" " +
                                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                                "xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" " +
                                "xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" " +
                                "xmlns:bioc=\"http://bpmn.io/schema/bpmn/biocolor/1.0\" " +
                                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                                "targetNamespace=\""+category+"\">\n" +
                    "  <process id=\""+key+"\" name=\""+name+"\" flowable:processCategory=\""+category+"\">\n" +
                    "<documentation>"+description+"</documentation>"+
                    "    <startEvent id=\"startNode1\" name=\"开始\" />\n" +
                    "  </process>\n" +
                    "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_"+key+"\">\n" +
                    "    <bpmndi:BPMNPlane id=\"BPMNPlane_"+key+"\" bpmnElement=\"process_"+ UUID.fastUUID() +"\">\n" +
                    "      <bpmndi:BPMNShape id=\"BPMNShape_startNode1\" bpmnElement=\"startNode1\" bioc:stroke=\"\">\n" +
                    "        <omgdc:Bounds x=\"240\" y=\"200\" width=\"30\" height=\"30\" />\n" +
                    "        <bpmndi:BPMNLabel>\n" +
                    "          <omgdc:Bounds x=\"242\" y=\"237\" width=\"23\" height=\"14\" />\n" +
                    "        </bpmndi:BPMNLabel>\n" +
                    "      </bpmndi:BPMNShape>\n" +
                    "    </bpmndi:BPMNPlane>\n" +
                    "  </bpmndi:BPMNDiagram>\n" +
                    "</definitions>\n";

            Model newModel = repositoryService.newModel();
            newModel.setMetaInfo(metaObj.toString());
            newModel.setName(name);
            newModel.setCategory(category);
            newModel.setTenantId(SecurityUtils.getTenantId().toString());
            newModel.setKey(StringUtils.defaultString(key));
            repositoryService.saveModel(newModel);
            //保存二进制文件，存入数据库，act_ge_bytearray表中，source为xml，source-extra为png图片
            repositoryService.addModelEditorSource(newModel.getId(), xml.getBytes("utf-8"));
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
        }
    }

    /**
     * 根据Model部署流程
     */
    public void deploy(String modelId) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            //ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            //byte[] bpmnBytes = null;
            //BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            //bpmnBytes = new BpmnXMLConverter().convertToXML(model);
            String processName = modelData.getName() + ".bpmn20.xml";
            String modelPngName = modelData.getName() + "."+modelData.getKey()+".png";
            //此处的category设置分类，不起作用。数据库中还是用的targetSpace。解决办法为，保存流程图时候，将http://www.activiti.org/processdef替换为分类编号
            //部署的时候，会生成png的和xml的resource，但是png的图片会位移。使用svg的图片
            Deployment deployment = repositoryService.createDeployment().category(modelData.getCategory()).name(modelData.getName()).tenantId(SecurityUtils.getTenantId().toString())
                    .addBytes(modelPngName,repositoryService.getModelEditorSourceExtra(modelData.getId()))
                    .addString(processName, new String(repositoryService.getModelEditorSource(modelData.getId()), "UTF-8"))
                    .deploy();
        } catch (Exception e) {
            logger.error("根据模型部署流程失败：modelId={}", modelId, e);
        }
    }



    /**
     * 删除流程图
     */
    public void remove(String[] ids)
    {
        try {
            for(String id:ids){//这里没有管事务，后面有时间再处理，【暂时设计不支持批量删除，不存在事务问题】
                repositoryService.deleteModel(id);
            }
        }catch (Exception e) {
            logger.error("删除流程图失败");
        }
    }

    /****
     * 获取流程图数据xml
     * @param modelId
     * @return
     */
    public Map<String, Object> getModelDetail(String modelId){
        Map<String, Object> data = new HashMap<>();
        String xml = "";
        try {
            Model modelData = repositoryService.getModel(modelId);
            data.put("name",modelData.getName());
            data.put("description", JSONUtil.parseObj(modelData.getMetaInfo()).getStr("description"));
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            //JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            //BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            // 流程非空判断
            //if (!CollectionUtils.isEmpty(bpmnModel.getProcesses())) {
            //    BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            //    byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);
            xml = new String(repositoryService.getModelEditorSource(modelData.getId()));
            data.put("xml",xml);
            //}
        }catch (Exception e) {
            logger.error("导出model的xml文件失败：modelId={}", modelId, e);
        }
        return data;
    }

    /****
     * 更新
     * @param modelId
     * @param values
     */
    public void saveModel(String modelId, LinkedHashMap<String,Object> values) {
        try {
            LinkedHashMap<String,String> info = (LinkedHashMap)(values.get("process"));
            String key = (String)(info.get("id"));
            String category = (String)(info.get("category"));
            String name = (String)(info.get("name"));
            Model model = repositoryService.getModel(modelId);
            ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, name);
            model.setMetaInfo(modelJson.toString());
            model.setName(name);
            model.setCategory(category);
            model.setKey(key);
            repositoryService.saveModel(model);
            String xml = (String)values.get("xml");
            xml = xml.replace("targetNamespace=\"http://www.activiti.org/processdef\"","targetNamespace=\""+category+"\"");
            repositoryService.addModelEditorSource(model.getId(), xml.getBytes("utf-8"));
            InputStream svgStream = new ByteArrayInputStream(((String)(values.get("svg"))).getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);
            PNGTranscoder transcoder = new PNGTranscoder();
            // Setup output
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);
            // Do the transformation
            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);
            outStream.close();
        } catch (Exception e) {
            logger.error("保存流程图失败", e);
            throw new FlowableException("保存流程图失败", e);
        }
    }


    /***
     * 获取能选择的用户列表
     * @param username
     * @return
     */
    @DataScope(deptAlias = "d", userAlias = "u")
    public List getModelDesignerUserList(String username){
        SysUser query = new SysUser();
        query.setTenantId(SecurityUtils.getTenantId());
        List<SysUser> list = userMapper.selectUserList(query);
        List<Map<String,String>> data = new ArrayList<>();
        for(SysUser user:list){
            Map item = new HashMap();
            item.put("id",user.getUserId().toString());
            String name = user.getNickName();
            if(user.getDept()!=null){
                name = name + "("+user.getDept().getDeptName()+")";
            }
            name = name + "("+user.getUserName()+")";
            item.put("name",name);
            data.add(item);
        }
        return data;
    }


    /***
     * 获取能选择的角色列表
     * @param username
     * @return
     */
    public List getModelDesignerPostList(String username){
        List<SysPost> list = sysPostMapper.selectPostAll(SecurityUtils.getTenantId());
        List<Map<String,String>> data = new ArrayList<>();
        for(SysPost post:list){
            Map item = new HashMap();
            item.put("id",post.getPostId().toString());
            item.put("name",post.getPostName()+"("+post.getPostCode()+")");
            data.add(item);
        }
        return data;
    }
}
