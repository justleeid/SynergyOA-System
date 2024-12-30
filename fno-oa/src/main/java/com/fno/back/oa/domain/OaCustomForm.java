package com.fno.back.oa.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 自定义表单对象 oa_custom_form
 * 
 * @author fno
 * @date 2022-07-07
 */
@Data
public class OaCustomForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 表单名称 */
    @Excel(name = "表单名称")
    private String name;

    private Long tenantId;

    /** 表单分类 */
    @Excel(name = "表单分类")
    private String category;

    /** 表单规则 */
    @Excel(name = "表单规则")
    private String rules;

    /** 表单配置 */
    @Excel(name = "表单配置")
    private String options;

    /** 表单代码 */
    @Excel(name = "表单代码")
    private String formKey;


    private String flowKey;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("category", getCategory())
            .append("rules", getRules())
            .append("options", getOptions())
            .append("formKey", getFormKey())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
