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
 * 任务分配对象 oa_send_task
 *
 */
@Data
public class OaSendTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 发送用户ID */
    @Excel(name = "发送用户ID")
    private Long sendUserId;

    /** 发送用户名称 */
    @Excel(name = "发送用户名称")
    private String sendNickName;

    /** 接收人用户ID */
    @Excel(name = "接收人用户ID")
    private Long receiveUserId;

    /** 接收人用户名称 */
    @Excel(name = "接收人用户名称")
    private String receiveNickName;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String title;

    /** 任务内容 */
    @Excel(name = "任务内容")
    private String content;

    /** 协助人id */
    @Excel(name = "协助人id")
    private String extUserId;

    /** 协助人名称 */
    @Excel(name = "协助人名称")
    private String extNickName;

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

    /** 租户ID */
    @Excel(name = "租户ID")
    private Long tenantId;

    /** 进度 */
    @Excel(name = "进度")
    private BigDecimal schedule;

    /** 进度状态 */
    @Excel(name = "进度状态")
    private String scheduleStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishTime;

    private String forTrack;

    private Long forTrackId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sendUserId", getSendUserId())
            .append("sendNickName", getSendNickName())
            .append("receiveUserId", getReceiveUserId())
            .append("receiveNickName", getReceiveNickName())
            .append("title", getTitle())
            .append("content", getContent())
            .append("extUserId", getExtUserId())
            .append("extNickName", getExtNickName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("billType", getBillType())
            .append("billCode", getBillCode())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("tenantId", getTenantId())
            .append("schedule", getSchedule())
            .append("scheduleStatus", getScheduleStatus())
            .toString();
    }
}
