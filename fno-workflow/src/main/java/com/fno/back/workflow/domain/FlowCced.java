package com.fno.back.workflow.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 抄送我的对象 oa_flow_cced
 * 
 * @author fno
 * @date 2023-08-04
 */
@Data
public class FlowCced extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 流程实例id */
    @Excel(name = "流程实例id")
    private String flowInsId;

    /** 单据类型 */
    @Excel(name = "单据类型")
    private String billType;

    /** 流程定义编号 */
    @Excel(name = "流程定义编号")
    private String flowKey;

    /** 业务id */
    @Excel(name = "业务id")
    private Long businessId;

    /** 流程业务key */
    @Excel(name = "流程业务key")
    private String businessKey;

    /** 来源用户 */
    @Excel(name = "来源用户")
    private Long fromUserId;

    /** 抄送用户 */
    @Excel(name = "抄送用户")
    private Long toUserId;

    /** 单据标题 */
    @Excel(name = "单据标题")
    private String title;

    private String tableName;

    private String fromNickName;

    private String toNickName;

    private String isCustomForm;


    private String toDeptName;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flowInsId", getFlowInsId())
            .append("billType", getBillType())
            .append("flowKey", getFlowKey())
            .append("businessId", getBusinessId())
            .append("businessKey", getBusinessKey())
            .append("createTime", getCreateTime())
            .append("fromUserId", getFromUserId())
            .append("toUserId", getToUserId())
            .append("title", getTitle())
            .toString();
    }
}
