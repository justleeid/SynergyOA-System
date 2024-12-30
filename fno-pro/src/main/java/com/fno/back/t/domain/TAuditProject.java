package com.fno.back.t.domain;

import lombok.Data;
import java.math.BigDecimal;
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
 * 审核项目对象 t_audit_project
 * 
 * @author fno
 * @date 2024-02-27
 */
@Data
@ApiModel(value = "TAuditProject", description = "审核项目")
public class TAuditProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 项目编码 */
    @Excel(name = "项目编码")
    @ApiModelProperty("项目编码")
    private String code;

    /** 项目名称 */
    @Excel(name = "项目名称")
    @ApiModelProperty("项目名称")
    private String name;

    /** 项目（法人）单位 */
    @Excel(name = "项目", readConverterExp = "法=人")
    @ApiModelProperty("项目")
    private String legal;

    /** 建设规模及内容 */
    @Excel(name = "建设规模及内容")
    @ApiModelProperty("建设规模及内容")
    private String contenet;

    /** 项目投资（万元） */
    @Excel(name = "项目投资", readConverterExp = "万=元")
    @ApiModelProperty("项目投资")
    private BigDecimal amount;

    /** 年度 */
    @Excel(name = "年度")
    @ApiModelProperty("年度")
    private String year;

    /** 立项时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "立项时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("立项时间")
    private Date deployDate;

    /** 项目建议书批复 */
    @Excel(name = "项目建议书批复")
    @ApiModelProperty("项目建议书批复")
    private String auditFile;

    /** 可研报告批复 */
    @Excel(name = "可研报告批复")
    @ApiModelProperty("可研报告批复")
    private String reportFile;

    /** 初设报告批复 */
    @Excel(name = "初设报告批复")
    @ApiModelProperty("初设报告批复")
    private String initFile;

    /** 是否颁发施工许可证 */
    @Excel(name = "是否颁发施工许可证")
    @ApiModelProperty("是否颁发施工许可证")
    private String ifCanBuild;

    /** 是否开工建设 */
    @Excel(name = "是否开工建设")
    @ApiModelProperty("是否开工建设")
    private String ifStartBuild;

    /** 租户ID */
    @Excel(name = "租户ID")
    @ApiModelProperty("租户ID")
    private Long tenantId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("legal", getLegal())
            .append("contenet", getContenet())
            .append("amount", getAmount())
            .append("year", getYear())
            .append("deployDate", getDeployDate())
            .append("auditFile", getAuditFile())
            .append("reportFile", getReportFile())
            .append("initFile", getInitFile())
            .append("ifCanBuild", getIfCanBuild())
            .append("ifStartBuild", getIfStartBuild())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .toString();
    }
}
