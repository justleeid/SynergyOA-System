package com.fno.back.workflow.domain;

import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ActReProcdef extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;

    @Excel(name = "流程名称")
    private String name;

    @Excel(name = "流程KEY")
    private String key;

    @Excel(name = "流程版本")
    private int version;

    @Excel(name = "所属分类")
    private String category;

    @Excel(name = "流程描述")
    private String description;

    private String deploymentId;

    @Excel(name = "部署时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deploymentTime;

    @Excel(name = "流程图")
    private String diagramResourceName;

    @Excel(name = "流程定义")
    private String resourceName;


    //激活状态
    private Long suspensionState;



    private Long rev;

    private String dgrmResourceName;

    private Long hasStartFormKey;

    private Long hasGraphicalNotation;



    private Long tenantId;

    private String engineVersion;

    private String derivedFrom;

    private String derivedFromRoot;

    private Long derivedVersion;

}
