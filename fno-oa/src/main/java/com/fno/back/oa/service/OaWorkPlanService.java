package com.fno.back.oa.service;

import java.util.List;

import com.fno.back.common.service.SerialService;
import com.fno.back.workflow.service.FlowProcessInstanceService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.fno.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.fno.back.oa.domain.OaWorkPlanItem;
import com.fno.back.oa.mapper.OaWorkPlanMapper;
import com.fno.back.oa.domain.OaWorkPlan;

/**
 * 工作计划Service业务层处理
 * 
 * @author fno
 * @date 2023-08-13
 */
@Service
public class OaWorkPlanService
{
    @Autowired
    private OaWorkPlanMapper oaWorkPlanMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;

    /**
     * 查询工作计划
     * 
     * @param id 工作计划主键
     * @return 工作计划
     */
    public OaWorkPlan selectOaWorkPlanById(Long id)
    {
        return oaWorkPlanMapper.selectOaWorkPlanById(id);
    }

    /**
     * 查询工作计划列表
     * 
     * @param oaWorkPlan 工作计划
     * @return 工作计划
     */
    public List<OaWorkPlan> selectOaWorkPlanList(OaWorkPlan oaWorkPlan)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        //-------------所有人都能看计划，和跟踪情况
        //if(!SecurityUtils.isAdmin()){
        //    oaWorkPlan.setCreateBy(SecurityUtils.getUserId());
        //}
        oaWorkPlan.setTenantId(SecurityUtils.getTenantId());
        return oaWorkPlanMapper.selectOaWorkPlanList(oaWorkPlan);
    }

    /**
     * 新增工作计划
     * 
     * @param oaWorkPlan 工作计划
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertOaWorkPlan(OaWorkPlan oaWorkPlan)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaWorkPlan.getBillType());
        oaWorkPlan.setCreateTime(DateUtils.getNowDate());
        oaWorkPlan.setBillCode(billCode);
        oaWorkPlan.setCreateBy(SecurityUtils.getLoginUser().getUserId());
        oaWorkPlan.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        oaWorkPlan.setUserId(SecurityUtils.getUserId());
        oaWorkPlan.setPlanUserId(SecurityUtils.getUserId());
        //租户ID
        oaWorkPlan.setTenantId(SecurityUtils.getTenantId());
        int rows = oaWorkPlanMapper.insertOaWorkPlan(oaWorkPlan);
        insertOaWorkPlanItem(oaWorkPlan);
        return rows;
    }

    /**
     * 修改工作计划
     * 
     * @param oaWorkPlan 工作计划
     * @return 结果
     */
    @Transactional
    public int updateOaWorkPlan(OaWorkPlan oaWorkPlan)
    {
        oaWorkPlan.setUpdateTime(DateUtils.getNowDate());
        oaWorkPlanMapper.deleteOaWorkPlanItemByPlanId(oaWorkPlan.getId());
        insertOaWorkPlanItem(oaWorkPlan);
        return oaWorkPlanMapper.updateOaWorkPlan(oaWorkPlan);
    }

    /**
     * 批量删除工作计划
     * 
     * @param ids 需要删除的工作计划主键
     * @return 结果
     */
    @Transactional
    public int deleteOaWorkPlanByIds(Long[] ids)
    {
        oaWorkPlanMapper.deleteOaWorkPlanItemByPlanIds(ids);
        return oaWorkPlanMapper.deleteOaWorkPlanByIds(ids);
    }

    /**
     * 删除工作计划信息
     * 
     * @param id 工作计划主键
     * @return 结果
     */
    @Transactional
    public int deleteOaWorkPlanById(Long id)
    {
        oaWorkPlanMapper.deleteOaWorkPlanItemByPlanId(id);
        return oaWorkPlanMapper.deleteOaWorkPlanById(id);
    }

    /**
     * 新增工作计划安排信息
     * 
     * @param oaWorkPlan 工作计划对象
     */
    public void insertOaWorkPlanItem(OaWorkPlan oaWorkPlan)
    {
        List<OaWorkPlanItem> oaWorkPlanItemList = oaWorkPlan.getOaWorkPlanItemList();
        Long id = oaWorkPlan.getId();
        if (StringUtils.isNotNull(oaWorkPlanItemList))
        {
            List<OaWorkPlanItem> list = new ArrayList<OaWorkPlanItem>();
            for (OaWorkPlanItem oaWorkPlanItem : oaWorkPlanItemList)
            {
                oaWorkPlanItem.setPlanId(id);
                list.add(oaWorkPlanItem);
            }
            if (list.size() > 0)
            {
                oaWorkPlanMapper.batchOaWorkPlanItem(list);
            }
        }
    }






}
