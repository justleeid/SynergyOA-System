package com.fno.back.oa.service;

import com.fno.back.oa.mapper.OaBdSealMapper;
import com.fno.back.common.service.SerialService;
import com.fno.back.oa.domain.OaSealApply;
import com.fno.back.oa.mapper.OaSealApplyMapper;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公章使用Service业务层处理
 * 
 * @author fno
 * @date 2023-05-29
 */
@Service
public class OaSealApplyService
{
    @Autowired
    private OaSealApplyMapper oaSealApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private OaBdSealMapper oaBdSealMapper;

    /**
     * 查询公章使用
     * 
     * @param id 公章使用主键
     * @return 公章使用
     */
    public OaSealApply selectOaSealApplyById(Long id)
    {
        return oaSealApplyMapper.selectOaSealApplyById(id);
    }

    /**
     * 查询公章使用列表
     * 
     * @param oaSealApply 公章使用
     * @return 公章使用
     */
    public List<OaSealApply> selectOaSealApplyList(OaSealApply oaSealApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaSealApply.setCreateBy(SecurityUtils.getUserId());
        }
        //租户
        oaSealApply.setTenantId(SecurityUtils.getTenantId());
        return oaSealApplyMapper.selectOaSealApplyList(oaSealApply);
    }

    /**
     * 新增公章使用
     * 
     * @param oaSealApply 公章使用
     * @return 结果
     */
    public int insertOaSealApply(OaSealApply oaSealApply)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaSealApply.getBillType());
        oaSealApply.setCreateTime(DateUtils.getNowDate());
        oaSealApply.setBillCode(billCode);
        oaSealApply.setCreateBy(SecurityUtils.getLoginUser().getUserId());
        oaSealApply.setUserId(SecurityUtils.getUserId());
        oaSealApply.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        //租户
        oaSealApply.setTenantId(SecurityUtils.getTenantId());
        return oaSealApplyMapper.insertOaSealApply(oaSealApply);
    }

    /**
     * 修改公章使用
     * 
     * @param oaSealApply 公章使用
     * @return 结果
     */
    public int updateOaSealApply(OaSealApply oaSealApply)
    {
        oaSealApply.setUpdateTime(DateUtils.getNowDate());
        return oaSealApplyMapper.updateOaSealApply(oaSealApply);
    }

    /**
     * 批量删除公章使用
     * 
     * @param ids 需要删除的公章使用主键
     * @return 结果
     */
    public int deleteOaSealApplyByIds(Long[] ids)
    {
        return oaSealApplyMapper.deleteOaSealApplyByIds(ids);
    }

    /**
     * 删除公章使用信息
     * 
     * @param id 公章使用主键
     * @return 结果
     */
    public int deleteOaSealApplyById(Long id)
    {
        return oaSealApplyMapper.deleteOaSealApplyById(id);
    }



}
