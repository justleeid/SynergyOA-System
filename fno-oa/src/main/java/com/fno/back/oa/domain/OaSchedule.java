package com.fno.back.oa.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 日程管理对象 oa_schedule
 *
 */
@Data
public class OaSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 日程内容 */
    @Excel(name = "日程内容")
    private String title;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date start;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date end;

    /** 地址 */
    @Excel(name = "地址")
    private String url;

    /** 是否可调整 */
    @Excel(name = "是否可调整")
    private String editable;

    /** 是否提醒 */
    @Excel(name = "是否提醒")
    private String ifAlert;

    /** 提醒方式 */
    @Excel(name = "提醒方式")
    private String alertType;

    private String className;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("title", getTitle())
            .append("start", getStart())
            .append("end", getEnd())
            .append("url", getUrl())
            .append("editable", getEditable())
            .append("ifAlert", getIfAlert())
            .append("alertType", getAlertType())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
