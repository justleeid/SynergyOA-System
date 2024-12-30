package com.fno.back.common.mapper;

import java.util.List;
import com.fno.back.common.domain.Serial;

/**
 * 编号规则Mapper接口
 * 
 * @author fno
 * @date 2022-10-29
 */
public interface SerialMapper 
{
    /**
     * 查询编号规则
     * 
     * @param id 编号规则主键
     * @return 编号规则
     */
    public Serial selectSerialById(Long id);


    /****
     *  根据key获取编码规则
     * @param key
     * @return
     */
    public Serial getSerialByKey(String key);

    /**
     * 查询编号规则列表
     * 
     * @param serial 编号规则
     * @return 编号规则集合
     */
    public List<Serial> selectSerialList(Serial serial);

    /**
     * 新增编号规则
     * 
     * @param serial 编号规则
     * @return 结果
     */
    public int insertSerial(Serial serial);

    /**
     * 修改编号规则
     * 
     * @param serial 编号规则
     * @return 结果
     */
    public int updateSerial(Serial serial);

    /**
     * 删除编号规则
     * 
     * @param id 编号规则主键
     * @return 结果
     */
    public int deleteSerialById(Long id);

    /**
     * 批量删除编号规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSerialByIds(Long[] ids);
}
