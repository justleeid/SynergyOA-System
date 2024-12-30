package com.fno.back.common.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 公司管理-租户对象 sys_company_tenant
 * 
 * @author fno
 * @date 2023-08-15
 */
@Data
public class CompanyTenant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String name;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 法人 */
    @Excel(name = "法人")
    private String legalPerson;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 营业执照 */
    @Excel(name = "营业执照")
    private String businessLicense;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String businessScope;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationTime;

    private String status;

    private String ifInit;

    private String idNum;

    private String adminName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("legalPerson", getLegalPerson())
            .append("businessLicense", getBusinessLicense())
            .append("businessScope", getBusinessScope())
            .append("expirationTime", getExpirationTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
