package com.fno.back.oa.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 发票管理对象 oa_invoice
 * 
 * @author fno
 * @date 2023-08-12
 */
@Data
public class OaInvoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /**租户ID */
    private Long tenantId;
    /** 发票文件地址 */
    @Excel(name = "发票文件地址")
    private String url;

    /** 发票金额 */
    @Excel(name = "发票金额")
    private BigDecimal amt;

    /** 开票时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开票时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invoiceDate;

    /** 发票代码 */
    @Excel(name = "发票代码")
    private String invoiceCode;

    /** 发票号码 */
    @Excel(name = "发票号码")
    private String invoiceNum;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

    /** 税额 */
    @Excel(name = "税额")
    private BigDecimal taxAmt;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String name;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String companyName;

    /** 纳税识别号 */
    @Excel(name = "纳税识别号")
    private String taxNum;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 银行 */
    @Excel(name = "银行")
    private String bank;

    /** 收款人 */
    @Excel(name = "收款人")
    private String payee;

    /** 复核人 */
    @Excel(name = "复核人")
    private String examinePerson;

    /** 开票人 */
    @Excel(name = "开票人")
    private String drawer;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("url", getUrl())
            .append("amt", getAmt())
            .append("invoiceDate", getInvoiceDate())
            .append("invoiceCode", getInvoiceCode())
            .append("invoiceNum", getInvoiceNum())
            .append("taxRate", getTaxRate())
            .append("taxAmt", getTaxAmt())
            .append("name", getName())
            .append("companyName", getCompanyName())
            .append("taxNum", getTaxNum())
            .append("address", getAddress())
            .append("phone", getPhone())
            .append("bank", getBank())
            .append("payee", getPayee())
            .append("examinePerson", getExaminePerson())
            .append("drawer", getDrawer())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
