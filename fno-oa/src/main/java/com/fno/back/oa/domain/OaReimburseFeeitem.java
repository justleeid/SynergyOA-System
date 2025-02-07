package com.fno.back.oa.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 报销费用明细对象 oa_reimburse_feeitem
 *
 */
public class OaReimburseFeeitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 报销单id */
    @Excel(name = "报销单id")
    private Long reimburseId;

    /** 款项金额 */
    @Excel(name = "款项金额")
    private BigDecimal itemAmt;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 月份 */
    @Excel(name = "月份")
    private String month;

    /** 票据类型 */
    @Excel(name = "票据类型")
    private String billType;

    /** 票据张数 */
    @Excel(name = "票据张数")
    private Long billCount;

    /** 起始地 */
    @Excel(name = "起始地")
    private String startCity;

    /** 目的地 */
    @Excel(name = "目的地")
    private String endCity;

    /** 票据日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "票据日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date billDate;

    /** 天数 */
    @Excel(name = "天数")
    private BigDecimal dayCount;

    /** 补贴标准 */
    @Excel(name = "补贴标准")
    private BigDecimal standardSubsidy;

    /** 费用类型 */
    @Excel(name = "费用类型")
    private String itemType;

    /** 住宿地点 */
    @Excel(name = "住宿地点")
    private String stayCity;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setReimburseId(Long reimburseId) 
    {
        this.reimburseId = reimburseId;
    }

    public Long getReimburseId() 
    {
        return reimburseId;
    }
    public void setItemAmt(BigDecimal itemAmt) 
    {
        this.itemAmt = itemAmt;
    }

    public BigDecimal getItemAmt() 
    {
        return itemAmt;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }

    public String getYear() 
    {
        return year;
    }
    public void setMonth(String month) 
    {
        this.month = month;
    }

    public String getMonth() 
    {
        return month;
    }
    public void setBillType(String billType) 
    {
        this.billType = billType;
    }

    public String getBillType() 
    {
        return billType;
    }
    public void setBillCount(Long billCount) 
    {
        this.billCount = billCount;
    }

    public Long getBillCount() 
    {
        return billCount;
    }
    public void setStartCity(String startCity) 
    {
        this.startCity = startCity;
    }

    public String getStartCity() 
    {
        return startCity;
    }
    public void setEndCity(String endCity) 
    {
        this.endCity = endCity;
    }

    public String getEndCity() 
    {
        return endCity;
    }
    public void setBillDate(Date billDate) 
    {
        this.billDate = billDate;
    }

    public Date getBillDate() 
    {
        return billDate;
    }
    public void setDayCount(BigDecimal dayCount) 
    {
        this.dayCount = dayCount;
    }

    public BigDecimal getDayCount() 
    {
        return dayCount;
    }
    public void setStandardSubsidy(BigDecimal standardSubsidy) 
    {
        this.standardSubsidy = standardSubsidy;
    }

    public BigDecimal getStandardSubsidy() 
    {
        return standardSubsidy;
    }
    public void setItemType(String itemType) 
    {
        this.itemType = itemType;
    }

    public String getItemType() 
    {
        return itemType;
    }
    public void setStayCity(String stayCity) 
    {
        this.stayCity = stayCity;
    }

    public String getStayCity() 
    {
        return stayCity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("reimburseId", getReimburseId())
            .append("itemAmt", getItemAmt())
            .append("year", getYear())
            .append("month", getMonth())
            .append("billType", getBillType())
            .append("billCount", getBillCount())
            .append("startCity", getStartCity())
            .append("endCity", getEndCity())
            .append("billDate", getBillDate())
            .append("dayCount", getDayCount())
            .append("standardSubsidy", getStandardSubsidy())
            .append("itemType", getItemType())
            .append("stayCity", getStayCity())
            .toString();
    }
}
