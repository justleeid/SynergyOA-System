package com.fno.back.workflow.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fno.back.common.domain.SysBillType;
import com.fno.back.common.mapper.SysBillTypeMapper;
import com.fno.back.workflow.domain.FlowCced;
import com.fno.back.workflow.mapper.FlowCcedMapper;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 抄送我的Service业务层处理
 *
 */
@Service
public class FlowCcedService
{
    @Autowired
    private FlowCcedMapper oaFlowCcedMapper;
    @Autowired
    private SysBillTypeMapper sysBillTypeMapper;

    /**
     * 查询抄送我的
     * 
     * @param id 抄送我的主键
     * @return 抄送我的
     */
    public FlowCced selectOaFlowCcedById(Long id)
    {
        return oaFlowCcedMapper.selectOaFlowCcedById(id);
    }

    /**
     * 查询抄送我的列表
     * 
     * @param oaFlowCced 抄送我的
     * @return 抄送我的
     */
    public List<FlowCced> selectOaFlowCcedList(FlowCced oaFlowCced)
    {
        return oaFlowCcedMapper.selectOaFlowCcedList(oaFlowCced);
    }

    /**
     * 查询抄送我的列表
     *
     * @param oaFlowCced 抄送我的
     * @return 抄送我的
     */
    public List<FlowCced> selectCcedListToMe(FlowCced oaFlowCced)
    {
        oaFlowCced.setToUserId(SecurityUtils.getUserId());
        return oaFlowCcedMapper.selectOaFlowCcedList(oaFlowCced);
    }



    /**
     * 计算抄送数量
     *
     * @param oaFlowCced 抄送我的
     * @return 抄送我的
     */
    public int countCced(FlowCced oaFlowCced)
    {
        return oaFlowCcedMapper.countCced(oaFlowCced);
    }

    /**
     * 新增抄送我的
     * 
     * @param oaFlowCced 抄送我的
     * @return 结果
     */
    public int insertOaFlowCced(FlowCced oaFlowCced)
    {
        oaFlowCced.setCreateTime(DateUtils.getNowDate());
        return oaFlowCcedMapper.insertOaFlowCced(oaFlowCced);
    }


    /****
     * 根据但据类型和业务id删除抄送数据
     * @param billType
     * @param businessId
     */
    public void deleteCcedByBillTypeAndBusinessId(String billType,Long businessId){
        oaFlowCcedMapper.deleteCcedByBillTypeAndBusinessId(billType,businessId);
    }

    /****
     *  首次保存时候，批量插入全部抄送人员
     * @param objList
     * @param billType
     * @param businessId
     */
    public void formSaveBatchInsertCcedList(JSONArray objList, String billType, String businessId){
        if(objList!=null && objList.size()>0){
            SysBillType sysBillType = sysBillTypeMapper.selectOaBillTypeByBillType(billType);
            List<FlowCced> list = new ArrayList<>();
            for(int i=0;i<objList.size();i++){
                JSONObject obj = objList.getJSONObject(i);
                FlowCced cced = new FlowCced();
                cced.setToUserId(obj.getLong("toUserId"));
                cced.setBillType(billType);
                cced.setBusinessId(Long.parseLong(businessId));
                cced.setFlowKey(sysBillType.getDefKey());
                cced.setTitle("用户["+SecurityUtils.getNickName()+"]发起的["+sysBillType.getBillName()+"]");
                cced.setTableName(sysBillType.getTableName());
                cced.setCreateBy(SecurityUtils.getUserId());
                cced.setCreateTime(new Date());
                cced.setFromUserId(SecurityUtils.getUserId());
                list.add(cced);
            }
            oaFlowCcedMapper.insertBatch(list);
        }

    }

    /**
     * 修改抄送我的
     * 
     * @param oaFlowCced 抄送我的
     * @return 结果
     */
    public int updateOaFlowCced(FlowCced oaFlowCced)
    {
        return oaFlowCcedMapper.updateOaFlowCced(oaFlowCced);
    }

    /**
     * 批量删除抄送我的
     * 
     * @param ids 需要删除的抄送我的主键
     * @return 结果
     */
    public int deleteOaFlowCcedByIds(Long[] ids)
    {
        return oaFlowCcedMapper.deleteOaFlowCcedByIds(ids);
    }

    /**
     * 删除抄送我的信息
     * 
     * @param id 抄送我的主键
     * @return 结果
     */
    public int deleteOaFlowCcedById(Long id)
    {
        return oaFlowCcedMapper.deleteOaFlowCcedById(id);
    }
}
