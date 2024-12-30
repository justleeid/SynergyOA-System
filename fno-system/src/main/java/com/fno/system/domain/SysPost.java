package com.fno.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.annotation.Excel.ColumnType;
import com.fno.common.core.domain.BaseEntity;

/**
 * 职位表 sys_post
 * 
 * @author ry
 */
public class SysPost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 职位序号 */
    @Excel(name = "职位序号", cellType = ColumnType.NUMERIC)
    private Long postId;

    /** 职位编码 */
    @Excel(name = "职位编码")
    private String postCode;

    /** 职位名称 */
    @Excel(name = "职位名称")
    private String postName;

    /** 职位排序 */
    @Excel(name = "职位排序")
    private String postSort;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    /** 租户ID */
    private Long tenantId;

    /** 用户是否存在此职位标识 默认不存在 */
    private boolean flag = false;

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    @NotBlank(message = "职位编码不能为空")
    @Size(min = 0, max = 64, message = "职位编码长度不能超过64个字符")
    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    @NotBlank(message = "职位名称不能为空")
    @Size(min = 0, max = 50, message = "职位名称长度不能超过50个字符")
    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    @NotBlank(message = "显示顺序不能为空")
    public String getPostSort()
    {
        return postSort;
    }

    public void setPostSort(String postSort)
    {
        this.postSort = postSort;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postId", getPostId())
            .append("postCode", getPostCode())
            .append("postName", getPostName())
            .append("postSort", getPostSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
