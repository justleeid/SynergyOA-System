package com.fno.back.common.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 附件中心对象 sys_attachment
 * 
 * @author fno
 * @date 2023-08-05
 */
@Data
public class SysAttachment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 单据id */
    @Excel(name = "单据id")
    private Long businessId;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String url;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long size;

    /** 扩展名 */
    @Excel(name = "扩展名")
    private String ext;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("businessId", getBusinessId())
            .append("billType", getBillType())
            .append("url", getUrl())
            .append("size", getSize())
            .append("ext", getExt())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .toString();
    }
}
