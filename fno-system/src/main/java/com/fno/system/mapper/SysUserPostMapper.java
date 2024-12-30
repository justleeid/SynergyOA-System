package com.fno.system.mapper;

import java.util.List;

import com.fno.system.domain.SysUserPost;

/**
 * 用户与职位关联表 数据层
 * 
 * @author ry
 */
public interface SysUserPostMapper
{
    /**
     * 通过用户ID删除用户和职位关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);

    /**
     * 通过职位ID查询职位使用数量
     * 
     * @param postId 职位ID
     * @return 结果
     */
    public int countUserPostById(Long postId);

    /**
     * 批量删除用户和职位关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPost(Long[] ids);

    /**
     * 批量新增用户职位信息
     * 
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);
}
