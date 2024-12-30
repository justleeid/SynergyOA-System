package com.fno.back.common.service;

import com.fno.back.common.domain.SysUserCommunicateBook;
import com.fno.back.common.mapper.SysUserCommunicateBookMapper;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * @des
 * @author Ly
 * @date 2023/7/31
 */
@Service
public class SysCommunicateBookService {

    @Autowired
    private SysUserCommunicateBookMapper communicateBookMapper;

    public List<Map> querySysCommunicateBook(SysUser sysUser){
        sysUser.setTenantId(SecurityUtils.getTenantId());
        sysUser.setUserId(SecurityUtils.getUserId());
        List<Map> sysBook = communicateBookMapper.querySysCommunicateBook(sysUser);
        return sysBook;
    }



    @Autowired
    private SysUserCommunicateBookMapper sysUserCommunicateBookMapper;

    /**
     * 查询个人通讯录
     *
     * @param id 个人通讯录主键
     * @return 个人通讯录
     */
    public SysUserCommunicateBook selectOaUserCommunicateBookById(Long id)
    {
        return sysUserCommunicateBookMapper.selectOaUserCommunicateBookById(id);
    }

    /**
     * 查询个人通讯录列表
     *
     * @param sysUserCommunicateBook 个人通讯录
     * @return 个人通讯录
     */
    public List<SysUserCommunicateBook> selectOaUserCommunicateBookList(SysUserCommunicateBook sysUserCommunicateBook)
    {
        //如果不是admin用户，则只能查看自己创建的申请单
        if(!SecurityUtils.isAdmin()){
            sysUserCommunicateBook.setUserId(SecurityUtils.getUserId());
        }
        //租户ID
        sysUserCommunicateBook.setTenantId(SecurityUtils.getTenantId());
        return sysUserCommunicateBookMapper.selectOaUserCommunicateBookList(sysUserCommunicateBook);
    }

    /**
     * 新增个人通讯录
     *
     * @param sysUserCommunicateBook 个人通讯录
     * @return 结果
     */
    public int insertOaUserCommunicateBook(SysUserCommunicateBook sysUserCommunicateBook)
    {
        sysUserCommunicateBook.setCreateTime(DateUtils.getNowDate());
        sysUserCommunicateBook.setUserId(SecurityUtils.getUserId());

        //租户ID
        sysUserCommunicateBook.setTenantId(SecurityUtils.getTenantId());
        return sysUserCommunicateBookMapper.insertOaUserCommunicateBook(sysUserCommunicateBook);
    }

    /**
     * 修改个人通讯录
     *
     * @param sysUserCommunicateBook 个人通讯录
     * @return 结果
     */
    public int updateOaUserCommunicateBook(SysUserCommunicateBook sysUserCommunicateBook)
    {
        sysUserCommunicateBook.setUpdateTime(DateUtils.getNowDate());
        return sysUserCommunicateBookMapper.updateOaUserCommunicateBook(sysUserCommunicateBook);
    }

    /**
     * 批量删除个人通讯录
     *
     * @param ids 需要删除的个人通讯录主键
     * @return 结果
     */
    public int deleteOaUserCommunicateBookByIds(Long[] ids)
    {
        return sysUserCommunicateBookMapper.deleteOaUserCommunicateBookByIds(ids);
    }

    /**
     * 删除个人通讯录信息
     *
     * @param id 个人通讯录主键
     * @return 结果
     */
    public int deleteOaUserCommunicateBookById(Long id)
    {
        return sysUserCommunicateBookMapper.deleteOaUserCommunicateBookById(id);
    }
}
