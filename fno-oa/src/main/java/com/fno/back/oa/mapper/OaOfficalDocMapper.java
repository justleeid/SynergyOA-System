package com.fno.back.oa.mapper;

import java.util.List;
import com.fno.back.oa.domain.OaOfficalDoc;

/**
 * 公文发文Mapper接口
 * 
 * @author fno
 * @date 2023-11-05
 */
public interface OaOfficalDocMapper 
{
    /**
     * 查询公文发文
     * 
     * @param id 公文发文主键
     * @return 公文发文
     */
    public OaOfficalDoc selectOaOfficalDocById(Long id);

    /**
     * 查询公文发文列表
     * 
     * @param oaOfficalDoc 公文发文
     * @return 公文发文集合
     */
    public List<OaOfficalDoc> selectOaOfficalDocList(OaOfficalDoc oaOfficalDoc);


    /**
     * 查询公文发文列表
     *
     * @param oaOfficalDoc 公文收文
     * @return 公文收文集合
     */
    public List<OaOfficalDoc> receiveList(OaOfficalDoc oaOfficalDoc);

    /**
     * 新增公文发文
     * 
     * @param oaOfficalDoc 公文发文
     * @return 结果
     */
    public int insertOaOfficalDoc(OaOfficalDoc oaOfficalDoc);

    /**
     * 修改公文发文
     * 
     * @param oaOfficalDoc 公文发文
     * @return 结果
     */
    public int updateOaOfficalDoc(OaOfficalDoc oaOfficalDoc);

    /**
     * 删除公文发文
     * 
     * @param id 公文发文主键
     * @return 结果
     */
    public int deleteOaOfficalDocById(Long id);

    /**
     * 批量删除公文发文
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaOfficalDocByIds(Long[] ids);
}
