package com.fno.back.oa.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 车辆管理对象 oa_bd_car
 * 
 * @author fno
 * @date 2023-05-29
 */
@Data
public class OaBdCar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    private Long tenantId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNo;

    /** 车型 */
    @Excel(name = "车型")
    private String carModel;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    private String useStatus;

    /** 座位数(含司机) */
    @Excel(name = "座位数(含司机)")
    private Long seatCount;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carNo", getCarNo())
            .append("carModel", getCarModel())
            .append("color", getColor())
            .append("status", getStatus())
            .append("seatCount", getSeatCount())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
