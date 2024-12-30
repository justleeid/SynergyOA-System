package com.fno.back.common.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 计量单位对象 pl_unit
 * 
 * @author fno
 * @date 2022-10-20
 */
@Data
public class SysUnit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String name;

    /** 基础单位 */
    @Excel(name = "基础单位")
    private String basicUnit;

    /** 副单位1 */
    @Excel(name = "副单位1")
    private String otherUnitOne;

    /** 副单位2 */
    @Excel(name = "副单位2")
    private String otherUnitTwo;

    /** 副单位3 */
    @Excel(name = "副单位3")
    private String otherUnitThree;

    /** 副单位4 */
    @Excel(name = "副单位4")
    private String otherUnitFour;

    /** 副单位5 */
    @Excel(name = "副单位5")
    private String otherUnitFive;

    /** 副单位6 */
    @Excel(name = "副单位6")
    private String otherUnitSix;

    /** 比例1 */
    @Excel(name = "比例1")
    private BigDecimal ratioOne;

    /** 比例2 */
    @Excel(name = "比例2")
    private BigDecimal ratioTwo;

    /** 比例3 */
    @Excel(name = "比例3")
    private BigDecimal ratioThree;

    /** 比例4 */
    @Excel(name = "比例4")
    private BigDecimal ratioFour;

    /** 比例5 */
    @Excel(name = "比例5")
    private BigDecimal ratioFive;

    /** 比例6 */
    @Excel(name = "比例6")
    private BigDecimal ratioSix;

    /** 启用 */
    @Excel(name = "启用")
    private String status;

    /** 租户id */
    @Excel(name = "租户id")
    private Long tenantId;

    /** 删除标记，0未删除，1删除 */
    @Excel(name = "删除标记，0未删除，1删除")
    private String deleteFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("basicUnit", getBasicUnit())
            .append("otherUnitOne", getOtherUnitOne())
            .append("otherUnitTwo", getOtherUnitTwo())
            .append("otherUnitThree", getOtherUnitThree())
            .append("otherUnitFour", getOtherUnitFour())
            .append("otherUnitFive", getOtherUnitFive())
            .append("otherUnitSix", getOtherUnitSix())
            .append("ratioOne", getRatioOne())
            .append("ratioTwo", getRatioTwo())
            .append("ratioThree", getRatioThree())
            .append("ratioFour", getRatioFour())
            .append("ratioFive", getRatioFive())
            .append("ratioSix", getRatioSix())
            .append("status", getStatus())
            .append("tenantId", getTenantId())
            .append("deleteFlag", getDeleteFlag())
            .toString();
    }
}
