package com.fno.back.common.mapper;

import com.fno.back.common.domain.SysUserCommunicateBook;
import com.fno.common.core.domain.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * 个人通讯录Mapper接口
 * 
 * @author fno
 * @date 2023-07-31
 */
public interface SysUserCommunicateBookMapper
{

    public List<Map> querySysCommunicateBook(SysUser user);
    /**
     * 查询个人通讯录
     * 
     * @param id 个人通讯录主键
     * @return 个人通讯录
     */
    public SysUserCommunicateBook selectOaUserCommunicateBookById(Long id);

    /**
     * 查询个人通讯录列表
     * 
     * @param sysUserCommunicateBook 个人通讯录
     * @return 个人通讯录集合
     */
    public List<SysUserCommunicateBook> selectOaUserCommunicateBookList(SysUserCommunicateBook sysUserCommunicateBook);

    /**
     * 新增个人通讯录
     * 
     * @param sysUserCommunicateBook 个人通讯录
     * @return 结果
     */
    public int insertOaUserCommunicateBook(SysUserCommunicateBook sysUserCommunicateBook);

    /**
     * 修改个人通讯录
     * 
     * @param sysUserCommunicateBook 个人通讯录
     * @return 结果
     */
    public int updateOaUserCommunicateBook(SysUserCommunicateBook sysUserCommunicateBook);

    /**
     * 删除个人通讯录
     * 
     * @param id 个人通讯录主键
     * @return 结果
     */
    public int deleteOaUserCommunicateBookById(Long id);

    /**
     * 批量删除个人通讯录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaUserCommunicateBookByIds(Long[] ids);
}
