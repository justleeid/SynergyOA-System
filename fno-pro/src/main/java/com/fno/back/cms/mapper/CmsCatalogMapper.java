package com.fno.back.cms.mapper;

import java.util.List;
import com.fno.back.cms.domain.CmsCatalog;

/**
 * 栏目管理Mapper接口
 * 
 * @author fno
 * @date 2023-04-29
 */
public interface CmsCatalogMapper 
{
    /**
     * 查询栏目管理
     * 
     * @param id 栏目管理主键
     * @return 栏目管理
     */
    public CmsCatalog selectCmsCatalogById(Long id);

    /**
     * 查询栏目管理列表
     * 
     * @param cmsCatalog 栏目管理
     * @return 栏目管理集合
     */
    public List<CmsCatalog> selectCmsCatalogList(CmsCatalog cmsCatalog);

    /**
     * 新增栏目管理
     * 
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    public int insertCmsCatalog(CmsCatalog cmsCatalog);

    /**
     * 修改栏目管理
     * 
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    public int updateCmsCatalog(CmsCatalog cmsCatalog);

    /**
     * 删除栏目管理
     * 
     * @param id 栏目管理主键
     * @return 结果
     */
    public int deleteCmsCatalogById(Long id);

    /**
     * 批量删除栏目管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmsCatalogByIds(Long[] ids);
}
