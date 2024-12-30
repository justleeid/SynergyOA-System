package com.fno.back.oa.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fno.back.common.domain.SysBillType;
import com.fno.back.oa.domain.OaCustomFormApply;
import com.fno.back.oa.mapper.OaCustomFormApplyMapper;
import com.fno.back.common.constant.BillTypeConstants;
import com.fno.back.common.constant.CommonConstants;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.back.common.service.SysBillTypeService;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.domain.model.LoginUser;
import com.fno.back.common.service.base.BaseService;
import com.fno.back.common.util.FlowUtil;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.mapper.SysUserMapper;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 自定义表单申请Service业务层处理
 * 
 * @author fno
 * @date 2022-07-09
 */
@Service
public class OaCustomFormApplyService extends BaseService
{
    @Autowired
    private OaCustomFormApplyMapper oaCustomFormApplyMapper;
    @Autowired
    private OaCustomFormApplyService oaCustomFormApplyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysBillTypeService sysBillTypeService;

    /**
     * 查询自定义表单申请
     * 
     * @param id 自定义表单申请主键
     * @return 自定义表单申请
     */
    public OaCustomFormApply selectOaCustomFormApplyById(Long id)
    {
        return oaCustomFormApplyMapper.selectOaCustomFormApplyById(id);
    }

    /**
     * 查询自定义表单申请列表
     * 
     * @param oaCustomFormApply 自定义表单申请
     * @return 自定义表单申请
     */
    public List<OaCustomFormApply> selectOaCustomFormApplyList(OaCustomFormApply oaCustomFormApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaCustomFormApply.setCreateBy(SecurityUtils.getUserId());
        }
        oaCustomFormApply.setTenantId(SecurityUtils.getTenantId());
        return oaCustomFormApplyMapper.selectOaCustomFormApplyList(oaCustomFormApply);
    }

    /**
     * 新增自定义表单申请
     * 
     * @param oaCustomFormApply 自定义表单申请
     * @return 结果
     */
    @Transactional
    public Long insertOaCustomFormApply(OaCustomFormApply oaCustomFormApply, LoginUser loginUser)
    {

        SysBillType sysBillType = sysBillTypeService.selectOaBillTypeByBillType(BillTypeConstants.CUSTOM_FORM);

        oaCustomFormApply.setCreateBy(loginUser.getUserId());
        oaCustomFormApply.setApplyTitle("用户【"+loginUser.getUser().getNickName()+"】申请的【"+oaCustomFormApply.getName()+"单】");
        oaCustomFormApply.setCreateTime(DateUtils.getNowDate());
        String status = oaCustomFormApply.getStatus();//前台传过来的参数
        oaCustomFormApply.setStatus("0");//为了避开校验，提交成功后，后面再进行更新
        oaCustomFormApply.setBillType(sysBillType.getBillType());
        //租户ID
        oaCustomFormApply.setTenantId(SecurityUtils.getTenantId());
        int i = oaCustomFormApplyMapper.insertOaCustomFormApply(oaCustomFormApply);
        String insId = "";
        if("1".equals(status)){
            SysUser user = sysUserMapper.selectUserById(oaCustomFormApply.getCreateBy());
            Map<String,Object> vars = FlowUtil.buildFlowVars(oaCustomFormApply.getApplyTitle(),oaCustomFormApply.getCreateBy(),user.getNickName(),
                    DateUtil.formatDateTime(oaCustomFormApply.getCreateTime()), CommonConstants.YES , sysBillType.getAppStatus());
            String businessKey = sysBillType.getBillType()+":"+ sysBillType.getTableName()+":"+oaCustomFormApply.getId();
            insId = oaCustomFormApplyService.submitApply(oaCustomFormApply.getFlowKey(),businessKey ,vars);
            oaCustomFormApply.setFlowInsId(insId);
            OaCustomFormApply updateApply = new OaCustomFormApply();
            updateApply.setId(oaCustomFormApply.getId());
            updateApply.setFlowInsId(insId);
            updateApply.setStatus("1");//更新状态
            oaCustomFormApplyMapper.updateOaCustomFormApply(updateApply);
        }
        return oaCustomFormApply.getId();
    }

    /**
     * 修改自定义表单申请
     * 
     * @param oaCustomFormApply 自定义表单申请
     * @return 结果
     */
    @Transactional
    public int updateOaCustomFormApply(OaCustomFormApply oaCustomFormApply, LoginUser loginUser)
    {
        SysBillType sysBillType = sysBillTypeService.selectOaBillTypeByBillType(BillTypeConstants.CUSTOM_FORM);

        oaCustomFormApply.setUpdateBy(loginUser.getUserId());
        oaCustomFormApply.setApplyTitle("用户【"+loginUser.getUser().getNickName()+"】申请的【"+oaCustomFormApply.getName()+"单】");
        oaCustomFormApply.setUpdateTime(DateUtils.getNowDate());
        String insId = "";
        if("1".equals(oaCustomFormApply.getStatus())){
            String businessKey = sysBillType.getBillType()+":"+ sysBillType.getTableName()+":"+oaCustomFormApply.getId();
            Map<String,Object> vars = FlowUtil.buildFlowVars(oaCustomFormApply.getApplyTitle(),oaCustomFormApply.getCreateBy(),
                    loginUser.getUser().getNickName(), DateUtil.formatDateTime(oaCustomFormApply.getCreateTime()), CommonConstants.YES, sysBillType.getAppStatus());
            insId = oaCustomFormApplyService.submitApply(oaCustomFormApply.getFlowKey(),businessKey,vars);
            oaCustomFormApply.setFlowInsId(insId);
        }
        int i = oaCustomFormApplyMapper.updateOaCustomFormApply(oaCustomFormApply);
        return i;
    }

    /****
     * 提交
     * @param id
     * @return
     */
    public void submitApply(Long id){
        SysBillType sysBillType = sysBillTypeService.selectOaBillTypeByBillType(BillTypeConstants.CUSTOM_FORM);

        OaCustomFormApply apply = oaCustomFormApplyService.selectOaCustomFormApplyById(id);
        SysUser user = sysUserMapper.selectUserById(apply.getCreateBy());
        Map<String,Object> vars = FlowUtil.buildFlowVars(apply.getApplyTitle(),apply.getCreateBy(),user.getNickName(),
        DateUtil.formatDateTime(apply.getCreateTime()), CommonConstants.YES, sysBillType.getAppStatus());

        String businessKey = sysBillType.getBillType()+":"+ sysBillType.getTableName()+":"+id;
        String insId = oaCustomFormApplyService.submitApply(apply.getFlowKey(),businessKey,vars);
        OaCustomFormApply updateApply = new OaCustomFormApply();
        updateApply.setId(id);
        updateApply.setStatus(CommonConstants.FLOW_STATUS_SUBMIT);
        updateApply.setFlowInsId(insId);
        oaCustomFormApplyMapper.updateOaCustomFormApply(updateApply);
    }

    /****
     * 提交
     * @param defKey
     * @param businessKey
     * @param var
     * @return
     */
    public String submitApply(String defKey, String businessKey, Map<String, Object> var){
        // 提交校验，可能在单据页面中提交后，再其他打开的页面的列表页中再次点击【提交】按钮，会导致重复流程实例。需要添加【提交前校验】
        Long id = Long.valueOf(businessKey.split(":")[2]);
        OaCustomFormApply apply = oaCustomFormApplyMapper.selectOaCustomFormApplyById(id);
        if("1".equals(apply.getStatus())){//如果已经提交过了
            throw new RuntimeException("流程已经提交");
        }
        ProcessInstance procIns = runtimeService.startProcessInstanceByKeyAndTenantId(defKey,businessKey,var,SecurityUtils.getTenantId().toString());
        if(procIns!=null){
            return procIns.getId();
        }else{
            return "";
        }
    }

    /**
     * 批量删除自定义表单申请
     * 
     * @param ids 需要删除的自定义表单申请主键
     * @return 结果
     */
    public int deleteOaCustomFormApplyByIds(Long[] ids)
    {
        return oaCustomFormApplyMapper.deleteOaCustomFormApplyByIds(ids);
    }

    /****
     * 撤销申请
     * @param id
     */
    public void cancleFlow(Long id){
        OaCustomFormApply apply = oaCustomFormApplyService.selectOaCustomFormApplyById(id);
        String insId = apply.getFlowInsId();
        if(StrUtil.isNotBlank(insId)){
            flowProcessInstanceService.cancleProcesInstance(insId,"用户撤销");
        }
        OaCustomFormApply updateApply = new OaCustomFormApply();
        updateApply.setId(id);
        updateApply.setStatus(CommonConstants.FLOW_STATUS_START);
        updateApply.setFlowInsId("");
        updateApply.setUpdateTime(new Date());
        oaCustomFormApplyMapper.updateOaCustomFormApply(updateApply);
    }

    /**
     * 删除自定义表单申请信息
     * 
     * @param id 自定义表单申请主键
     * @return 结果
     */
    public int deleteOaCustomFormApplyById(Long id)
    {
        return oaCustomFormApplyMapper.deleteOaCustomFormApplyById(id);
    }
}
