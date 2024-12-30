package com.fno.back.pg.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 项目管理对象 pg_project
 * 
 * @author fno
 * @date 2023-04-15
 */
public class PgProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 责任人 */
    @Excel(name = "责任人")
    private String liablePerson;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 工作天数 */
    @Excel(name = "工作天数")
    private Long workDays;

    /** 项目描述信息 */
    @Excel(name = "项目描述信息")
    private String des;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String projectStatus;


    public void setId(Long id) 
    {
        this.id = id;
    }
    public Long getId() 
    {
        return id;
    }


    public void setName(String name) 
    {
        this.name = name;
    }
    public String getName() 
    {
        return name;
    }


    public void setLiablePerson(String liablePerson) 
    {
        this.liablePerson = liablePerson;
    }
    public String getLiablePerson() 
    {
        return liablePerson;
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


    public void setWorkDays(Long workDays) 
    {
        this.workDays = workDays;
    }
    public Long getWorkDays() 
    {
        return workDays;
    }


    public void setDes(String des) 
    {
        this.des = des;
    }
    public String getDes() 
    {
        return des;
    }


    public void setProjectStatus(String projectStatus) 
    {
        this.projectStatus = projectStatus;
    }
    public String getProjectStatus() 
    {
        return projectStatus;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("liablePerson", getLiablePerson())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("workDays", getWorkDays())
            .append("des", getDes())
            .append("projectStatus", getProjectStatus())
            .toString();
    }
}
