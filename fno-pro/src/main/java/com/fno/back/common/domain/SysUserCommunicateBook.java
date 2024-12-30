package com.fno.back.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 个人通讯录对象 oa_user_communicate_book
 * 
 * @author fno
 * @date 2023-07-31
 */
@Data
public class SysUserCommunicateBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    private Long tenantId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 头像地址 */
    @Excel(name = "头像地址")
    private String avatar;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 出生年月 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNum;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String companyAddress;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String address;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("avatar", getAvatar())
            .append("name", getName())
            .append("birthDate", getBirthDate())
            .append("idNum", getIdNum())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("remark", getRemark())
            .append("companyName", getCompanyName())
            .append("companyAddress", getCompanyAddress())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("address", getAddress())
            .toString();
    }
}
