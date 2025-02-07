package com.fno.back.oa.domain;

import lombok.Data;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 办公用品对象 oa_office_goods
 *
 */
@Data
public class OaOfficeGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;

    /** 剩余数量 */
    @Excel(name = "剩余数量")
    private BigDecimal quantity;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 状态 */
    @Excel(name = "状态")
    private String status;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("unit", getUnit())
            .append("quantity", getQuantity())
            .append("brand", getBrand())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
