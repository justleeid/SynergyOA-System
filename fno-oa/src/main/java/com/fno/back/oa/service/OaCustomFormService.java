package com.fno.back.oa.service;

import com.fno.back.common.service.base.BaseService;
import com.fno.common.utils.DateUtils;
import com.fno.back.oa.domain.OaCustomForm;
import com.fno.back.oa.mapper.OaCustomFormMapper;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义单Service业务层处理
 * 
 * @author fno
 * @date 2022-07-05
 */
@Service
public class OaCustomFormService extends BaseService
{
    @Autowired
    private OaCustomFormMapper oaCustomFormMapper;

    /**
     * 查询自定义单
     * 
     * @param id 自定义单主键
     * @return 自定义单
     */
    public OaCustomForm selectOaCustomFormById(Long id)
    {
        return oaCustomFormMapper.selectOaCustomFormById(id);
    }

    /**
     * 查询自定义单列表
     * 
     * @param oaCustomForm 自定义单
     * @return 自定义单
     */
    public List<OaCustomForm> selectOaCustomFormList(OaCustomForm oaCustomForm)
    {
        oaCustomForm.setTenantId(SecurityUtils.getTenantId());
        return oaCustomFormMapper.selectOaCustomFormList(oaCustomForm);
    }

    /**
     * 新增自定义单
     * 
     * @param oaCustomForm 自定义单
     * @return 结果
     */
    public int insertOaCustomForm(OaCustomForm oaCustomForm)
    {
        oaCustomForm.setTenantId(SecurityUtils.getTenantId());
        oaCustomForm.setCreateTime(DateUtils.getNowDate());
        return oaCustomFormMapper.insertOaCustomForm(oaCustomForm);
    }

    /**
     * 修改自定义单
     * 
     * @param oaCustomForm 自定义单
     * @return 结果
     */
    public int updateOaCustomForm(OaCustomForm oaCustomForm)
    {
        oaCustomForm.setUpdateTime(DateUtils.getNowDate());
        return oaCustomFormMapper.updateOaCustomForm(oaCustomForm);
    }

    /**
     * 批量删除自定义单
     * 
     * @param ids 需要删除的自定义单主键
     * @return 结果
     */
    public int deleteOaCustomFormByIds(Long[] ids)
    {
        return oaCustomFormMapper.deleteOaCustomFormByIds(ids);
    }

    /**
     * 删除自定义单信息
     * 
     * @param id 自定义单主键
     * @return 结果
     */
    public int deleteOaCustomFormById(Long id)
    {
        return oaCustomFormMapper.deleteOaCustomFormById(id);
    }
}
