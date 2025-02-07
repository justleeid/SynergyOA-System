package com.fno.back.oa.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 会议室申请对象 oa_meetingroom_apply
 *
 */
@Data
public class OaMeetingroomApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 用户ID（单据 拥有者） */
    @Excel(name = "用户ID", readConverterExp = "单=据,拥=有者")
    private Long userId;

    /** 部门ID（单据拥有部门） */
    @Excel(name = "部门ID", readConverterExp = "单=据拥有部门")
    private Long deptId;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String status;

    /** 申请流程 */
    private String flowKey;

    /** 流程实例id */
    private String flowInsId;

    /** 申请标题 */
    private String applyTitle;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String billCode;

    private String billType;

    /** 使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 使用人数 */
    @Excel(name = "使用人数")
    private Long userCount;

    /** 会议题目 */
    @Excel(name = "会议题目")
    private String meetingTitle;

    /** 会议纪要 */
    private String meetingDes;

    private Long meetingroomId;

    private String meetingroomCode;

    private String meetingroomName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("applyTitle", getApplyTitle())
            .append("submitTime", getSubmitTime())
            .append("billcode", getBillCode())
            .append("remark", getRemark())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("userCount", getUserCount())
            .append("meetingTitle", getMeetingTitle())
            .append("meetingDes", getMeetingDes())
            .toString();
    }
}
