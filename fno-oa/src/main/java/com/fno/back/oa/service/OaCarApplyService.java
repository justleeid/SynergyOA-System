package com.fno.back.oa.service;

import com.fno.back.common.service.SerialService;
import com.fno.back.oa.domain.OaCarApply;
import com.fno.back.oa.mapper.OaBdCarMapper;
import com.fno.back.oa.mapper.OaCarApplyMapper;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆申请Service业务层处理
 * 
 * @author fno
 * @date 2023-06-05
 */
@Service
public class OaCarApplyService
{
    @Autowired
    private OaCarApplyMapper oaCarApplyMapper;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private SerialService serialService;
    @Autowired
    private OaBdCarMapper oaBdCarMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询车辆申请
     * 
     * @param id 车辆申请主键
     * @return 车辆申请
     */
    public OaCarApply selectOaCarApplyById(Long id)
    {
        return oaCarApplyMapper.selectOaCarApplyById(id);
    }

    /**
     * 查询车辆申请列表
     * 
     * @param oaCarApply 车辆申请
     * @return 车辆申请
     */
    public List<OaCarApply> selectOaCarApplyList(OaCarApply oaCarApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaCarApply.setCreateBy(SecurityUtils.getUserId());
        }
        //租户
        oaCarApply.setTenantId(SecurityUtils.getTenantId());
        return oaCarApplyMapper.selectOaCarApplyList(oaCarApply);
    }

    /**
     * 新增车辆申请
     * 
     * @param oaCarApply 车辆申请
     * @return 结果
     */
    public int insertOaCarApply(OaCarApply oaCarApply)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaCarApply.getBillType());
        oaCarApply.setCreateTime(DateUtils.getNowDate());
        oaCarApply.setBillCode(billCode);
        oaCarApply.setCreateBy(SecurityUtils.getUserId());
        oaCarApply.setDeptId(SecurityUtils.getDeptId());
        oaCarApply.setUserId(SecurityUtils.getUserId());
        //租户
        oaCarApply.setTenantId(SecurityUtils.getTenantId());
        return oaCarApplyMapper.insertOaCarApply(oaCarApply);
    }

    /**
     * 修改车辆申请
     * 
     * @param oaCarApply 车辆申请
     * @return 结果
     */
    public int updateOaCarApply(OaCarApply oaCarApply)
    {
        oaCarApply.setUpdateTime(DateUtils.getNowDate());
        return oaCarApplyMapper.updateOaCarApply(oaCarApply);
    }

    /**
     * 批量删除车辆申请
     * 
     * @param ids 需要删除的车辆申请主键
     * @return 结果
     */
    public int deleteOaCarApplyByIds(Long[] ids)
    {
        return oaCarApplyMapper.deleteOaCarApplyByIds(ids);
    }

    /**
     * 删除车辆申请信息
     * 
     * @param id 车辆申请主键
     * @return 结果
     */
    public int deleteOaCarApplyById(Long id)
    {
        return oaCarApplyMapper.deleteOaCarApplyById(id);
    }




}
