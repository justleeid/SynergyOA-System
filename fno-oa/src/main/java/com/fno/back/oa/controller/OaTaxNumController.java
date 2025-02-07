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
import com.fno.back.oa.domain.OaTaxNum;
import com.fno.back.oa.service.OaTaxNumService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 税号管理Controller
 *
 */
@RestController
@RequestMapping("/oa/taxNum")
public class OaTaxNumController extends BaseController
{
    @Autowired
    private OaTaxNumService oaTaxNumService;

    /**
     * 查询税号管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:taxNum:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaTaxNum oaTaxNum)
    {
        startPage();
        long tenantId = SecurityUtils.getTenantId();
        oaTaxNum.setTenantId(tenantId);
        List<OaTaxNum> list = oaTaxNumService.selectOaTaxNumList(oaTaxNum);
        return getDataTable(list);
    }

    /**
     * 导出税号管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:taxNum:export')")
    @Log(title = "税号管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaTaxNum oaTaxNum)
    {
        long tenantId = SecurityUtils.getTenantId();
        oaTaxNum.setTenantId(tenantId);
        List<OaTaxNum> list = oaTaxNumService.selectOaTaxNumList(oaTaxNum);
        ExcelUtil<OaTaxNum> util = new ExcelUtil<OaTaxNum>(OaTaxNum.class);
        util.exportExcel(response, list, "税号管理数据");
    }

    /**
     * 获取税号管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:taxNum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaTaxNumService.selectOaTaxNumById(id));
    }

    /**
     * 新增税号管理
     */
    @PreAuthorize("@ss.hasPermi('oa:taxNum:add')")
    @Log(title = "税号管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaTaxNum oaTaxNum)
    {
        return toAjax(oaTaxNumService.insertOaTaxNum(oaTaxNum));
    }

    /**
     * 修改税号管理
     */
    @PreAuthorize("@ss.hasPermi('oa:taxNum:edit')")
    @Log(title = "税号管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaTaxNum oaTaxNum)
    {
        return toAjax(oaTaxNumService.updateOaTaxNum(oaTaxNum));
    }

    /**
     * 删除税号管理
     */
    @PreAuthorize("@ss.hasPermi('oa:taxNum:remove')")
    @Log(title = "税号管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaTaxNumService.deleteOaTaxNumByIds(ids));
    }
}
