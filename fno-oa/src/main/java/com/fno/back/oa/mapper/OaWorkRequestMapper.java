package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaWorkRequest;

/**
 * 工作请示Mapper接口
 * 
 * @author fno
 * @date 2024-03-04
 */
public interface OaWorkRequestMapper 
{
    /**
     * 查询工作请示
     * 
     * @param id 工作请示主键
     * @return 工作请示
     */
    public OaWorkRequest selectOaWorkRequestById(Long id);

    /**
     * 查询工作请示列表
     * 
     * @param oaWorkRequest 工作请示
     * @return 工作请示集合
     */
    public List<OaWorkRequest> selectOaWorkRequestList(OaWorkRequest oaWorkRequest);

    /**
     * 新增工作请示
     * 
     * @param oaWorkRequest 工作请示
     * @return 结果
     */
    public int insertOaWorkRequest(OaWorkRequest oaWorkRequest);

    /**
     * 修改工作请示
     * 
     * @param oaWorkRequest 工作请示
     * @return 结果
     */
    public int updateOaWorkRequest(OaWorkRequest oaWorkRequest);

    /**
     * 删除工作请示
     * 
     * @param id 工作请示主键
     * @return 结果
     */
    public int deleteOaWorkRequestById(Long id);

    /**
     * 批量删除工作请示
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaWorkRequestByIds(Long[] ids);
}
