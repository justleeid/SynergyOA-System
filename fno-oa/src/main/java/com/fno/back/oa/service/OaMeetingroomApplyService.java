package com.fno.back.oa.service;

import com.fno.back.common.service.SerialService;
import com.fno.back.oa.domain.OaMeetingroomApply;
import com.fno.back.oa.mapper.OaBdMeetingroomMapper;
import com.fno.back.oa.mapper.OaMeetingroomApplyMapper;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议室申请Service业务层处理
 * 
 * @author fno
 * @date 2023-05-29
 */
@Service
public class OaMeetingroomApplyService
{
    @Autowired
    private OaMeetingroomApplyMapper oaMeetingroomApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;
    @Autowired
    private OaBdMeetingroomMapper oaBdMeetingroomMapper;

    /**
     * 查询会议室申请
     * 
     * @param id 会议室申请主键
     * @return 会议室申请
     */
    public OaMeetingroomApply selectOaMeetingroomApplyById(Long id)
    {
        return oaMeetingroomApplyMapper.selectOaMeetingroomApplyById(id);
    }

    /**
     * 查询会议室申请列表
     * 
     * @param oaMeetingroomApply 会议室申请
     * @return 会议室申请
     */
    public List<OaMeetingroomApply> selectOaMeetingroomApplyList(OaMeetingroomApply oaMeetingroomApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaMeetingroomApply.setCreateBy(SecurityUtils.getUserId());
        }
        oaMeetingroomApply.setTenantId(SecurityUtils.getTenantId());
        return oaMeetingroomApplyMapper.selectOaMeetingroomApplyList(oaMeetingroomApply);
    }

    /**
     * 新增会议室申请
     * 
     * @param oaMeetingroomApply 会议室申请
     * @return 结果
     */
    public int insertOaMeetingroomApply(OaMeetingroomApply oaMeetingroomApply)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaMeetingroomApply.getBillType());
        oaMeetingroomApply.setCreateTime(DateUtils.getNowDate());
        oaMeetingroomApply.setBillCode(billCode);
        oaMeetingroomApply.setCreateBy(SecurityUtils.getUserId());
        oaMeetingroomApply.setDeptId(SecurityUtils.getUserId());
        oaMeetingroomApply.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        //租户ID
        oaMeetingroomApply.setTenantId(SecurityUtils.getTenantId());
        return oaMeetingroomApplyMapper.insertOaMeetingroomApply(oaMeetingroomApply);
    }

    /**
     * 修改会议室申请
     * 
     * @param oaMeetingroomApply 会议室申请
     * @return 结果
     */
    public int updateOaMeetingroomApply(OaMeetingroomApply oaMeetingroomApply)
    {
        oaMeetingroomApply.setUpdateTime(DateUtils.getNowDate());
        return oaMeetingroomApplyMapper.updateOaMeetingroomApply(oaMeetingroomApply);
    }

    /**
     * 批量删除会议室申请
     * 
     * @param ids 需要删除的会议室申请主键
     * @return 结果
     */
    public int deleteOaMeetingroomApplyByIds(Long[] ids)
    {
        return oaMeetingroomApplyMapper.deleteOaMeetingroomApplyByIds(ids);
    }

    /**
     * 删除会议室申请信息
     * 
     * @param id 会议室申请主键
     * @return 结果
     */
    public int deleteOaMeetingroomApplyById(Long id)
    {
        return oaMeetingroomApplyMapper.deleteOaMeetingroomApplyById(id);
    }


}
