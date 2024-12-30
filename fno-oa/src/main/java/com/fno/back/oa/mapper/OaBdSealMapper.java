package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaBdSeal;

/**
 * 公章管理Mapper接口
 * 
 * @author fno
 * @date 2023-05-29
 */
public interface OaBdSealMapper 
{
    /**
     * 查询公章管理
     * 
     * @param id 公章管理主键
     * @return 公章管理
     */
    public OaBdSeal selectOaBdSealById(Long id);

    /**
     * 查询公章管理列表
     * 
     * @param oaBdSeal 公章管理
     * @return 公章管理集合
     */
    public List<OaBdSeal> selectOaBdSealList(OaBdSeal oaBdSeal);

    /**
     * 新增公章管理
     * 
     * @param oaBdSeal 公章管理
     * @return 结果
     */
    public int insertOaBdSeal(OaBdSeal oaBdSeal);

    /**
     * 修改公章管理
     * 
     * @param oaBdSeal 公章管理
     * @return 结果
     */
    public int updateOaBdSeal(OaBdSeal oaBdSeal);

    /**
     * 删除公章管理
     * 
     * @param id 公章管理主键
     * @return 结果
     */
    public int deleteOaBdSealById(Long id);

    /**
     * 批量删除公章管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaBdSealByIds(Long[] ids);
}
