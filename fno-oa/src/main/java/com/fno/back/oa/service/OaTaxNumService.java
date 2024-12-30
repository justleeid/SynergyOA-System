package com.fno.back.oa.service;

import java.util.List;
import com.fno.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.oa.mapper.OaTaxNumMapper;
import com.fno.back.oa.domain.OaTaxNum;
import com.fno.back.oa.service.OaTaxNumService;

/**
 * 税号管理Service业务层处理
 * 
 * @author fno
 * @date 2023-08-12
 */
@Service
public class OaTaxNumService
{
    @Autowired
    private OaTaxNumMapper oaTaxNumMapper;

    /**
     * 查询税号管理
     * 
     * @param id 税号管理主键
     * @return 税号管理
     */
    public OaTaxNum selectOaTaxNumById(Long id)
    {
        return oaTaxNumMapper.selectOaTaxNumById(id);
    }

    /**
     * 查询税号管理列表
     * 
     * @param oaTaxNum 税号管理
     * @return 税号管理
     */
    public List<OaTaxNum> selectOaTaxNumList(OaTaxNum oaTaxNum)
    {
        return oaTaxNumMapper.selectOaTaxNumList(oaTaxNum);
    }

    /**
     * 新增税号管理
     * 
     * @param oaTaxNum 税号管理
     * @return 结果
     */
    public int insertOaTaxNum(OaTaxNum oaTaxNum)
    {
        oaTaxNum.setCreateTime(DateUtils.getNowDate());
        return oaTaxNumMapper.insertOaTaxNum(oaTaxNum);
    }

    /**
     * 修改税号管理
     * 
     * @param oaTaxNum 税号管理
     * @return 结果
     */
    public int updateOaTaxNum(OaTaxNum oaTaxNum)
    {
        oaTaxNum.setUpdateTime(DateUtils.getNowDate());
        return oaTaxNumMapper.updateOaTaxNum(oaTaxNum);
    }

    /**
     * 批量删除税号管理
     * 
     * @param ids 需要删除的税号管理主键
     * @return 结果
     */
    public int deleteOaTaxNumByIds(Long[] ids)
    {
        return oaTaxNumMapper.deleteOaTaxNumByIds(ids);
    }

    /**
     * 删除税号管理信息
     * 
     * @param id 税号管理主键
     * @return 结果
     */
    public int deleteOaTaxNumById(Long id)
    {
        return oaTaxNumMapper.deleteOaTaxNumById(id);
    }
}
