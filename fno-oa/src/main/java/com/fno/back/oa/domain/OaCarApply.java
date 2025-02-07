package com.fno.back.oa.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 车辆申请对象 oa_car_apply
 *
 */
@Data
public class OaCarApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 车辆id */
    @Excel(name = "车辆id")
    private Long carId;

    /** 使用缘由 */
    @Excel(name = "使用缘由")
    private String reason;

    /** 目的地 */
    @Excel(name = "目的地")
    private String destination;

    /** 用户ID（单据 拥有者） */
    @Excel(name = "用户ID", readConverterExp = "单=据,拥=有者")
    private Long userId;

    /** 部门ID（单据拥有部门） */
    @Excel(name = "部门ID", readConverterExp = "单=据拥有部门")
    private Long deptId;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 单据编码 */
    @Excel(name = "单据编码")
    private String billCode;

    /** 申请流程 */
    @Excel(name = "申请流程")
    private String flowKey;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String flowInsId;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 流程状态。0：未提交。1：审批中。2：已完成 */
    @Excel(name = "流程状态。0：未提交。1：审批中。2：已完成")
    private String status;


    /** 车牌号 */
    @Excel(name = "车牌号")
    private String carNo;

    /** 车型号 */
    @Excel(name = "车型号")
    private String carModel;

    private String color;
    private Integer seatCount;



    private String repairAddress;
    private String repairContent;
    private BigDecimal costAmt;
    private String maintenanceAddress;
    private String maintenanceContent;
    private String contactsPhone;
    private String scrapType;
    private BigDecimal incomeAmt;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carId", getCarId())
            .append("carNo", getCarNo())
            .append("carModel", getCarModel())
            .append("reason", getReason())
            .append("destination", getDestination())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
