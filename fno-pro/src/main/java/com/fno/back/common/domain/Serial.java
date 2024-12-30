package com.fno.back.common.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 编号规则对象 pl_serial
 * 
 * @author fno
 * @date 2022-10-29
 */
@Data
public class Serial extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    private String type;

    private String name;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 序号 */
    @Excel(name = "序号")
    private Long serialNum;

    /** 租户id */
    @Excel(name = "租户id")
    private Long tenantId;

    /** 编号前缀 */
    @Excel(name = "编号前缀")
    private String prefix;

    /** 是否包含日期 */
    @Excel(name = "是否包含日期")
    private String ifHaveDate;

    /** 长度 */
    @Excel(name = "长度")
    private Long codeLength;

    /** 最新的日期 **/
    private String codeDate;

    private String date;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("billType", getBillType())
            .append("serialNum", getSerialNum())
            .append("tenantId", getTenantId())
            .append("prefix", getPrefix())
            .append("ifHaveDate", getIfHaveDate())
            .toString();
    }
}
