package com.fno.back.oa.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 车票申请对象 oa_ticket_apply
 * 
 * @author fno
 * @date 2023-05-29
 */
@Data
public class OaTicketApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;
    /** 用户ID */
    private Long userId;

    /** 部门ID */
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

    /** 目的地 */
    @Excel(name = "目的地")
    private String destination;

    /** 出发地 */
    @Excel(name = "出发地")
    private String departure;

    /** 预计金额 */
    @Excel(name = "预计金额")
    private BigDecimal totalAmt;

    /** 启程时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "启程时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date goTime;

    /** 车次/架次 */
    @Excel(name = "车次/架次")
    private String carNum;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String applyRealname;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNum;



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
            .append("destination", getDestination())
            .append("departure", getDeparture())
            .append("totalAmt", getTotalAmt())
            .append("goTime", getGoTime())
            .append("carNum", getCarNum())
            .append("applyRealname", getApplyRealname())
            .append("idNum", getIdNum())
            .toString();
    }
}
