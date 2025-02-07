package com.fno.back.workflow.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fno.back.workflow.service.FlowCcedService;
import com.fno.common.utils.file.ImageUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @des
 */

@Aspect
@Component
public class SaveCcedAspect {

    private static final Logger log = LoggerFactory.getLogger(SaveCcedAspect.class);

    @Autowired
    private FlowCcedService flowCcedService;


    /***
     * 当执行插入新增，和，更新的时候
     */
    @Pointcut("execution(public * com.fno.back.*.service.*Service.insert*(..)) || execution(public * com.fno.back.*.service.*Service.update*(..))")
    public void pointcut() {

    }


    //使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            // 执行方法，连接点
            result = joinPoint.proceed(joinPoint.getArgs());
            JSONArray jsonArr = JSONArray.parseArray(JSON.toJSONString(joinPoint.getArgs()));
            JSONObject obj = jsonArr.getJSONObject(0);
            JSONArray ccedArr = obj.getJSONArray("ccedList");
            if(ccedArr!=null && !ccedArr.isEmpty()){
                //删除历史抄送数据
                flowCcedService.deleteCcedByBillTypeAndBusinessId(obj.getString("billType"),Long.parseLong(obj.getString("id")));
                //首次保存检查是否需要保存抄送人
                flowCcedService.formSaveBatchInsertCcedList(ccedArr,obj.getString("billType"),obj.getString("id"));
            }
        } catch (Throwable throwable) {
            //log.error(throwable.toString());
            //log.error(throwable.getStackTrace().toString());
            throw new RuntimeException(throwable.getMessage(),throwable);
        }
        return result;
    }
}
