package com.fno.back.cms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.fno.back.cms.domain.PTreeSelect;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.cms.mapper.CmsCatalogMapper;
import com.fno.back.cms.domain.CmsCatalog;

/**
 * 栏目管理Service业务层处理
 *
 * @author fno
 * @date 2023-04-29
 */
@Service
public class CmsCatalogService
{
    @Autowired
    private CmsCatalogMapper cmsCatalogMapper;

    /**
     * 查询栏目管理
     *
     * @param id 栏目管理主键
     * @return 栏目管理
     */
    public CmsCatalog selectCmsCatalogById(Long id)
    {
        return cmsCatalogMapper.selectCmsCatalogById(id);
    }

    /**
     * 查询栏目管理列表
     *
     * @param cmsCatalog 栏目管理
     * @return 栏目管理
     */
    public List<CmsCatalog> selectCmsCatalogList(CmsCatalog cmsCatalog)
    {
        return cmsCatalogMapper.selectCmsCatalogList(cmsCatalog);
    }


    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<PTreeSelect> buildTreeSelect(List<CmsCatalog> depts)
    {
        List<CmsCatalog> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(PTreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 构建前端所需要树结构
     *
     * @param datas 部门列表
     * @return 树结构列表
     */
    public List<CmsCatalog> buildDeptTree(List<CmsCatalog> datas)
    {
        List<CmsCatalog> returnList = new ArrayList<CmsCatalog>();
        List<Long> tempList = new ArrayList<Long>();
        for (CmsCatalog dept : datas)
        {
            tempList.add(dept.getId());
        }
        for (CmsCatalog dept : datas)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(datas, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = datas;
        }
        return returnList;
    }


    private void recursionFn(List<CmsCatalog> list, CmsCatalog t)
    {
        // 得到子节点列表
        List<CmsCatalog> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CmsCatalog tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }


    /**
     * 得到子节点列表
     */
    private List<CmsCatalog> getChildList(List<CmsCatalog> list, CmsCatalog t)
    {
        List<CmsCatalog> tlist = new ArrayList<CmsCatalog>();
        Iterator<CmsCatalog> it = list.iterator();
        while (it.hasNext())
        {
            CmsCatalog n = (CmsCatalog) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<CmsCatalog> list, CmsCatalog t)
    {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 新增栏目管理
     *
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    public int insertCmsCatalog(CmsCatalog cmsCatalog)
    {
        cmsCatalog.setTenantId(SecurityUtils.getTenantId());
        return cmsCatalogMapper.insertCmsCatalog(cmsCatalog);
    }

    /**
     * 修改栏目管理
     *
     * @param cmsCatalog 栏目管理
     * @return 结果
     */
    public int updateCmsCatalog(CmsCatalog cmsCatalog)
    {
        return cmsCatalogMapper.updateCmsCatalog(cmsCatalog);
    }

    /**
     * 批量删除栏目管理
     *
     * @param ids 需要删除的栏目管理主键
     * @return 结果
     */
    public int deleteCmsCatalogByIds(Long[] ids)
    {
        return cmsCatalogMapper.deleteCmsCatalogByIds(ids);
    }

    /**
     * 删除栏目管理信息
     *
     * @param id 栏目管理主键
     * @return 结果
     */
    public int deleteCmsCatalogById(Long id)
    {
        return cmsCatalogMapper.deleteCmsCatalogById(id);
    }
}
