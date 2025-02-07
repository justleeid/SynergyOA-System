package com.fno.back.oa.service;

import java.util.List;

import com.fno.back.common.service.SerialService;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaTicketApplyMapper;
import com.fno.back.oa.domain.OaTicketApply;

/**
 * 车票申请Service业务层处理
 *
 */
@Service
public class OaTicketApplyService
{
    @Autowired
    private OaTicketApplyMapper oaTicketApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;

    /**
     * 查询车票申请
     * 
     * @param id 车票申请主键
     * @return 车票申请
     */
    public OaTicketApply selectOaTicketApplyById(Long id)
    {
        return oaTicketApplyMapper.selectOaTicketApplyById(id);
    }

    /**
     * 查询车票申请列表
     * 
     * @param oaTicketApply 车票申请
     * @return 车票申请
     */
    public List<OaTicketApply> selectOaTicketApplyList(OaTicketApply oaTicketApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaTicketApply.setCreateBy(SecurityUtils.getUserId());
        }
        //租户
        oaTicketApply.setTenantId(SecurityUtils.getTenantId());
        return oaTicketApplyMapper.selectOaTicketApplyList(oaTicketApply);
    }

    /**
     * 新增车票申请
     * 
     * @param oaTicketApply 车票申请
     * @return 结果
     */
    public int insertOaTicketApply(OaTicketApply oaTicketApply)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaTicketApply.getBillType());
        oaTicketApply.setCreateTime(DateUtils.getNowDate());
        oaTicketApply.setBillCode(billCode);
        oaTicketApply.setCreateBy(SecurityUtils.getLoginUser().getUserId());
        oaTicketApply.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        oaTicketApply.setUserId(SecurityUtils.getUserId());
        //租户
        oaTicketApply.setTenantId(SecurityUtils.getTenantId());
        return oaTicketApplyMapper.insertOaTicketApply(oaTicketApply);
    }

    /**
     * 修改车票申请
     * 
     * @param oaTicketApply 车票申请
     * @return 结果
     */
    public int updateOaTicketApply(OaTicketApply oaTicketApply)
    {
        oaTicketApply.setUpdateTime(DateUtils.getNowDate());
        return oaTicketApplyMapper.updateOaTicketApply(oaTicketApply);
    }

    /**
     * 批量删除车票申请
     * 
     * @param ids 需要删除的车票申请主键
     * @return 结果
     */
    public int deleteOaTicketApplyByIds(Long[] ids)
    {
        return oaTicketApplyMapper.deleteOaTicketApplyByIds(ids);
    }

    /**
     * 删除车票申请信息
     * 
     * @param id 车票申请主键
     * @return 结果
     */
    public int deleteOaTicketApplyById(Long id)
    {
        return oaTicketApplyMapper.deleteOaTicketApplyById(id);
    }



}
