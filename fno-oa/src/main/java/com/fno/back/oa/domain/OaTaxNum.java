package com.fno.back.oa.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 税号管理对象 oa_tax_num
 * 
 * @author fno
 * @date 2023-08-12
 */
@Data
public class OaTaxNum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 租户id */
    private Long tenantId;

    /** 税号 */
    @Excel(name = "税号")
    private String taxNum;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String companyName;

    /** 法人 */
    @Excel(name = "法人")
    private String legalPerson;

    /** 企业类型 */
    @Excel(name = "企业类型")
    private String type;

    /** 成立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date establishDate;

    /** 地址 */
    @Excel(name = "地址")
    private String address;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taxNum", getTaxNum())
            .append("companyName", getCompanyName())
            .append("legalPerson", getLegalPerson())
            .append("type", getType())
            .append("establishDate", getEstablishDate())
            .append("address", getAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
