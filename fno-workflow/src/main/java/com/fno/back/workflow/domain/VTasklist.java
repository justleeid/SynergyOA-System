package com.fno.back.workflow.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 我的待办对象 v_tasklist
 * 
 * @author fno
 * @date 2022-07-16
 */
@Data
public class VTasklist extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskid;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String insid;

    /** 任务编码 */
    @Excel(name = "任务编码")
    private String taskdefkey;

    /** 流程编码 */
    @Excel(name = "流程编码")
    private String defkey;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String defname;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskname;

    /** 审批人 */
    @Excel(name = "审批人")
    private String assignee;

    /** 候选人 */
    @Excel(name = "候选人")
    private String candidate;

    /** 流程定义ID */
    @Excel(name = "流程定义ID")
    private String defid;

    /** DELEGATIONID */
    @Excel(name = "DELEGATIONID")
    private String delegationid;

    /** 描述信息 */
    @Excel(name = "描述信息")
    private String description;

    /** 处理时间 */
    @Excel(name = "处理时间")
    private String dueTime;

    /** 审批信息 */
    @Excel(name = "审批信息")
    private String comment;

    /** 完成状态 */
    @Excel(name = "完成状态")
    private String completeStatus;

    /** 办理人名称 */
    @Excel(name = "办理人名称")
    private String assigneeName;

    /** 候选人名称 */
    @Excel(name = "候选人名称")
    private String candidateName;

    /** 单据信息 */
    @Excel(name = "单据信息")
    private String orderinfo;

    /** 单据id */
    @Excel(name = "单据id")
    private String businessId;

    /****
     * 单据类型
     */
    @Excel(name = "单据类型")
    private String billType;

    private String createtime;

    private Date duedate;


    /****
     * 流程变量
     */
    private String vars;


    /** 审批人名称 */
    @Excel(name = "审批人名称")
    private String nickName;

    private String userName;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskid", getTaskid())
            .append("insid", getInsid())
            .append("taskdefkey", getTaskdefkey())
            .append("defkey", getDefkey())
            .append("defname", getDefname())
            .append("taskname", getTaskname())
            .append("assignee", getAssignee())
            .append("candidate", getCandidate())
            .append("defid", getDefid())
            .append("delegationid", getDelegationid())
            .append("description", getDescription())
            .append("duetime", getDueTime())
            .append("comment",getComment())
            .append("nickName",getNickName())
            .append("completeStatus",getCompleteStatus())
            .append("vars",getVars())
            .toString();
    }
}
