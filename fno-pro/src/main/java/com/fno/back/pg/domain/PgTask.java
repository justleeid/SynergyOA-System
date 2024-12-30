package com.fno.back.pg.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 任务迭代对象 pg_task
 * 
 * @author fno
 * @date 2023-04-15
 */
@Data
public class PgTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    /** 模块名称 */
    @Excel(name = "模块名称")
    private String modelName;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 优先级 */
    @Excel(name = "优先级")
    private String priority;

    /** 摘要 */
    @Excel(name = "摘要")
    private String summary;

    /** 任务详情 */
    @Excel(name = "任务详情")
    private String des;

    /** 责任人 */

    private Long dutyPerson;

    @Excel(name = "责任人")
    private String dutyPersonName;

    /** 协助人 */
    @Excel(name = "协助人")
    private String assistants;

    /** 计划开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 计划结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 工作进度 */
    @Excel(name = "工作进度")
    private Long progress;

    @Excel(name = "项目名称")
    private String projectName;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("modelName", getModelName())
            .append("taskName", getTaskName())
            .append("priority", getPriority())
            .append("summary", getSummary())
            .append("des", getDes())
            .append("dutyPerson", getDutyPerson())
            .append("assistants", getAssistants())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("progress", getProgress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
