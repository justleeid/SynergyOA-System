package com.fno.back.cms.mapper;

import java.util.List;
import com.fno.back.cms.domain.CmsContent;

/**
 * 内容管理Mapper接口
 * 
 * @author fno
 * @date 2023-04-29
 */
public interface CmsContentMapper 
{
    /**
     * 查询内容管理
     * 
     * @param id 内容管理主键
     * @return 内容管理
     */
    public CmsContent selectCmsContentById(Long id);

    /**
     * 查询内容管理列表
     * 
     * @param cmsContent 内容管理
     * @return 内容管理集合
     */
    public List<CmsContent> selectCmsContentList(CmsContent cmsContent);

    /**
     * 新增内容管理
     * 
     * @param cmsContent 内容管理
     * @return 结果
     */
    public int insertCmsContent(CmsContent cmsContent);

    /**
     * 修改内容管理
     * 
     * @param cmsContent 内容管理
     * @return 结果
     */
    public int updateCmsContent(CmsContent cmsContent);

    /**
     * 删除内容管理
     * 
     * @param id 内容管理主键
     * @return 结果
     */
    public int deleteCmsContentById(Long id);

    /**
     * 批量删除内容管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsContentByIds(Long[] ids);
}
