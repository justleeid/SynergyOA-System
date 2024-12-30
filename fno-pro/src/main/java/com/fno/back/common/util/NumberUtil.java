package com.fno.back.common.util;

import java.math.BigDecimal;

/***
 * @des
 * @author Ly
 * @date 2023/10/12
 */

public class NumberUtil extends cn.hutool.core.util.NumberUtil {


    public static BigDecimal emptyToZero(BigDecimal d){
        if(d!=null){
            return d;
        }else{
            return BigDecimal.ZERO;
        }
    }
}
