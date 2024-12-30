package com.fno.back.oa.service;

import com.fno.back.common.service.SerialService;
import com.fno.back.oa.domain.OaReimburseApply;
import com.fno.back.oa.domain.OaReimburseFeeitem;
import com.fno.back.oa.mapper.OaReimburseApplyMapper;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 报销申请Service业务层处理
 * 
 * @author fno
 * @date 2023-05-29
 */
@Service
public class OaReimburseApplyService
{
    @Autowired
    private OaReimburseApplyMapper oaReimburseApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;


    /**
     * 查询报销申请
     * 
     * @param id 报销申请主键
     * @return 报销申请
     */
    public OaReimburseApply selectOaReimburseApplyById(Long id)
    {
        return oaReimburseApplyMapper.selectOaReimburseApplyById(id);
    }

    /**
     * 查询报销申请列表
     * 
     * @param oaReimburseApply 报销申请
     * @return 报销申请
     */
    public List<OaReimburseApply> selectOaReimburseApplyList(OaReimburseApply oaReimburseApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaReimburseApply.setCreateBy(SecurityUtils.getUserId());
        }
        //租户ID
        oaReimburseApply.setTenantId(SecurityUtils.getTenantId());
        return oaReimburseApplyMapper.selectOaReimburseApplyList(oaReimburseApply);
    }

    /**
     * 新增报销申请
     * 
     * @param oaReimburseApply 报销申请
     * @return 结果
     */
    @Transactional
    public int insertOaReimburseApply(OaReimburseApply oaReimburseApply)
    {
        oaReimburseApply.setCreateTime(DateUtils.getNowDate());
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaReimburseApply.getBillType());
        oaReimburseApply.setBillcode(billCode);
        oaReimburseApply.setDeptId(SecurityUtils.getDeptId());
        oaReimburseApply.setCreateBy(SecurityUtils.getUserId());
        oaReimburseApply.setUserId(SecurityUtils.getUserId());

        //租户ID
        oaReimburseApply.setTenantId(SecurityUtils.getTenantId());

        int rows = oaReimburseApplyMapper.insertOaReimburseApply(oaReimburseApply);
        insertoaReimburseFeeitem(oaReimburseApply);
        return rows;
    }

    /**
     * 修改报销申请
     * 
     * @param oaReimburseApply 报销申请
     * @return 结果
     */
    @Transactional
    public int updateOaReimburseApply(OaReimburseApply oaReimburseApply)
    {
        oaReimburseApply.setUpdateTime(DateUtils.getNowDate());
        oaReimburseApplyMapper.deleteoaReimburseFeeitemByReimburseId(oaReimburseApply.getId());
        insertoaReimburseFeeitem(oaReimburseApply);
        return oaReimburseApplyMapper.updateOaReimburseApply(oaReimburseApply);
    }

    /**
     * 批量删除报销申请
     * 
     * @param ids 需要删除的报销申请主键
     * @return 结果
     */
    @Transactional
    public int deleteOaReimburseApplyByIds(Long[] ids)
    {
        oaReimburseApplyMapper.deleteoaReimburseFeeitemByReimburseIds(ids);
        return oaReimburseApplyMapper.deleteOaReimburseApplyByIds(ids);
    }

    /**
     * 删除报销申请信息
     * 
     * @param id 报销申请主键
     * @return 结果
     */
    @Transactional
    public int deleteOaReimburseApplyById(Long id)
    {
        oaReimburseApplyMapper.deleteoaReimburseFeeitemByReimburseId(id);
        return oaReimburseApplyMapper.deleteOaReimburseApplyById(id);
    }

    /**
     * 新增报销费用明细信息
     * 
     * @param oaReimburseApply 报销申请对象
     */
    public void insertoaReimburseFeeitem(OaReimburseApply oaReimburseApply)
    {
        List<OaReimburseFeeitem> oaReimburseFeeitemList = oaReimburseApply.getOaReimburseFeeitemList();
        Long id = oaReimburseApply.getId();
        if (StringUtils.isNotNull(oaReimburseFeeitemList))
        {
            List<OaReimburseFeeitem> list = new ArrayList<OaReimburseFeeitem>();
            for (OaReimburseFeeitem oaReimburseFeeitem : oaReimburseFeeitemList)
            {
                oaReimburseFeeitem.setReimburseId(id);
                list.add(oaReimburseFeeitem);
            }
            if (list.size() > 0)
            {
                oaReimburseApplyMapper.batchoaReimburseFeeitem(list);
            }
        }
    }


}
