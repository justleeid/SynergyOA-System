package com.fno.back.oa.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 工作计划安排对象 oa_work_plan_item
 * 
 * @author fno
 * @date 2023-08-13
 */
public class OaWorkPlanItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 计划id */
    @Excel(name = "计划id")
    private Long planId;

    /** 分组 */
    @Excel(name = "分组")
    private String groupName;

    /** 详细信息 */
    @Excel(name = "详细信息")
    private String detail;

    /** 负责人ID */
    @Excel(name = "负责人ID")
    private Long dutyUserId;

    /** 负责人名称 */
    @Excel(name = "负责人名称")
    private String dutyNickName;

    /** 协助人ID */
    @Excel(name = "协助人ID")
    private Long helpUserId;

    /** 协助人名称 */
    @Excel(name = "协助人名称")
    private String helpNickName;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 进度 */
    @Excel(name = "进度")
    private BigDecimal schedule;

    /** 产出物 */
    @Excel(name = "产出物")
    private String makeObject;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
    }
    public void setDutyUserId(Long dutyUserId) 
    {
        this.dutyUserId = dutyUserId;
    }

    public Long getDutyUserId() 
    {
        return dutyUserId;
    }
    public void setDutyNickName(String dutyNickName) 
    {
        this.dutyNickName = dutyNickName;
    }

    public String getDutyNickName() 
    {
        return dutyNickName;
    }
    public void setHelpUserId(Long helpUserId) 
    {
        this.helpUserId = helpUserId;
    }

    public Long getHelpUserId() 
    {
        return helpUserId;
    }
    public void setHelpNickName(String helpNickName) 
    {
        this.helpNickName = helpNickName;
    }

    public String getHelpNickName() 
    {
        return helpNickName;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setSchedule(BigDecimal schedule) 
    {
        this.schedule = schedule;
    }

    public BigDecimal getSchedule() 
    {
        return schedule;
    }
    public void setMakeObject(String makeObject) 
    {
        this.makeObject = makeObject;
    }

    public String getMakeObject() 
    {
        return makeObject;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planId", getPlanId())
            .append("groupName", getGroupName())
            .append("detail", getDetail())
            .append("remark", getRemark())
            .append("dutyUserId", getDutyUserId())
            .append("dutyNickName", getDutyNickName())
            .append("helpUserId", getHelpUserId())
            .append("helpNickName", getHelpNickName())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("schedule", getSchedule())
            .append("makeObject", getMakeObject())
            .append("status", getStatus())
            .toString();
    }
}
