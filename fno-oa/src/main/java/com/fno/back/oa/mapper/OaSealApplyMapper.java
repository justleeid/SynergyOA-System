package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaSealApply;

/**
 * 公章使用Mapper接口
 * 
 * @author fno
 * @date 2023-05-29
 */
public interface OaSealApplyMapper 
{
    /**
     * 查询公章使用
     * 
     * @param id 公章使用主键
     * @return 公章使用
     */
    public OaSealApply selectOaSealApplyById(Long id);

    /**
     * 查询公章使用列表
     * 
     * @param oaSealApply 公章使用
     * @return 公章使用集合
     */
    public List<OaSealApply> selectOaSealApplyList(OaSealApply oaSealApply);

    /**
     * 新增公章使用
     * 
     * @param oaSealApply 公章使用
     * @return 结果
     */
    public int insertOaSealApply(OaSealApply oaSealApply);

    /**
     * 修改公章使用
     * 
     * @param oaSealApply 公章使用
     * @return 结果
     */
    public int updateOaSealApply(OaSealApply oaSealApply);

    /**
     * 删除公章使用
     * 
     * @param id 公章使用主键
     * @return 结果
     */
    public int deleteOaSealApplyById(Long id);

    /**
     * 批量删除公章使用
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaSealApplyByIds(Long[] ids);
}
