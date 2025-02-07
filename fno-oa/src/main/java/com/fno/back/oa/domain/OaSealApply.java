package com.fno.back.oa.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 公章使用对象 oa_seal_apply
 *
 */
@Data
public class OaSealApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 用户ID（单据 拥有者） */
    private Long userId;

    /** 部门ID（单据拥有部门） */
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

    /** 公章 */
    @Excel(name = "公章")
    private String sealType;

    /** 使用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    /** 归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date backTime;

    /** 使用描述 */
    @Excel(name = "使用描述")
    private String useInfo;

    private String sealName;

    private Long sealId;


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
            .append("sealType", getSealType())
            .append("useTime", getUseTime())
            .append("backTime", getBackTime())
            .append("useInfo", getUseInfo())
            .toString();
    }
}
