package com.fno.back.oa.service;

import com.fno.back.common.service.SerialService;
import com.fno.back.oa.domain.OaDutyApply;
import com.fno.back.oa.mapper.OaDutyApplyMapper;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 假勤申请Service业务层处理
 *
 */
@Service
public class OaDutyApplyService
{
    @Autowired
    private OaDutyApplyMapper oaDutyApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询假勤申请
     * 
     * @param id 假勤申请主键
     * @return 假勤申请
     */
    public OaDutyApply selectOaDutyApplyById(Long id)
    {
        return oaDutyApplyMapper.selectOaDutyApplyById(id);
    }

    /**
     * 查询假勤申请列表
     * 
     * @param oaDutyApply 假勤申请
     * @return 假勤申请
     */
    public List<OaDutyApply> selectOaDutyApplyList(OaDutyApply oaDutyApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaDutyApply.setCreateBy(SecurityUtils.getUserId());
        }
        //租户
        oaDutyApply.setTenantId(SecurityUtils.getTenantId());
        return oaDutyApplyMapper.selectOaDutyApplyList(oaDutyApply);
    }

    /**
     * 新增假勤申请
     * 
     * @param oaDutyApply 假勤申请
     * @return 结果
     */
    public int insertOaDutyApply(OaDutyApply oaDutyApply)
    {
        oaDutyApply.setCreateTime(DateUtils.getNowDate());
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaDutyApply.getBillType());
        oaDutyApply.setBillCode(billCode);
        oaDutyApply.setCreateBy(SecurityUtils.getUserId());
        oaDutyApply.setDeptId(SecurityUtils.getDeptId());
        oaDutyApply.setUserId(SecurityUtils.getUserId());
        //租户
        oaDutyApply.setTenantId(SecurityUtils.getTenantId());
        return oaDutyApplyMapper.insertOaDutyApply(oaDutyApply);
    }

    /**
     * 修改假勤申请
     * 
     * @param oaDutyApply 假勤申请
     * @return 结果
     */
    public int updateOaDutyApply(OaDutyApply oaDutyApply)
    {
        oaDutyApply.setUpdateTime(DateUtils.getNowDate());
        return oaDutyApplyMapper.updateOaDutyApply(oaDutyApply);
    }

    /**
     * 批量删除假勤申请
     * 
     * @param ids 需要删除的假勤申请主键
     * @return 结果
     */
    public int deleteOaDutyApplyByIds(Long[] ids)
    {
        return oaDutyApplyMapper.deleteOaDutyApplyByIds(ids);
    }

    /**
     * 删除假勤申请信息
     * 
     * @param id 假勤申请主键
     * @return 结果
     */
    public int deleteOaDutyApplyById(Long id)
    {
        return oaDutyApplyMapper.deleteOaDutyApplyById(id);
    }





}
