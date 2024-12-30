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
 * 假勤申请对象 oa_duty_apply
 * 
 * @author fno
 * @date 2023-06-01
 */
@Data
public class OaDutyApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 申请类型 */
    @Excel(name = "申请类型")
    private String leaveType;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String billCode;

    /** 申请流程 */
    @Excel(name = "申请流程")
    private String flowKey;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String flowInsId;

    /** 申请标题 */
    @Excel(name = "申请标题")
    private String applyTitle;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 流程状态。0：未提交。1：审批中。2：已完成 */
    @Excel(name = "流程状态。0：未提交。1：审批中。2：已完成")
    private String status;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 天数 */
    @Excel(name = "天数")
    private BigDecimal days;

    /** 事由 */
    @Excel(name = "事由")
    private String reason;

    /** 用户ID（单据 拥有者） */
    @Excel(name = "用户ID", readConverterExp = "单=据,拥=有者")
    private Long userId;

    /** 部门ID（单据拥有部门） */
    @Excel(name = "部门ID", readConverterExp = "单=据拥有部门")
    private Long deptId;

    @Excel(name = "地点")
    private String destination;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("billType", getBillType())
            .append("leaveType", getLeaveType())
            .append("billCode", getBillCode())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("applyTitle", getApplyTitle())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("days", getDays())
            .append("reason", getReason())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
