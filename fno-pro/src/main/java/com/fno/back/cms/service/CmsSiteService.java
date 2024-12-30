package com.fno.back.cms.service;

import java.util.Date;
import java.util.List;

import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.cms.mapper.CmsSiteMapper;
import com.fno.back.cms.domain.CmsSite;
import com.fno.back.cms.service.CmsSiteService;

/**
 * 站点管理Service业务层处理
 *
 * @author fno
 * @date 2024-01-29
 */
@Service
public class CmsSiteService
{
    @Autowired
    private CmsSiteMapper cmsSiteMapper;

    /**
     * 查询站点管理
     *
     * @param id 站点管理主键
     * @return 站点管理
     */
    public CmsSite selectCmsSiteById(Long id)
    {
        return cmsSiteMapper.selectCmsSiteById(id);
    }

    /**
     * 查询站点管理列表
     *
     * @param cmsSite 站点管理
     * @return 站点管理
     */
    public List<CmsSite> selectCmsSiteList(CmsSite cmsSite)
    {
        return cmsSiteMapper.selectCmsSiteList(cmsSite);
    }

    /**
     * 新增站点管理
     *
     * @param cmsSite 站点管理
     * @return 结果
     */
    public int insertCmsSite(CmsSite cmsSite)
    {
        cmsSite.setTenantId(SecurityUtils.getTenantId());
        cmsSite.setCreateBy(SecurityUtils.getUserId());
        cmsSite.setCreateTime(new Date());
        return cmsSiteMapper.insertCmsSite(cmsSite);
    }

    /**
     * 修改站点管理
     *
     * @param cmsSite 站点管理
     * @return 结果
     */
    public int updateCmsSite(CmsSite cmsSite)
    {
        cmsSite.setUpdateBy(SecurityUtils.getUserId());
        cmsSite.setUpdateTime(new Date());
        return cmsSiteMapper.updateCmsSite(cmsSite);
    }

    /**
     * 批量删除站点管理
     *
     * @param ids 需要删除的站点管理主键
     * @return 结果
     */
    public int deleteCmsSiteByIds(Long[] ids)
    {
        return cmsSiteMapper.deleteCmsSiteByIds(ids);
    }

    /**
     * 删除站点管理信息
     *
     * @param id 站点管理主键
     * @return 结果
     */
    public int deleteCmsSiteById(Long id)
    {
        return cmsSiteMapper.deleteCmsSiteById(id);
    }
}
