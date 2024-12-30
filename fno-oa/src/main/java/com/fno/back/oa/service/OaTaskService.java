package com.fno.back.oa.service;

import com.fno.back.oa.domain.OaCustomFormApply;
import com.fno.back.oa.mapper.OaCustomFormApplyMapper;
import com.fno.back.workflow.domain.VTasklist;
import com.fno.back.workflow.mapper.VTasklistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @des
 * @author Ly
 * @date 2023/5/11
 */

@Service
public class OaTaskService {


    @Autowired
    private OaCustomFormApplyMapper oaCustomFormApplyMapper;
    @Autowired
    private VTasklistMapper vTasklistMapper;


    /****
     * 根据taskId，查询自定表单信息
     * @param taskId
     * @return
     */
    public OaCustomFormApply getCustomFormByTaskId(String taskId){
        VTasklist task = vTasklistMapper.selectVTasklistByTaskid(taskId);
        String insId = task.getInsid();
        OaCustomFormApply customFormApply = oaCustomFormApplyMapper.selectOaCustomFormApplyByInsId(insId);
        return customFormApply;
    }
}
