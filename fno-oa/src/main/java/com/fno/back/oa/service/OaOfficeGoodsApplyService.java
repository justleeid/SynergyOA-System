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
import com.fno.back.oa.domain.OaOfficeGoodsApplyItem;
import com.fno.back.oa.mapper.OaOfficeGoodsApplyMapper;
import com.fno.back.oa.domain.OaOfficeGoodsApply;

/**
 * 办公用品领用Service业务层处理
 * 
 * @author fno
 * @date 2023-08-09
 */
@Service
public class OaOfficeGoodsApplyService
{
    @Autowired
    private OaOfficeGoodsApplyMapper oaOfficeGoodsApplyMapper;
    @Autowired
    private SerialService serialService;
    @Autowired
    private FlowProcessInstanceService flowProcessInstanceService;

    /**
     * 查询办公用品领用
     * 
     * @param id 办公用品领用主键
     * @return 办公用品领用
     */
    public OaOfficeGoodsApply selectOaOfficeGoodsApplyById(Long id)
    {
        return oaOfficeGoodsApplyMapper.selectOaOfficeGoodsApplyById(id);
    }

    /**
     * 查询办公用品领用列表
     * 
     * @param oaOfficeGoodsApply 办公用品领用
     * @return 办公用品领用
     */
    public List<OaOfficeGoodsApply> selectOaOfficeGoodsApplyList(OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            oaOfficeGoodsApply.setCreateBy(SecurityUtils.getUserId());
        }
        //租户
        oaOfficeGoodsApply.setTenantId(SecurityUtils.getTenantId());
        return oaOfficeGoodsApplyMapper.selectOaOfficeGoodsApplyList(oaOfficeGoodsApply);
    }

    /**
     * 新增办公用品领用
     * 
     * @param oaOfficeGoodsApply 办公用品领用
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertOaOfficeGoodsApply(OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        oaOfficeGoodsApply.setCreateTime(DateUtils.getNowDate());
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaOfficeGoodsApply.getBillType());
        oaOfficeGoodsApply.setBillCode(billCode);
        oaOfficeGoodsApply.setCreateBy(SecurityUtils.getLoginUser().getUserId());
        oaOfficeGoodsApply.setDeptId(SecurityUtils.getLoginUser().getDeptId());
        oaOfficeGoodsApply.setUserId(SecurityUtils.getUserId());
        //租户
        oaOfficeGoodsApply.setTenantId(SecurityUtils.getTenantId());
        int rows = oaOfficeGoodsApplyMapper.insertOaOfficeGoodsApply(oaOfficeGoodsApply);
        insertOaOfficeGoodsApplyItem(oaOfficeGoodsApply);
        return rows;
    }

    /**
     * 修改办公用品领用
     * 
     * @param oaOfficeGoodsApply 办公用品领用
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateOaOfficeGoodsApply(OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        oaOfficeGoodsApply.setUpdateTime(DateUtils.getNowDate());
        oaOfficeGoodsApply.setUpdateBy(SecurityUtils.getLoginUser().getUserId());
        oaOfficeGoodsApplyMapper.deleteOaOfficeGoodsApplyItemByApplyId(oaOfficeGoodsApply.getId());
        insertOaOfficeGoodsApplyItem(oaOfficeGoodsApply);
        return oaOfficeGoodsApplyMapper.updateOaOfficeGoodsApply(oaOfficeGoodsApply);
    }

    /**
     * 批量删除办公用品领用
     * 
     * @param ids 需要删除的办公用品领用主键
     * @return 结果
     */
    @Transactional
    public int deleteOaOfficeGoodsApplyByIds(Long[] ids)
    {
        oaOfficeGoodsApplyMapper.deleteOaOfficeGoodsApplyItemByApplyIds(ids);
        return oaOfficeGoodsApplyMapper.deleteOaOfficeGoodsApplyByIds(ids);
    }

    /**
     * 删除办公用品领用信息
     * 
     * @param id 办公用品领用主键
     * @return 结果
     */
    @Transactional
    public int deleteOaOfficeGoodsApplyById(Long id)
    {
        oaOfficeGoodsApplyMapper.deleteOaOfficeGoodsApplyItemByApplyId(id);
        return oaOfficeGoodsApplyMapper.deleteOaOfficeGoodsApplyById(id);
    }

    /**
     * 新增办公用品领用明细信息
     * 
     * @param oaOfficeGoodsApply 办公用品领用对象
     */
    public void insertOaOfficeGoodsApplyItem(OaOfficeGoodsApply oaOfficeGoodsApply)
    {
        List<OaOfficeGoodsApplyItem> oaOfficeGoodsApplyItemList = oaOfficeGoodsApply.getOaOfficeGoodsApplyItemList();
        Long id = oaOfficeGoodsApply.getId();
        if (StringUtils.isNotNull(oaOfficeGoodsApplyItemList))
        {
            List<OaOfficeGoodsApplyItem> list = new ArrayList<OaOfficeGoodsApplyItem>();
            for (OaOfficeGoodsApplyItem oaOfficeGoodsApplyItem : oaOfficeGoodsApplyItemList)
            {
                oaOfficeGoodsApplyItem.setApplyId(id);
                list.add(oaOfficeGoodsApplyItem);
            }
            if (list.size() > 0)
            {
                oaOfficeGoodsApplyMapper.batchOaOfficeGoodsApplyItem(list);
            }
        }
    }


}
