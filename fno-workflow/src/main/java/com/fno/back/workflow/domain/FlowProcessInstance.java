package com.fno.back.workflow.domain;

import lombok.Data;

/***
 * @des
 * @author Ly
 * @date 2023/6/2
 */
@Data
public class FlowProcessInstance {

    private String billType;

    private Long businessId;

    private String defKey;

}
