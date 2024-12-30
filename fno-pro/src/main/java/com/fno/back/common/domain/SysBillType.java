package com.fno.back.common.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 单据类型配置对象 oa_bill_type
 *
 * @author fno
 * @date 2023-08-30
 */
@Data
public class SysBillType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 单据类型名称 */
    @Excel(name = "单据类型名称")
    private String billName;

    /** 对应主表 */
    @Excel(name = "对应主表")
    private String tableName;

    /** 流程定义编码 */
    @Excel(name = "流程定义编码")
    private String defKey;

    /** 路由地址 */
    @Excel(name = "路由地址")
    private String formUrl;

    /** 租户ID */
    @Excel(name = "租户ID")
    private Long tenantId;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    private String appImgUrl;
    private String appShowName;
    private String appStatus ;
    private String appFormUrl;
    private String appListUrl;

    private String icon;

    private Integer sort;


    private String homepageShow;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("billType", getBillType())
                .append("billName", getBillName())
                .append("tableName", getTableName())
                .append("defKey", getDefKey())
                .append("formUrl", getFormUrl())
                .append("tenantId", getTenantId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("status", getStatus())
                .toString();
    }
}
