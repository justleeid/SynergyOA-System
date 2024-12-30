package com.fno.back.oa.service;

import java.util.List;

import com.fno.back.common.service.SerialService;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaWorkRequestMapper;
import com.fno.back.oa.domain.OaWorkRequest;
import com.fno.back.oa.service.OaWorkRequestService;

/**
 * 工作请示Service业务层处理
 *
 * @author fno
 * @date 2024-03-04
 */
@Service
public class OaWorkRequestService
{
    @Autowired
    private OaWorkRequestMapper oaWorkRequestMapper;
    @Autowired
    private SerialService serialService;

    /**
     * 查询工作请示
     *
     * @param id 工作请示主键
     * @return 工作请示
     */
    public OaWorkRequest selectOaWorkRequestById(Long id)
    {
        return oaWorkRequestMapper.selectOaWorkRequestById(id);
    }

    /**
     * 查询工作请示列表
     *
     * @param oaWorkRequest 工作请示
     * @return 工作请示
     */
    public List<OaWorkRequest> selectOaWorkRequestList(OaWorkRequest oaWorkRequest)
    {
        return oaWorkRequestMapper.selectOaWorkRequestList(oaWorkRequest);
    }

    /**
     * 新增工作请示
     *
     * @param oaWorkRequest 工作请示
     * @return 结果
     */
    public int insertOaWorkRequest(OaWorkRequest oaWorkRequest)
    {
        //生成订单编号
        String billCode = serialService.generateBillCodeByBillType(oaWorkRequest.getBillType());
        oaWorkRequest.setBillCode(billCode);
        oaWorkRequest.setCreateTime(DateUtils.getNowDate());
        oaWorkRequest.setCreateBy(SecurityUtils.getUserId());
        oaWorkRequest.setTenantId(SecurityUtils.getTenantId());
        return oaWorkRequestMapper.insertOaWorkRequest(oaWorkRequest);
    }

    /**
     * 修改工作请示
     *
     * @param oaWorkRequest 工作请示
     * @return 结果
     */
    public int updateOaWorkRequest(OaWorkRequest oaWorkRequest)
    {
        oaWorkRequest.setUpdateTime(DateUtils.getNowDate());
        oaWorkRequest.setUpdateBy(SecurityUtils.getUserId());
        return oaWorkRequestMapper.updateOaWorkRequest(oaWorkRequest);
    }

    /**
     * 批量删除工作请示
     *
     * @param ids 需要删除的工作请示主键
     * @return 结果
     */
    public int deleteOaWorkRequestByIds(Long[] ids)
    {
        return oaWorkRequestMapper.deleteOaWorkRequestByIds(ids);
    }

    /**
     * 删除工作请示信息
     *
     * @param id 工作请示主键
     * @return 结果
     */
    public int deleteOaWorkRequestById(Long id)
    {
        return oaWorkRequestMapper.deleteOaWorkRequestById(id);
    }
}
