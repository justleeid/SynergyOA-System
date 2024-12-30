package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaOfficalDocRedheadtplMapper;
import com.fno.back.oa.domain.OaOfficalDocRedheadtpl;

/**
 * 套红模版Service业务层处理
 * 
 * @author fno
 * @date 2023-08-20
 */
@Service
public class OaOfficalDocRedheadtplService
{
    @Autowired
    private OaOfficalDocRedheadtplMapper oaOfficalDocRedheadtplMapper;

    /**
     * 查询套红模版
     * 
     * @param id 套红模版主键
     * @return 套红模版
     */
    public OaOfficalDocRedheadtpl selectOaOfficalDocRedheadtplById(Long id)
    {
        return oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplById(id);
    }

    /**
     * 查询套红模版列表
     * 
     * @param oaOfficalDocRedheadtpl 套红模版
     * @return 套红模版
     */
    public List<OaOfficalDocRedheadtpl> selectOaOfficalDocRedheadtplList(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        oaOfficalDocRedheadtpl.setTenantId(SecurityUtils.getTenantId());
        return oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplList(oaOfficalDocRedheadtpl);
    }

    /**
     * 新增套红模版
     * 
     * @param oaOfficalDocRedheadtpl 套红模版
     * @return 结果
     */
    public int insertOaOfficalDocRedheadtpl(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        oaOfficalDocRedheadtpl.setCreateTime(DateUtils.getNowDate());
        oaOfficalDocRedheadtpl.setTenantId(SecurityUtils.getTenantId());
        return oaOfficalDocRedheadtplMapper.insertOaOfficalDocRedheadtpl(oaOfficalDocRedheadtpl);
    }

    /**
     * 修改套红模版
     * 
     * @param oaOfficalDocRedheadtpl 套红模版
     * @return 结果
     */
    public int updateOaOfficalDocRedheadtpl(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl)
    {
        return oaOfficalDocRedheadtplMapper.updateOaOfficalDocRedheadtpl(oaOfficalDocRedheadtpl);
    }

    /**
     * 批量删除套红模版
     * 
     * @param ids 需要删除的套红模版主键
     * @return 结果
     */
    public int deleteOaOfficalDocRedheadtplByIds(Long[] ids)
    {
        return oaOfficalDocRedheadtplMapper.deleteOaOfficalDocRedheadtplByIds(ids);
    }

    /**
     * 删除套红模版信息
     * 
     * @param id 套红模版主键
     * @return 结果
     */
    public int deleteOaOfficalDocRedheadtplById(Long id)
    {
        return oaOfficalDocRedheadtplMapper.deleteOaOfficalDocRedheadtplById(id);
    }
}
