package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaTaxNum;

/**
 * 税号管理Mapper接口
 *
 */
public interface OaTaxNumMapper 
{
    /**
     * 查询税号管理
     * 
     * @param id 税号管理主键
     * @return 税号管理
     */
    public OaTaxNum selectOaTaxNumById(Long id);

    /**
     * 查询税号管理列表
     * 
     * @param oaTaxNum 税号管理
     * @return 税号管理集合
     */
    public List<OaTaxNum> selectOaTaxNumList(OaTaxNum oaTaxNum);

    /**
     * 新增税号管理
     * 
     * @param oaTaxNum 税号管理
     * @return 结果
     */
    public int insertOaTaxNum(OaTaxNum oaTaxNum);

    /**
     * 修改税号管理
     * 
     * @param oaTaxNum 税号管理
     * @return 结果
     */
    public int updateOaTaxNum(OaTaxNum oaTaxNum);

    /**
     * 删除税号管理
     * 
     * @param id 税号管理主键
     * @return 结果
     */
    public int deleteOaTaxNumById(Long id);

    /**
     * 批量删除税号管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaTaxNumByIds(Long[] ids);
}
