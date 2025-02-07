package com.fno.back.workflow.domain;

import lombok.Data;

/***
 */
@Data
public class FlowProcessInstance {

    private String billType;

    private Long businessId;

    private String defKey;

}
