package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaCustomForm;

/**
 * 自定义单Mapper接口
 * 
 * @author fno
 * @date 2022-07-05
 */
public interface OaCustomFormMapper 
{
    /**
     * 查询自定义单
     * 
     * @param id 自定义单主键
     * @return 自定义单
     */
    public OaCustomForm selectOaCustomFormById(Long id);

    /**
     * 查询自定义单列表
     * 
     * @param oaCustomForm 自定义单
     * @return 自定义单集合
     */
    public List<OaCustomForm> selectOaCustomFormList(OaCustomForm oaCustomForm);

    /**
     * 新增自定义单
     * 
     * @param oaCustomForm 自定义单
     * @return 结果
     */
    public int insertOaCustomForm(OaCustomForm oaCustomForm);

    /**
     * 修改自定义单
     * 
     * @param oaCustomForm 自定义单
     * @return 结果
     */
    public int updateOaCustomForm(OaCustomForm oaCustomForm);

    /**
     * 删除自定义单
     * 
     * @param id 自定义单主键
     * @return 结果
     */
    public int deleteOaCustomFormById(Long id);

    /**
     * 批量删除自定义单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaCustomFormByIds(Long[] ids);
}
