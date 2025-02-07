package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaDutyApply;

/**
 * 假勤申请Mapper接口
 *
 */
public interface OaDutyApplyMapper 
{
    /**
     * 查询假勤申请
     * 
     * @param id 假勤申请主键
     * @return 假勤申请
     */
    public OaDutyApply selectOaDutyApplyById(Long id);

    /**
     * 查询假勤申请列表
     * 
     * @param oaDutyApply 假勤申请
     * @return 假勤申请集合
     */
    public List<OaDutyApply> selectOaDutyApplyList(OaDutyApply oaDutyApply);

    /**
     * 新增假勤申请
     * 
     * @param oaDutyApply 假勤申请
     * @return 结果
     */
    public int insertOaDutyApply(OaDutyApply oaDutyApply);

    /**
     * 修改假勤申请
     * 
     * @param oaDutyApply 假勤申请
     * @return 结果
     */
    public int updateOaDutyApply(OaDutyApply oaDutyApply);

    /**
     * 删除假勤申请
     * 
     * @param id 假勤申请主键
     * @return 结果
     */
    public int deleteOaDutyApplyById(Long id);

    /**
     * 批量删除假勤申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaDutyApplyByIds(Long[] ids);
}
