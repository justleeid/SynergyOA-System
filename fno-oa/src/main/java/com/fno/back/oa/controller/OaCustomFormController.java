package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.oa.domain.OaCustomForm;
import com.fno.back.oa.service.OaCustomFormService;
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
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 自定义表单Controller
 * 
 * @author fno
 * @date 2022-07-05
 */
@RestController
@RequestMapping("/oa/customForm")
public class OaCustomFormController extends BaseController
{
    @Autowired
    private OaCustomFormService oaCustomFormService;

    /**
     * 查询自定义表单列表
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaCustomForm oaCustomForm)
    {
        startPage();
        List<OaCustomForm> list = oaCustomFormService.selectOaCustomFormList(oaCustomForm);
        return getDataTable(list);
    }

    /**
     * 查询自定义表单列表
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(OaCustomForm oaCustomForm)
    {
        List<OaCustomForm> list = oaCustomFormService.selectOaCustomFormList(oaCustomForm);
        return success("查询成功",list);
    }

    /**
     * 导出自定义表单列表
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:export')")
    @Log(title = "自定义表单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaCustomForm oaCustomForm)
    {
        List<OaCustomForm> list = oaCustomFormService.selectOaCustomFormList(oaCustomForm);
        ExcelUtil<OaCustomForm> util = new ExcelUtil<OaCustomForm>(OaCustomForm.class);
        util.exportExcel(response, list, "自定义表单数据");
    }

    /**
     * 获取自定义表单详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaCustomFormService.selectOaCustomFormById(id));
    }

    /**
     * 新增自定义表单
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:add')")
    @Log(title = "自定义表单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaCustomForm oaCustomForm)
    {
        return toAjax(oaCustomFormService.insertOaCustomForm(oaCustomForm));
    }

    /**
     * 修改自定义表单
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:edit')")
    @Log(title = "自定义表单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaCustomForm oaCustomForm)
    {
        return toAjax(oaCustomFormService.updateOaCustomForm(oaCustomForm));
    }

    /**
     * 删除自定义表单
     */
    @PreAuthorize("@ss.hasPermi('oa:customForm:remove')")
    @Log(title = "自定义表单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaCustomFormService.deleteOaCustomFormByIds(ids));
    }
}
