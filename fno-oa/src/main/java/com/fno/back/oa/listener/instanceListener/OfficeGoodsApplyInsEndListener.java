package com.fno.back.oa.listener.instanceListener;

import cn.hutool.core.util.ObjectUtil;
import com.fno.back.oa.domain.OaOfficeGoods;
import com.fno.back.oa.domain.OaOfficeGoodsApply;
import com.fno.back.oa.domain.OaOfficeGoodsApplyItem;
import com.fno.back.oa.service.OaOfficeGoodsApplyService;
import com.fno.back.oa.service.OaOfficeGoodsService;
import com.fno.common.utils.spring.SpringUtils;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

import java.math.BigDecimal;
import java.util.List;

/***
 * @des
 * @author Ly
 * @date 2023/8/11
 */

public class OfficeGoodsApplyInsEndListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution execution) {
        String businessKey = execution.getProcessInstanceBusinessKey();
        String[] arr = businessKey.split(":");
        String billType = arr[0];
        String tablename = arr[1];
        String businessId = arr[2];
        Long id = Long.parseLong(businessId);


        OaOfficeGoodsApply apply = SpringUtils.getBean(OaOfficeGoodsApplyService.class).selectOaOfficeGoodsApplyById(id);
        List<OaOfficeGoodsApplyItem> list = apply.getOaOfficeGoodsApplyItemList();
        if(ObjectUtil.isNotEmpty(list)&&list.size()>0){
            for(OaOfficeGoodsApplyItem i : list){
                //如果领用数量填写了
                if(i.getGoodsCount()!=null && BigDecimal.ZERO.compareTo(i.getGoodsCount())<0){
                    OaOfficeGoods s = SpringUtils.getBean(OaOfficeGoodsService.class).selectOaOfficeGoodsById(i.getGoodsId());
                    //更新库存
                    if(s!=null&&s.getQuantity()!=null){
                        OaOfficeGoods u = new OaOfficeGoods();
                        u.setId(i.getGoodsId());
                        u.setQuantity(s.getQuantity().subtract(i.getGoodsCount()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        SpringUtils.getBean(OaOfficeGoodsService.class).updateOaOfficeGoods(u);
                    }
                }
            }
        }
    }
}
