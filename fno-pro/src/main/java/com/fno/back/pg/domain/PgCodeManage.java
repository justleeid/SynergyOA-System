package com.fno.back.pg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 代码管理对象 pg_code_manage
 * 
 * @author fno
 * @date 2023-04-15
 */
public class PgCodeManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    /** git地址 */
    @Excel(name = "git地址")
    private String gitUrl;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storeName;


    public void setId(Long id) 
    {
        this.id = id;
    }
    public Long getId() 
    {
        return id;
    }


    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }
    public Long getProjectId() 
    {
        return projectId;
    }


    public void setGitUrl(String gitUrl) 
    {
        this.gitUrl = gitUrl;
    }
    public String getGitUrl() 
    {
        return gitUrl;
    }


    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }
    public String getStoreName() 
    {
        return storeName;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("gitUrl", getGitUrl())
            .append("storeName", getStoreName())
            .toString();
    }
}
