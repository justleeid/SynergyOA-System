package com.fno.system.service.impl;

import java.util.List;

import com.fno.system.domain.SysPost;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.common.constant.UserConstants;
import com.fno.common.exception.ServiceException;
import com.fno.common.utils.StringUtils;
import com.fno.system.mapper.SysPostMapper;
import com.fno.system.mapper.SysUserPostMapper;
import com.fno.system.service.ISysPostService;

/**
 * 职位信息 服务层处理
 * 
 * @author ry
 */
@Service
public class SysPostServiceImpl implements ISysPostService
{
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;
    @Autowired
    private WorkflowIdentityService workflowIdentityService;

    /**
     * 查询职位信息集合
     * 
     * @param post 职位信息
     * @return 职位信息集合
     */
    @Override
    public List<SysPost> selectPostList(SysPost post)
    {
        Long tenantId = SecurityUtils.getTenantId();
        post.setTenantId(tenantId);
        return postMapper.selectPostList(post);
    }

    /**
     * 查询所有职位
     * 
     * @return 职位列表
     */
    @Override
    public List<SysPost> selectPostAll(Long tenantId)
    {
        return postMapper.selectPostAll(tenantId);
    }

    /**
     * 通过职位ID查询职位信息
     * 
     * @param postId 职位ID
     * @return 角色对象信息
     */
    @Override
    public SysPost selectPostById(Long postId)
    {
        return postMapper.selectPostById(postId);
    }

    /**
     * 根据用户ID获取职位选择框列表
     * 
     * @param userId 用户ID
     * @return 选中职位ID列表
     */
    @Override
    public List<Long> selectPostListByUserId(Long userId)
    {
        return postMapper.selectPostListByUserId(userId);
    }

    /**
     * 校验职位名称是否唯一
     * 
     * @param post 职位信息
     * @return 结果
     */
    @Override
    public String checkPostNameUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验职位编码是否唯一
     * 
     * @param post 职位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = postMapper.checkPostCodeUnique(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 通过职位ID查询职位使用数量
     * 
     * @param postId 职位ID
     * @return 结果
     */
    @Override
    public int countUserPostById(Long postId)
    {
        return userPostMapper.countUserPostById(postId);
    }

    /**
     * 删除职位信息
     * 
     * @param postId 职位ID
     * @return 结果
     */
    @Override
    public int deletePostById(Long postId)
    {
        workflowIdentityService.deleteRoleAllUser(postId.toString());
        return postMapper.deletePostById(postId);
    }

    /**
     * 批量删除职位信息
     * 
     * @param postIds 需要删除的职位ID
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] postIds)
    {
        for (Long postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
            workflowIdentityService.deleteRoleAllUser(postId.toString());
        }

        return postMapper.deletePostByIds(postIds);
    }

    /**
     * 新增保存职位信息
     * 
     * @param post 职位信息
     * @return 结果
     */
    @Override
    public int insertPost(SysPost post)
    {
        int n = postMapper.insertPost(post);
        //添加新岗位
        workflowIdentityService.addGroup(post.getPostId().toString(),post.getPostName());
        return n;
    }

    /**
     * 修改保存职位信息
     * 
     * @param post 职位信息
     * @return 结果
     */
    @Override
    public int updatePost(SysPost post)
    {

        return postMapper.updatePost(post);
    }
}
