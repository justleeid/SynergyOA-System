package com.fno.back.oa.domain;

import lombok.Data;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 公文发文对象 oa_offical_doc
 * 
 * @author fno
 * @date 2023-11-05
 */
@Data
public class OaOfficalDoc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 套红id */
    @Excel(name = "套红id")
    private Long redHeadId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 公文内容 */
    @Excel(name = "公文内容")
    private String content;

    /** 密级 */
    @Excel(name = "密级")
    private String secretLevel;

    /** 发文部门 */
    @Excel(name = "部门")
    private String docDeptName;

    /** 年份 */
    @Excel(name = "年份")
    private Long year;

    /** 第几号文 */
    @Excel(name = "第几号文")
    private Long num;

    /** 企字 */
    @Excel(name = "企字")
    private String qizi;

    /** 起草状态.0：起草中。1：起草完（生成pdf） */
    @Excel(name = "起草状态.0：起草中。1：起草完", readConverterExp = "生=成pdf")
    private String draftStatus;

    /** 文件地址 */
    @Excel(name = "文件地址")
    private String url;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 单据编码 */
    @Excel(name = "单据编码")
    private String billCode;

    /** 申请流程 */
    @Excel(name = "申请流程")
    private String flowKey;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String flowInsId;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 流程状态 */
    @Excel(name = "流程状态")
    private String status;

    //保密期间
    private String secretPeriod;
    //紧急程度
    private String emergentLevel;
    //公开类别
    private String publicCategory;
    //公文编号
    private String docCode;




    /** 租户ID */
    @Excel(name = "租户ID")
    private Long tenantId;

    private String html;

    private String tplName;

    @JsonFormat(pattern = "yyyy年MM月dd日")
    @Excel(name = "发文日期", width = 30, dateFormat = "yyyy年MM月dd日")
    private Date sendDate;

    private String img;

    private String receiveDeptId;

    private String sendStatus;


    private List<Long> receiveDeptIdList;

    private List<Long> userParentDeptIdList;

    private String companyName;

    private String redTitle;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("redHeadId", getRedHeadId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("secretLevel", getSecretLevel())
            .append("year", getYear())
            .append("num", getNum())
            .append("qizi", getQizi())
            .append("draftStatus", getDraftStatus())
            .append("url", getUrl())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("billType", getBillType())
            .append("billCode", getBillCode())
            .append("flowKey", getFlowKey())
            .append("flowInsId", getFlowInsId())
            .append("submitTime", getSubmitTime())
            .append("status", getStatus())
            .append("tenantId", getTenantId())
            .toString();
    }
}
