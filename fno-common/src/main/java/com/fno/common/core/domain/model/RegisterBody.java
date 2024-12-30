package com.fno.common.core.domain.model;

/**
 * 用户注册对象
 * 
 * @author ry
 */

public class RegisterBody extends LoginBody
{
    private String tenantName;

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
}
