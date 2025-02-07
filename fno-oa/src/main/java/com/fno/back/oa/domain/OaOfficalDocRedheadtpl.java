package com.fno.back.oa.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 套红模版对象 oa_offical_doc_redheadtpl
 *
 */
@Data
public class OaOfficalDocRedheadtpl extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 模版名称 */
    @Excel(name = "模版名称")
    private String name;

    /** 模版 */
    @Excel(name = "模版")
    private String tpl;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private String status;

    private String company;

//    是否透明.0：白色背景。1：透明背景
    private String transparent;

    private String img;

    private Long sealId;

    private String sealName;

    private String redTitle;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("tpl", getTpl())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
