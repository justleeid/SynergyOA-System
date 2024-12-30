package com.fno.back.oa.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 任务跟踪对象 oa_send_task_track
 * 
 * @author fno
 * @date 2023-08-20
 */
@Data
public class OaSendTaskTrack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long taskId;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String track;

    /** 反馈人 */
    @Excel(name = "反馈人")
    private Long userId;

    private String trackNickName;

    private String avatar;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("track", getTrack())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
