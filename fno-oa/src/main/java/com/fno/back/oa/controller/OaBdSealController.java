package com.fno.back.oa.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.fno.back.common.constant.CommonConstants;
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
import com.fno.back.oa.domain.OaBdSeal;
import com.fno.back.oa.service.OaBdSealService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 公章管理Controller
 * 
 * @author fno
 * @date 2023-05-29
 */
@RestController
@RequestMapping("/oa/seal")
public class OaBdSealController extends BaseController
{
    @Autowired
    private OaBdSealService oaBdSealService;

    /**
     * 查询公章管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:seal:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaBdSeal oaBdSeal)
    {
        startPage();
        List<OaBdSeal> list = oaBdSealService.selectOaBdSealList(oaBdSeal);
        return getDataTable(list);
    }

    @GetMapping("/listAll")
    public AjaxResult listAll(OaBdSeal oaBdSeal)
    {
        oaBdSeal.setStatus(CommonConstants.ABLE);
        List<OaBdSeal> list = oaBdSealService.selectOaBdSealList(oaBdSeal);
        return success(list);
    }

    /**
     * 导出公章管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:seal:export')")
    @Log(title = "公章管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaBdSeal oaBdSeal)
    {
        List<OaBdSeal> list = oaBdSealService.selectOaBdSealList(oaBdSeal);
        ExcelUtil<OaBdSeal> util = new ExcelUtil<OaBdSeal>(OaBdSeal.class);
        util.exportExcel(response, list, "公章管理数据");
    }

    /**
     * 获取公章管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:seal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaBdSealService.selectOaBdSealById(id));
    }

    /**
     * 新增公章管理
     */
    @PreAuthorize("@ss.hasPermi('oa:seal:add')")
    @Log(title = "公章管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaBdSeal oaBdSeal)
    {
        return toAjax(oaBdSealService.insertOaBdSeal(oaBdSeal));
    }

    /**
     * 修改公章管理
     */
    @PreAuthorize("@ss.hasPermi('oa:seal:edit')")
    @Log(title = "公章管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaBdSeal oaBdSeal)
    {
        return toAjax(oaBdSealService.updateOaBdSeal(oaBdSeal));
    }

    /**
     * 删除公章管理
     */
    @PreAuthorize("@ss.hasPermi('oa:seal:remove')")
    @Log(title = "公章管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaBdSealService.deleteOaBdSealByIds(ids));
    }
}
