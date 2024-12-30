package com.fno.back.oa.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 自定义表单申请对象 oa_custom_form_apply
 * 
 * @author fno
 * @date 2022-07-09
 */
@Data
public class OaCustomFormApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private Long id;

    private Long tenantId;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String name;

    /** 表单分类 */
    @Excel(name = "表单分类")
    private String category;

    /** 表单规则 */
    @Excel(name = "表单规则")
    private String rules;

    /** 表单配置 */
    @Excel(name = "表单配置")
    private String options;

    /** 表单数据 */
    @Excel(name = "表单数据")
    private String formData;

    /** 表单代码 */
    @Excel(name = "表单代码")
    private String formKey;

    /** 申请流程 */
    @Excel(name = "申请流程")
    private String flowKey;

    /** 流程名称 */
    @Excel(name = "流程名称")
    private String flowName;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String flowInsId;

    /** 申请标题 */
    @Excel(name = "申请标题")
    private String applyTitle;

    /** 状态 */
    @Excel(name = "状态", readConverterExp = "0=未提交,1=已提交,2=已完成")
    private String status;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    private String billType;


    private Long formId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("category", getCategory())
            .append("rules", getRules())
            .append("options", getOptions())
            .append("formData", getFormData())
            .append("formKey", getFormKey())
            .append("flowKey", getFlowKey())
            .append("flowName", getFlowName())
            .append("flowInsId", getFlowInsId())
            .append("applyTitle", getApplyTitle())
            .append("submitTime", getSubmitTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .toString();
    }
}
