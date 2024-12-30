package com.fno.back.workflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 历史任务对象 act_hi_taskinst
 * 
 * @author fno
 * @date 2023-05-14
 */
@Data
public class ActHiTaskinst extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 版本 */
    @Excel(name = "版本")
    private Long rev;

    /** 流程定义ID */
    @Excel(name = "流程定义ID")
    private String procDefId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskDefId;

    /** 任务KEY */
    @Excel(name = "任务KEY")
    private String taskDefKey;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String procInstId;

    /** 执行ID */
    @Excel(name = "执行ID")
    private String executionId;

    private String scopeId;

    private String subScopeId;

    private String scopeType;

    private String scopeDefinitionId;

    private String propagatedStageInstId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String name;

    /** 父任务ID */
    @Excel(name = "父任务ID")
    private String parentTaskId;

    /** 描述信息 */
    @Excel(name = "描述信息")
    private String description;

    /** 拥有人 */
    @Excel(name = "拥有人")
    private String owner;

    /** 办理人 */
    @Excel(name = "办理人")
    private String assignee;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 认领时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "认领时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date claimTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 花费时间 */
    @Excel(name = "花费时间")
    private Long duration;

    private String deleteReason;

    private Long priority;

    private Date dueDate;

    private String formKey;

    private String category;

    private Long tenantId;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedTime;


    private String defName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rev", getRev())
            .append("procDefId", getProcDefId())
            .append("taskDefId", getTaskDefId())
            .append("taskDefKey", getTaskDefKey())
            .append("procInstId", getProcInstId())
            .append("executionId", getExecutionId())
            .append("scopeId", getScopeId())
            .append("subScopeId", getSubScopeId())
            .append("scopeType", getScopeType())
            .append("scopeDefinitionId", getScopeDefinitionId())
            .append("propagatedStageInstId", getPropagatedStageInstId())
            .append("name", getName())
            .append("parentTaskId", getParentTaskId())
            .append("description", getDescription())
            .append("owner", getOwner())
            .append("assignee", getAssignee())
            .append("startTime", getStartTime())
            .append("claimTime", getClaimTime())
            .append("endTime", getEndTime())
            .append("duration", getDuration())
            .append("deleteReason", getDeleteReason())
            .append("priority", getPriority())
            .append("dueDate", getDueDate())
            .append("formKey", getFormKey())
            .append("category", getCategory())
            .append("tenantId", getTenantId())
            .append("lastUpdatedTime", getLastUpdatedTime())
            .toString();
    }
}
