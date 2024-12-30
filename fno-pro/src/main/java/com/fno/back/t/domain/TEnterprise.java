package com.fno.back.t.domain;

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
 * 企业对象 t_enterprise
 * 
 * @author fno
 * @date 2024-02-27
 */
@Data
@ApiModel(value = "TEnterprise", description = "企业")
public class TEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    @ApiModelProperty("企业名称")
    private String name;

    /** 地址 */
    @Excel(name = "地址")
    @ApiModelProperty("地址")
    private String address;

    /** 联系电话 */
    @Excel(name = "联系电话")
    @ApiModelProperty("联系电话")
    private String tel;

    /** 产值 */
    @Excel(name = "产值")
    @ApiModelProperty("产值")
    private String productAmt;

    /** 税收 */
    @Excel(name = "税收")
    @ApiModelProperty("税收")
    private String tax;

    /** 是否高新 */
    @Excel(name = "是否高新")
    @ApiModelProperty("是否高新")
    private String ifNew;

    /** 年度 */
    @Excel(name = "年度")
    @ApiModelProperty("年度")
    private String year;

    /** 是否进出口 */
    @Excel(name = "是否进出口")
    @ApiModelProperty("是否进出口")
    private String ifFoign;

    /** 进口总额 */
    @Excel(name = "进口总额")
    @ApiModelProperty("进口总额")
    private String inAmt;

    /** 出口总额 */
    @Excel(name = "出口总额")
    @ApiModelProperty("出口总额")
    private String outAmt;

    /** 所属园区 */
    @Excel(name = "所属园区")
    @ApiModelProperty("所属园区")
    private String fromPlace;

    /** 企业人数 */
    @Excel(name = "企业人数")
    @ApiModelProperty("企业人数")
    private String personCount;

    /** 租户ID */
    @Excel(name = "租户ID")
    @ApiModelProperty("租户ID")
    private Long tenantId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("tel", getTel())
            .append("productAmt", getProductAmt())
            .append("tax", getTax())
            .append("ifNew", getIfNew())
            .append("year", getYear())
            .append("ifFoign", getIfFoign())
            .append("inAmt", getInAmt())
            .append("outAmt", getOutAmt())
            .append("fromPlace", getFromPlace())
            .append("personCount", getPersonCount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .toString();
    }
}
