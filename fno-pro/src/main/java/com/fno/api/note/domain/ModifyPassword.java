package com.fno.api.note.domain;

import lombok.Data;

/***
 * @des
 * @author Ly
 * @date 2023/5/2
 */
@Data
public class ModifyPassword {

    private String oldPassword;

    private String newPassword;


}
