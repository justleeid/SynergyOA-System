package com.fno.system.service.impl;

import java.util.List;

import com.fno.system.domain.SysLogininfor;
import com.fno.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.system.mapper.SysLogininforMapper;
import com.fno.system.service.ISysLogininforService;

/**
 * 系统访问日志情况信息 服务层处理
 * 
 * @author ry
 */
@Service
public class SysLogininforServiceImpl implements ISysLogininforService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SysLogininforServiceImpl.class);
    @Autowired
    private SysLogininforMapper logininforMapper;

    /**
     * 新增系统登录日志
     * 
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor)
    {
        try{
            // 因为没登录，此处无法获取租户ID
            //Long tenantId = SecurityUtils.getTenantId();
            //logininfor.setTenantId(tenantId);
            logininforMapper.insertLogininfor(logininfor);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * 查询系统登录日志集合
     * 
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor)
    {
        logininfor.setTenantId(SecurityUtils.getTenantId());
        return logininforMapper.selectLogininforList(logininfor);
    }

    /**
     * 批量删除系统登录日志
     * 
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    @Override
    public int deleteLogininforByIds(Long[] infoIds)
    {
        return logininforMapper.deleteLogininforByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLogininfor()
    {
        logininforMapper.cleanLogininfor();
    }
}
