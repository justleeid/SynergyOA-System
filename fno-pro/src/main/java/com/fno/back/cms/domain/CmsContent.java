package com.fno.back.cms.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 内容管理对象 cms_content
 * 
 * @author fno
 * @date 2023-04-29
 */
@Data
public class CmsContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 是否显示 */
    @Excel(name = "是否显示")
    private String visible;

    /** 封面 */
    @Excel(name = "封面")
    private String cover;

    private Long catalogId;

    private Long siteId;

    private Long tenantId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("visible", getVisible())
            .append("cover", getCover())
            .toString();
    }
}
