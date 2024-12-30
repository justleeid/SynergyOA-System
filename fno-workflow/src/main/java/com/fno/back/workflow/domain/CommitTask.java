package com.fno.back.workflow.domain;

import lombok.Data;

/***
 * @des
 * @author Ly
 * @date 2023/6/1
 */
@Data
public class CommitTask {
    //任务id
    private String taskId;

    //流程实例id
    private String insId;

    //批注信息
    private String comment;

    //单据类型
    private String billType;

    //处理方式。0：办理。1：转办。2：委托
    private String operateType;

    //被委托人id
    private Long delegateUserId;

    //转交人id
    private Long transUserId;

    //是否抄送。是否抄送
    private String ifCced;

    //抄送人
    private String ccedListStr;

}
