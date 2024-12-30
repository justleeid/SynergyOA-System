package com.fno.back.cms.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.TreeEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 栏目管理对象 cms_catalog
 * 
 * @author fno
 * @date 2023-04-29
 */
@Data
public class CmsCatalog extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 栏目名称 */
    @Excel(name = "栏目名称")
    private String name;

    /** 地址 */
    @Excel(name = "地址")
    private String url;

    /** 打开类型 */
    @Excel(name = "打开类型")
    private String openType;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 封面 */
    @Excel(name = "封面")
    private String cover;

    /** 是否显示 */
    @Excel(name = "是否显示")
    private String visible;

    private Long parentId;

    private Long tenantId;

    private Long siteId;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("url", getUrl())
            .append("openType", getOpenType())
            .append("type", getType())
            .append("parentId", getParentId())
            .append("cover", getCover())
            .append("visible", getVisible())
            .toString();
    }
}
