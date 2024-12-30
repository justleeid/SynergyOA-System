package com.fno.back.oa.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 进度跟踪对象 oa_work_plan_item_track
 * 
 * @author fno
 * @date 2023-08-18
 */
@Data
public class OaWorkPlanItemTrack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 计划明细ID */
    @Excel(name = "计划明细ID")
    private Long itemId;

    /** 跟踪内容 */
    @Excel(name = "跟踪内容")
    private String track;

    /** 跟踪人 */
    @Excel(name = "跟踪人ID")
    private Long trackUserId;

    @Excel(name = "跟踪人")
    private String trackNickName ;

    private String avatar;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemId", getItemId())
            .append("track", getTrack())
            .append("trackUserId", getTrackUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
