package com.fno.back.workflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流程实例对象 act_hi_procinst
 *
 */
@Data
public class ActHiProcinst extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    /**  */
    private Long rev;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String procInstId;

    /** 业务信息 */
    @Excel(name = "业务信息")
    private String businessKey;

    /** 流程定义ID */
    @Excel(name = "流程定义ID")
    private String procDefId;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 耗时 */
    @Excel(name = "耗时")
    private Long duration;

    /** 起始人 */
    @Excel(name = "起始人")
    private String startUserId;

    /** 起始节点ID */
    @Excel(name = "起始节点ID")
    private String startActId;

    /** 结束节点ID */
    @Excel(name = "结束节点ID")
    private String endActId;

    /** 父流程实例ID */
    @Excel(name = "父流程实例ID")
    private String superProcessInstanceId;

    /** 删除原因 */
    @Excel(name = "删除原因")
    private String deleteReason;

    /** $column.columnComment */
    private Long tenantId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    private String callbackId;

    private String callbackType;

    private String referenceId;

    private String referenceType;



    private String defKey;

    private String defVersion;

    private String defName  ;

    private String orderinfo;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rev", getRev())
            .append("procInstId", getProcInstId())
            .append("businessKey", getBusinessKey())
            .append("procDefId", getProcDefId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("duration", getDuration())
            .append("startUserId", getStartUserId())
            .append("startActId", getStartActId())
            .append("endActId", getEndActId())
            .append("superProcessInstanceId", getSuperProcessInstanceId())
            .append("deleteReason", getDeleteReason())
            .append("tenantId", getTenantId())
            .append("name", getName())
            .append("callbackId", getCallbackId())
            .append("callbackType", getCallbackType())
            .append("referenceId", getReferenceId())
            .append("referenceType", getReferenceType())
            .toString();
    }
}
