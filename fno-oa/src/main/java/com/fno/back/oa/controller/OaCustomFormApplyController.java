package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.enums.BusinessType;
import com.fno.back.oa.domain.OaCustomFormApply;
import com.fno.back.oa.service.OaCustomFormApplyService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 自定义表单申请Controller
 * 
 * @author fno
 * @date 2022-07-09
 */
@RestController
@RequestMapping("/oa/myApply")
public class OaCustomFormApplyController extends BaseController
{
    @Autowired
    private OaCustomFormApplyService oaCustomFormApplyService;

    /**
     * 查询自定义表单申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaCustomFormApply oaCustomFormApply)
    {
        startPage();
        List<OaCustomFormApply> list = oaCustomFormApplyService.selectOaCustomFormApplyList(oaCustomFormApply);
        return getDataTable(list);
    }

    /**
     * 导出自定义表单申请列表
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:export')")
    @Log(title = "自定义表单申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaCustomFormApply oaCustomFormApply)
    {
        List<OaCustomFormApply> list = oaCustomFormApplyService.selectOaCustomFormApplyList(oaCustomFormApply);
        ExcelUtil<OaCustomFormApply> util = new ExcelUtil<OaCustomFormApply>(OaCustomFormApply.class);
        util.exportExcel(response, list, "自定义表单申请数据");
    }

    /**
     * 获取自定义表单申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaCustomFormApplyService.selectOaCustomFormApplyById(id));
    }

    /**
     * 新增自定义表单申请
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:add')")
    @Log(title = "自定义表单申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaCustomFormApply oaCustomFormApply)
    {
        oaCustomFormApplyService.insertOaCustomFormApply(oaCustomFormApply,getLoginUser());
        return success(oaCustomFormApply);
    }

    /**
     * 修改自定义表单申请
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:edit')")
    @Log(title = "自定义表单申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaCustomFormApply oaCustomFormApply)
    {
        oaCustomFormApplyService.updateOaCustomFormApply(oaCustomFormApply, getLoginUser());
        return success(oaCustomFormApply);
    }

    /**
     * 删除自定义表单申请
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:remove')")
    @Log(title = "自定义表单申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaCustomFormApplyService.deleteOaCustomFormApplyByIds(ids));
    }


    /****
     * 撤销申请
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:remove')")
    @Log(title = "撤销申请", businessType = BusinessType.DELETE)
    @PostMapping("/cancelApplyInsId/{id}")
    public AjaxResult cancelApplyInsId(@PathVariable("id") Long id){
        oaCustomFormApplyService.cancleFlow(id);
        return success();
    }


    /****
     * 提交申请
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('oa:myApply:edit')")
    @Log(title = "提交申请", businessType = BusinessType.SUBMIT)
    @PostMapping("/submitApply/{id}")
    public AjaxResult submitApply(@PathVariable("id") Long id){
        oaCustomFormApplyService.submitApply(id);
        return success();
    }
}
