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
 * 酒店申请对象 oa_hotel_apply
 * 
 * @author fno
 * @date 2023-05-29
 */
@Data
public class OaHotelApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 用户ID（单据 拥有者） */
    private Long userId;

    /** 部门ID（单据拥有部门） */
    private Long deptId;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String status;

    /** 申请流程 */
    private String flowKey;

    /** 流程实例id */
    private String flowInsId;

    /** 申请标题 */
    private String applyTitle;

    /** 提交时间 */
    private Date submitTime;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String billCode;

    private String billType;

    /** 使用城市 */
    @Excel(name = "使用城市")
    private String applyCity;

    /** 所在街道 */
    @Excel(name = "所在街道")
    private String street;

    /** 酒店名称 */
    @Excel(name = "酒店名称")
    private String hotelName;

    /** 预计金额 */
    @Excel(name = "预计金额")
    private BigDecimal totalAmt;

    /** 房间类型 */
    @Excel(name = "房间类型")
    private String roomType;

    /** 房间数量 */
    @Excel(name = "房间数量")
    private Long roomCount;

    /** 房间等级 */
    @Excel(name = "房间等级")
    private String roomLevel;

    /** 入住人数 */
    @Excel(name = "入住人数")
    private Long userCount;

    /** 入住时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入住时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** 事项描述 */
    @Excel(name = "事项描述")
    private String reasonDes;




    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("applyTitle", getApplyTitle())
            .append("submitTime", getSubmitTime())
            .append("billcode", getBillCode())
            .append("remark", getRemark())
            .append("applyCity", getApplyCity())
            .append("street", getStreet())
            .append("hotelName", getHotelName())
            .append("totalAmt", getTotalAmt())
            .append("roomType", getRoomType())
            .append("roomCount", getRoomCount())
            .append("roomLevel", getRoomLevel())
            .append("userCount", getUserCount())
            .append("useTime", getUseTime())
            .append("reasonDes", getReasonDes())
            .toString();
    }
}
