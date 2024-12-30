package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaCustomFormApply;
import org.apache.ibatis.annotations.Param;

/**
 * 自定义表单申请Mapper接口
 * 
 * @author fno
 * @date 2022-07-09
 */
public interface OaCustomFormApplyMapper 
{
    /**
     * 查询自定义表单申请
     * 
     * @param id 自定义表单申请主键
     * @return 自定义表单申请
     */
    public OaCustomFormApply selectOaCustomFormApplyById(Long id);



    /**
     * 查询自定义表单申请
     *
     * @param id 自定义表单申请主键
     * @return 自定义表单申请
     */
    public OaCustomFormApply selectOaCustomFormApplyByInsId(@Param("insId") String insId);

    /**
     * 查询自定义表单申请列表
     * 
     * @param oaCustomFormApply 自定义表单申请
     * @return 自定义表单申请集合
     */
    public List<OaCustomFormApply> selectOaCustomFormApplyList(OaCustomFormApply oaCustomFormApply);

    /**
     * 新增自定义表单申请
     * 
     * @param oaCustomFormApply 自定义表单申请
     * @return 结果
     */
    public int insertOaCustomFormApply(OaCustomFormApply oaCustomFormApply);

    /**
     * 修改自定义表单申请
     * 
     * @param oaCustomFormApply 自定义表单申请
     * @return 结果
     */
    public int updateOaCustomFormApply(OaCustomFormApply oaCustomFormApply);

    /**
     * 删除自定义表单申请
     * 
     * @param id 自定义表单申请主键
     * @return 结果
     */
    public int deleteOaCustomFormApplyById(Long id);

    /**
     * 批量删除自定义表单申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaCustomFormApplyByIds(Long[] ids);
}
