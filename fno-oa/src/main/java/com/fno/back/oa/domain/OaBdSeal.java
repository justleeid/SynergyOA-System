package com.fno.back.oa.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 公章管理对象 oa_bd_seal
 * 
 * @author fno
 * @date 2023-05-29
 */
@Data
public class OaBdSeal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    private Long tenantId;

    /** 公章名称 */
    @Excel(name = "公章名称")
    private String name;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    private String company;

    private String img;

    private String transparent;
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
