package com.fno.back.common.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.StrUtil;
import com.fno.back.common.domain.Serial;
import com.fno.back.common.mapper.SerialMapper;
import com.fno.back.common.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 编号规则Service业务层处理
 * 
 * @author fno
 * @date 2022-10-29
 */
@Service
public class SerialService
{
    @Autowired
    private SerialMapper serialMapper;

    /**
     * 查询编号规则
     * 
     * @param id 编号规则主键
     * @return 编号规则
     */
    public Serial selectSerialById(Long id)
    {
        return serialMapper.selectSerialById(id);
    }

    /**
     * 查询编号规则列表
     * 
     * @param serial 编号规则
     * @return 编号规则
     */
    public List<Serial> selectSerialList(Serial serial)
    {
        return serialMapper.selectSerialList(serial);
    }

    /**
     * 新增编号规则
     * 
     * @param serial 编号规则
     * @return 结果
     */
    public int insertSerial(Serial serial)
    {
        return serialMapper.insertSerial(serial);
    }



    /**
     * 修改编号规则
     * 
     * @param serial 编号规则
     * @return 结果
     */
    public int updateSerial(Serial serial)
    {
        return serialMapper.updateSerial(serial);
    }

    /**
     * 批量删除编号规则
     * 
     * @param ids 需要删除的编号规则主键
     * @return 结果
     */
    public int deleteSerialByIds(Long[] ids)
    {
        return serialMapper.deleteSerialByIds(ids);
    }

    /**
     * 删除编号规则信息
     * 
     * @param id 编号规则主键
     * @return 结果
     */
    public int deleteSerialById(Long id)
    {
        return serialMapper.deleteSerialById(id);
    }



    /****
     *  根据key获取编码规则
     * @param key
     * @return
     */
    public Serial getSerialByKey(String key){
        Serial serial = serialMapper.getSerialByKey(key);
        if(CommonConstants.YES.equals(serial.getIfHaveDate())){
            String date = DatePattern.PURE_DATE_FORMAT.format(new Date());
            serial.setDate(date);
        }
        return serial;
    }

    /****
     * 获取新的编号，并新增序号
     * @param key
     * @return
     */
    @Transactional
    public String generateBillCodeByBillType(String key){
        Serial serial = serialMapper.getSerialByKey(key);
        String code = "";
        if(serial==null){
            throw new RuntimeException("没有配置单据编码生成规则");
        }
        if(CommonConstants.YES.equals(serial.getIfHaveDate())){
            //如果有日期，且日期不相同，则重置序号
            String date = DatePattern.PURE_DATE_FORMAT.format(new Date());
            //日期为空，或者，与当天不相等时候，从头开始编号
            if(StrUtil.isBlank(serial.getCodeDate())||(StrUtil.isNotBlank(serial.getCodeDate()) && !date.equals(serial.getCodeDate()))){
                //重置序号
                code = StrUtil.fillBefore("1", '0',serial.getCodeLength().intValue());
                //设置下一个序号
                serial.setSerialNum(new Long(2));
            }else{
                code = StrUtil.fillBefore(serial.getSerialNum()+"", '0',serial.getCodeLength().intValue());
                //设置下一个序号
                serial.setSerialNum(serial.getSerialNum()+1);
            }
            code = serial.getPrefix() + date + code.substring(date.length());
            //更新最新日期
            serial.setCodeDate(date);
        }else{
            code = serial.getPrefix() + StrUtil.fillBefore(serial.getSerialNum()+"", '0',serial.getCodeLength().intValue());
            serial.setSerialNum(serial.getSerialNum()+1);
        }

        serialMapper.updateSerial(serial);
        return code;
    }
}
