package com.fno.back.cms.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.cms.mapper.CmsContentMapper;
import com.fno.back.cms.domain.CmsContent;

/**
 * 内容管理Service业务层处理
 *
 * @author fno
 * @date 2023-04-29
 */
@Service
public class CmsContentService
{
    @Autowired
    private CmsContentMapper cmsContentMapper;

    /**
     * 查询内容管理
     *
     * @param id 内容管理主键
     * @return 内容管理
     */
    public CmsContent selectCmsContentById(Long id)
    {
        return cmsContentMapper.selectCmsContentById(id);
    }

    /**
     * 查询内容管理列表
     *
     * @param cmsContent 内容管理
     * @return 内容管理
     */
    public List<CmsContent> selectCmsContentList(CmsContent cmsContent)
    {
        return cmsContentMapper.selectCmsContentList(cmsContent);
    }

    /**
     * 新增内容管理
     *
     * @param cmsContent 内容管理
     * @return 结果
     */
    public int insertCmsContent(CmsContent cmsContent)
    {
        cmsContent.setCreateTime(DateUtils.getNowDate());
        return cmsContentMapper.insertCmsContent(cmsContent);
    }

    /**
     * 修改内容管理
     *
     * @param cmsContent 内容管理
     * @return 结果
     */
    public int updateCmsContent(CmsContent cmsContent)
    {
        cmsContent.setUpdateTime(DateUtils.getNowDate());
        return cmsContentMapper.updateCmsContent(cmsContent);
    }

    /**
     * 批量删除内容管理
     *
     * @param ids 需要删除的内容管理主键
     * @return 结果
     */
    public int deleteCmsContentByIds(Long[] ids)
    {
        return cmsContentMapper.deleteCmsContentByIds(ids);
    }

    /**
     * 删除内容管理信息
     *
     * @param id 内容管理主键
     * @return 结果
     */
    public int deleteCmsContentById(Long id)
    {
        return cmsContentMapper.deleteCmsContentById(id);
    }
}
