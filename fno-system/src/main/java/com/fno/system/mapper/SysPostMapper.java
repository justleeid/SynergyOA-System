package com.fno.system.mapper;

import java.util.List;

import com.fno.system.domain.SysPost;
import org.apache.ibatis.annotations.Param;

/**
 * 职位信息 数据层
 * 
 * @author ry
 */
public interface SysPostMapper
{
    /**
     * 查询职位数据集合
     * 
     * @param post 职位信息
     * @return 职位数据集合
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有职位
     * 
     * @return 职位列表
     */
    public List<SysPost> selectPostAll(Long tenantId);

    /**
     * 通过职位ID查询职位信息
     * 
     * @param postId 职位ID
     * @return 角色对象信息
     */
    public SysPost selectPostById(Long postId);

    /**
     * 根据用户ID获取职位选择框列表
     * 
     * @param userId 用户ID
     * @return 选中职位ID列表
     */
    public List<Long> selectPostListByUserId(Long userId);

    /**
     * 查询用户所属职位组
     * 
     * @param userName 用户名
     * @return 结果
     */
    public List<SysPost> selectPostsByUserName(@Param("userName") String userName, @Param("tenantId") Long tenantId);

    /**
     * 删除职位信息
     * 
     * @param postId 职位ID
     * @return 结果
     */
    public int deletePostById(Long postId);

    /**
     * 批量删除职位信息
     * 
     * @param postIds 需要删除的职位ID
     * @return 结果
     */
    public int deletePostByIds(Long[] postIds);

    /**
     * 修改职位信息
     * 
     * @param post 职位信息
     * @return 结果
     */
    public int updatePost(SysPost post);

    /**
     * 新增职位信息
     * 
     * @param post 职位信息
     * @return 结果
     */
    public int insertPost(SysPost post);

    /**
     * 校验职位名称
     * 
     * @param postName 职位名称
     * @return 结果
     */
    public SysPost checkPostNameUnique(String postName);

    /**
     * 校验职位编码
     * 
     * @param postCode 职位编码
     * @return 结果
     */
    public SysPost checkPostCodeUnique(String postCode);
}
