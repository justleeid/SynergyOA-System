package com.fno.back.workflow.service.business;

import cn.hutool.extra.spring.SpringUtil;
import com.fno.back.common.constant.BillTypeConstants;
import com.fno.back.common.domain.SysBillType;
import com.fno.back.workflow.service.business.wms.StoreBillCancelCall;
import com.fno.back.workflow.service.business.wms.StoreBillFinishCall;
import com.fno.back.workflow.service.business.wms.StoreBillSubmitCall;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/***
 * @des 流程引擎调用业务服务
 * @author Ly
 * @date 2024/1/5
 */
@Service
public class FlowCallBusinessService {


    /****
     * 根据单据类型回调业务服务---【单据提交时候回调】
     * @param sysBillType
     */
    public void flowSubmitCall(SysBillType sysBillType,Long businessId) throws Exception{

        //如果是【入库单】和【出库单】，则提交时候，则执行，设置待出入库库存数量
        if(sysBillType.getBillType().equals(BillTypeConstants.STORE_IN) || sysBillType.getBillType().equals(BillTypeConstants.STORE_OUT) || sysBillType.getBillType().equals(BillTypeConstants.STORE_ALLOT)){
            Class<?> c = Class.forName(StoreBillSubmitCall.className);
            Method method = c.getMethod(StoreBillSubmitCall.methodName,String.class,Long.class);
            //获取首字母小写类名
            String simpleName = c.getSimpleName();
            String firstLowerName = simpleName.substring(0,1).toLowerCase() + simpleName.substring(1);
            Object object = SpringUtil.getBean(firstLowerName, c);
            method.invoke(object, sysBillType.getBillType(), businessId);
        }
    }


    /****
     * 根据单据类型回调业务服务---【单据撤销提交时候回调】
     * @param sysBillType
     */
    public void flowCancelCall(SysBillType sysBillType,Long businessId) throws Exception{

        //如果是【入库单】和【出库单】，则提交时候，则执行，设置待出入库库存数量
        if(sysBillType.getBillType().equals(BillTypeConstants.STORE_IN) || sysBillType.getBillType().equals(BillTypeConstants.STORE_OUT) || sysBillType.getBillType().equals(BillTypeConstants.STORE_ALLOT)){
            Class<?> c = Class.forName(StoreBillCancelCall.className);
            Method method = c.getMethod(StoreBillCancelCall.methodName,String.class,Long.class);
            //获取首字母小写类名
            String simpleName = c.getSimpleName();
            String firstLowerName = simpleName.substring(0,1).toLowerCase() + simpleName.substring(1);
            Object object = SpringUtil.getBean(firstLowerName, c);
            method.invoke(object, sysBillType.getBillType(), businessId);
        }
    }


    /****
     * 流程结束的时候，进行单据回调
     * @param sysBillType
     * @param businessId
     */
    public void flowFinishCall(SysBillType sysBillType, long businessId) throws Exception {
        //如果是【入库单】和【出库单】，则提交时候，则执行，设置待出入库库存数量
        if(sysBillType.getBillType().equals(BillTypeConstants.STORE_IN) || sysBillType.getBillType().equals(BillTypeConstants.STORE_OUT) || sysBillType.getBillType().equals(BillTypeConstants.STORE_ALLOT)){
            Class<?> c = Class.forName(StoreBillFinishCall.className);
            Method method = c.getMethod(StoreBillFinishCall.methodName,String.class,Long.class);
            //获取首字母小写类名
            String simpleName = c.getSimpleName();
            String firstLowerName = simpleName.substring(0,1).toLowerCase() + simpleName.substring(1);
            Object object = SpringUtil.getBean(firstLowerName, c);
            method.invoke(object, sysBillType.getBillType(), businessId);
        }
    }


}
