package com.fno.back.oa.service;

import com.fno.back.common.service.SerialService;
import com.fno.back.oa.domain.OaHotelApply;
import com.fno.back.oa.mapper.OaHotelApplyMapper;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 酒店申请Service业务层处理
 * 
 * @author fno
 * @date 2023-05-29
 */
@Service
public class OaHotelApplyService
{
    @Autowired
    private OaHotelApplyMapper oaHotelApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;

    /**
     * 查询酒店申请
     * 
     * @param id 酒店申请主键
     * @return 酒店申请
     */
    public OaHotelApply selectOaHotelApplyById(Long id)
    {
        return oaHotelApplyMapper.selectOaHotelApplyById(id);
    }

    /**
     * 查询酒店申请列表
     * 
     * @param oaHotelApply 酒店申请
     * @return 酒店申请
     */
    public List<OaHotelApply> selectOaHotelApplyList(OaHotelApply oaHotelApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaHotelApply.setCreateBy(SecurityUtils.getUserId());
        }
        oaHotelApply.setTenantId(SecurityUtils.getTenantId());
        return oaHotelApplyMapper.selectOaHotelApplyList(oaHotelApply);
    }

    /**
     * 新增酒店申请
     * 
     * @param oaHotelApply 酒店申请
     * @return 结果
     */
    public int insertOaHotelApply(OaHotelApply oaHotelApply)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaHotelApply.getBillType());
        oaHotelApply.setCreateTime(DateUtils.getNowDate());
        oaHotelApply.setBillCode(billCode);
        oaHotelApply.setCreateBy(SecurityUtils.getLoginUser().getUserId());
        oaHotelApply.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        oaHotelApply.setUserId(SecurityUtils.getUserId());
        //租户ID
        oaHotelApply.setTenantId(SecurityUtils.getTenantId());
        return oaHotelApplyMapper.insertOaHotelApply(oaHotelApply);
    }

    /**
     * 修改酒店申请
     * 
     * @param oaHotelApply 酒店申请
     * @return 结果
     */
    public int updateOaHotelApply(OaHotelApply oaHotelApply)
    {
        oaHotelApply.setUpdateTime(DateUtils.getNowDate());
        return oaHotelApplyMapper.updateOaHotelApply(oaHotelApply);
    }

    /**
     * 批量删除酒店申请
     * 
     * @param ids 需要删除的酒店申请主键
     * @return 结果
     */
    public int deleteOaHotelApplyByIds(Long[] ids)
    {
        return oaHotelApplyMapper.deleteOaHotelApplyByIds(ids);
    }

    /**
     * 删除酒店申请信息
     * 
     * @param id 酒店申请主键
     * @return 结果
     */
    public int deleteOaHotelApplyById(Long id)
    {
        return oaHotelApplyMapper.deleteOaHotelApplyById(id);
    }



}
