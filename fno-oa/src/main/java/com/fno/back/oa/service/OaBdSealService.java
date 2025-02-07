package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaBdSealMapper;
import com.fno.back.oa.domain.OaBdSeal;

/**
 * 公章管理Service业务层处理
 */
@Service
public class OaBdSealService
{
    @Autowired
    private OaBdSealMapper oaBdSealMapper;

    /**
     * 查询公章管理
     * 
     * @param id 公章管理主键
     * @return 公章管理
     */
    public OaBdSeal selectOaBdSealById(Long id)
    {
        return oaBdSealMapper.selectOaBdSealById(id);
    }

    /**
     * 查询公章管理列表
     * 
     * @param oaBdSeal 公章管理
     * @return 公章管理
     */
    public List<OaBdSeal> selectOaBdSealList(OaBdSeal oaBdSeal)
    {
        //租户
        oaBdSeal.setTenantId(SecurityUtils.getTenantId());
        return oaBdSealMapper.selectOaBdSealList(oaBdSeal);
    }

    /**
     * 新增公章管理
     * 
     * @param oaBdSeal 公章管理
     * @return 结果
     */
    public int insertOaBdSeal(OaBdSeal oaBdSeal)
    {
        oaBdSeal.setCreateTime(DateUtils.getNowDate());
        //租户
        oaBdSeal.setTenantId(SecurityUtils.getTenantId());
        oaBdSeal.setCreateBy(SecurityUtils.getUserId());
        return oaBdSealMapper.insertOaBdSeal(oaBdSeal);
    }

    /**
     * 修改公章管理
     * 
     * @param oaBdSeal 公章管理
     * @return 结果
     */
    public int updateOaBdSeal(OaBdSeal oaBdSeal)
    {
        oaBdSeal.setUpdateTime(DateUtils.getNowDate());
        oaBdSeal.setUpdateBy(SecurityUtils.getUserId());
        return oaBdSealMapper.updateOaBdSeal(oaBdSeal);
    }

    /**
     * 批量删除公章管理
     * 
     * @param ids 需要删除的公章管理主键
     * @return 结果
     */
    public int deleteOaBdSealByIds(Long[] ids)
    {
        return oaBdSealMapper.deleteOaBdSealByIds(ids);
    }

    /**
     * 删除公章管理信息
     * 
     * @param id 公章管理主键
     * @return 结果
     */
    public int deleteOaBdSealById(Long id)
    {
        return oaBdSealMapper.deleteOaBdSealById(id);
    }
}
