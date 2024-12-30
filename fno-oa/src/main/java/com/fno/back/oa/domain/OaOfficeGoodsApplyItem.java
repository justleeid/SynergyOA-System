package com.fno.back.oa.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 办公用品领用明细对象 oa_office_goods_apply_item
 * 
 * @author fno
 * @date 2023-08-09
 */
public class OaOfficeGoodsApplyItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 申请id */
    @Excel(name = "申请id")
    private Long applyId;

    /** 单据id */
    @Excel(name = "单据id")
    private Long goodsId;

    /** 领用数量 */
    @Excel(name = "领用数量")
    private BigDecimal goodsCount;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String unit;

    /** 品牌 */
    @Excel(name = "品牌")
    private String brand;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsCount(BigDecimal goodsCount) 
    {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsCount() 
    {
        return goodsCount;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setBrand(String brand) 
    {
        this.brand = brand;
    }

    public String getBrand() 
    {
        return brand;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("applyId", getApplyId())
            .append("goodsId", getGoodsId())
            .append("goodsCount", getGoodsCount())
            .append("name", getName())
            .append("unit", getUnit())
            .append("brand", getBrand())
            .append("price", getPrice())
            .toString();
    }
}
