package com.fno.back.oa.domain;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 工作请示对象 oa_work_request
 * 
 * @author fno
 * @date 2024-03-04
 */
@Data
@ApiModel(value = "OaWorkRequest", description = "工作请示")
public class OaWorkRequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 单据类型 */
    @Excel(name = "单据类型")
    @ApiModelProperty("单据类型")
    private String billType;

    /** 单据编码 */
    @Excel(name = "单据编码")
    @ApiModelProperty("单据编码")
    private String billCode;

    /** 申请流程 */
    @Excel(name = "申请流程")
    @ApiModelProperty("申请流程")
    private String flowKey;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    @ApiModelProperty("流程实例id")
    private String flowInsId;

    /** 申请标题 */
    @Excel(name = "申请标题")
    @ApiModelProperty("申请标题")
    private String applyTitle;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("提交时间")
    private Date submitTime;

    /** 流程状态 */
    @Excel(name = "流程状态")
    @ApiModelProperty("流程状态")
    private String status;

    /** 租户ID */
    @Excel(name = "租户ID")
    @ApiModelProperty("租户ID")
    private Long tenantId;

    /** 请示内容 */
    @Excel(name = "请示内容")
    @ApiModelProperty("请示内容")
    private String content;

    /** 附件 */
    @Excel(name = "附件")
    @ApiModelProperty("附件")
    private String url;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("billType", getBillType())
            .append("billCode", getBillCode())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("applyTitle", getApplyTitle())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .append("content", getContent())
            .append("url", getUrl())
            .toString();
    }
}
