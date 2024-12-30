package com.fno.back.common.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 站内信对象 sys_message
 * 
 * @author fno
 * @date 2023-07-31
 */
@Data
public class SysMessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 发件人 */
    @Excel(name = "发件人")
    private Long sendUserId;

    /** 收件人 */
    @Excel(name = "收件人")
    private Long receiveUserId;

    /** 发件人标题 */
    @Excel(name = "发件人标题")
    private String title;

    /** 发送内容 */
    @Excel(name = "发送内容")
    private String content;

    /** 发送时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sendTime;

    /** 是否阅读 */
    @Excel(name = "是否阅读")
    private String ifRead;

    private Long tenantId;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sendUserId", getSendUserId())
            .append("receiveUserId", getReceiveUserId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("sendTime", getSendTime())
            .append("ifRead", getIfRead())
            .toString();
    }
}
