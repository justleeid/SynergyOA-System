package com.fno.back.t.domain;

import lombok.Data;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 项目对象 t_project
 * 
 * @author fno
 * @date 2024-02-27
 */
@Data
@ApiModel(value = "TProject", description = "项目")
public class TProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("${comment}")
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    @ApiModelProperty("项目名称")
    private String name;

    /** 项目建设单位 */
    @Excel(name = "项目建设单位")
    @ApiModelProperty("项目建设单位")
    private String buildOrg;

    /** 性质 */
    @Excel(name = "性质")
    @ApiModelProperty("性质")
    private String nature;

    /** 建设内容 */
    @Excel(name = "建设内容")
    @ApiModelProperty("建设内容")
    private String content;

    /** 总投资 */
    @Excel(name = "总投资")
    @ApiModelProperty("总投资")
    private BigDecimal invest;

    /** 累计完成投资 */
    @Excel(name = "累计完成投资")
    @ApiModelProperty("累计完成投资")
    private BigDecimal finishInvest;

    /** 完成进度 */
    @Excel(name = "完成进度")
    @ApiModelProperty("完成进度")
    private String schedule;

    /** 是否入统 */
    @Excel(name = "是否入统")
    @ApiModelProperty("是否入统")
    private String ifInSys;

    /** 项目进度 */
    @Excel(name = "项目进度")
    @ApiModelProperty("项目进度")
    private String projectSchedule;

    /** 现场照片 */
    @Excel(name = "现场照片")
    @ApiModelProperty("现场照片")
    private String images;

    /** 租户ID */
    @Excel(name = "租户ID")
    @ApiModelProperty("租户ID")
    private Long tenantId;

    private String year;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("buildOrg", getBuildOrg())
            .append("nature", getNature())
            .append("content", getContent())
            .append("invest", getInvest())
            .append("finishInvest", getFinishInvest())
            .append("schedule", getSchedule())
            .append("ifInSys", getIfInSys())
            .append("projectSchedule", getProjectSchedule())
            .append("images", getImages())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("tenantId", getTenantId())
            .toString();
    }
}
