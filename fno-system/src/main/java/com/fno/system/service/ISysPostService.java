package com.fno.system.service;

import java.util.List;

import com.fno.system.domain.SysPost;

/**
 * 职位信息 服务层
 * 
 * @author ry
 */
public interface ISysPostService
{
    /**
     * 查询职位信息集合
     * 
     * @param post 职位信息
     * @return 职位列表
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
     * 校验职位名称
     * 
     * @param post 职位信息
     * @return 结果
     */
    public String checkPostNameUnique(SysPost post);

    /**
     * 校验职位编码
     * 
     * @param post 职位信息
     * @return 结果
     */
    public String checkPostCodeUnique(SysPost post);

    /**
     * 通过职位ID查询职位使用数量
     * 
     * @param postId 职位ID
     * @return 结果
     */
    public int countUserPostById(Long postId);

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
     * 新增保存职位信息
     * 
     * @param post 职位信息
     * @return 结果
     */
    public int insertPost(SysPost post);

    /**
     * 修改保存职位信息
     * 
     * @param post 职位信息
     * @return 结果
     */
    public int updatePost(SysPost post);
}
