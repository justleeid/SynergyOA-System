package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaOfficalDocRedheadtpl;

/**
 * 套红模版Mapper接口
 * 
 * @author fno
 * @date 2023-08-20
 */
public interface OaOfficalDocRedheadtplMapper 
{
    /**
     * 查询套红模版
     * 
     * @param id 套红模版主键
     * @return 套红模版
     */
    public OaOfficalDocRedheadtpl selectOaOfficalDocRedheadtplById(Long id);

    /**
     * 查询套红模版列表
     * 
     * @param oaOfficalDocRedheadtpl 套红模版
     * @return 套红模版集合
     */
    public List<OaOfficalDocRedheadtpl> selectOaOfficalDocRedheadtplList(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl);

    /**
     * 新增套红模版
     * 
     * @param oaOfficalDocRedheadtpl 套红模版
     * @return 结果
     */
    public int insertOaOfficalDocRedheadtpl(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl);

    /**
     * 修改套红模版
     * 
     * @param oaOfficalDocRedheadtpl 套红模版
     * @return 结果
     */
    public int updateOaOfficalDocRedheadtpl(OaOfficalDocRedheadtpl oaOfficalDocRedheadtpl);

    /**
     * 删除套红模版
     * 
     * @param id 套红模版主键
     * @return 结果
     */
    public int deleteOaOfficalDocRedheadtplById(Long id);

    /**
     * 批量删除套红模版
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaOfficalDocRedheadtplByIds(Long[] ids);
}
