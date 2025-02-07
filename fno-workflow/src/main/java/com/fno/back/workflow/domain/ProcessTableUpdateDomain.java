package com.fno.back.workflow.domain;

import lombok.Data;

import java.util.Date;

/***
 * @des
 */
@Data
public class ProcessTableUpdateDomain {


    private String tableName;
    private String status;
    private String insId;
    private Long id;
    private String flowKey;
    private Date submitTime;
    private Date updateTime;
}
