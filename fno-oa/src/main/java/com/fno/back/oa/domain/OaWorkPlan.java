package com.fno.back.oa.domain;

import lombok.Data;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 工作计划对象 oa_work_plan
 *
 */
@Data
public class OaWorkPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 计划人 */
    @Excel(name = "计划人")
    private Long planUserId;

    private String planUserNickname;

    /** 计划标题 */
    @Excel(name = "计划标题")
    private String title;

    /** 用户ID（单据 拥有者） */
    @Excel(name = "用户ID", readConverterExp = "单=据,拥=有者")
    private Long userId;

    /** 部门ID（单据拥有部门） */
    @Excel(name = "部门ID", readConverterExp = "单=据拥有部门")
    private Long deptId;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 单据编码 */
    @Excel(name = "单据编码")
    private String billCode;

    /** 申请流程 */
    @Excel(name = "申请流程")
    private String flowKey;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String flowInsId;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String status;

    /** 工作计划安排信息 */
    private List<OaWorkPlanItem> oaWorkPlanItemList;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planUserId", getPlanUserId())
            .append("title", getTitle())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("billType", getBillType())
            .append("billCode", getBillCode())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("oaWorkPlanItemList", getOaWorkPlanItemList())
            .toString();
    }
}
