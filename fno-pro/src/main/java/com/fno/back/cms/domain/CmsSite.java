package com.fno.back.cms.domain;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 站点管理对象 cms_site
 * 
 * @author fno
 * @date 2024-01-29
 */
@Data
@ApiModel(value = "CmsSite", description = "站点管理")
public class CmsSite extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 站点id */
    @Excel(name = "站点id")
    @ApiModelProperty("站点id")
    private Long id;

    /** 站点名称 */
    @Excel(name = "站点名称")
    @ApiModelProperty("站点名称")
    private String name;

    /** 域名地址 */
    @Excel(name = "域名地址")
    @ApiModelProperty("域名地址")
    private String domainUrl;

    /** 租户id */
    @Excel(name = "租户id")
    @ApiModelProperty("租户id")
    private Long tenantId;

    /** 可用状态 */
    @Excel(name = "可用状态")
    @ApiModelProperty("可用状态")
    private String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("domainUrl", getDomainUrl())
            .append("tenantId", getTenantId())
            .append("status", getStatus())
            .toString();
    }
}
