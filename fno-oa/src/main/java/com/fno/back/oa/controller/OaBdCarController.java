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
import com.fno.back.oa.domain.OaBdCar;
import com.fno.back.oa.service.OaBdCarService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 车辆管理Controller
 *
 */
@RestController
@RequestMapping("/oa/car")
public class OaBdCarController extends BaseController
{
    @Autowired
    private OaBdCarService oaBdCarService;

    /**
     * 查询车辆管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:car:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaBdCar oaBdCar)
    {
        startPage();
        List<OaBdCar> list = oaBdCarService.selectOaBdCarList(oaBdCar);
        return getDataTable(list);
    }

    /***
     * 查询所有车辆
     * @param oaBdCar
     * @return
     */
    @GetMapping("/listAll")
    public AjaxResult listAll(OaBdCar oaBdCar)
    {
        oaBdCar.setStatus(CommonConstants.ABLE);
        List<OaBdCar> list = oaBdCarService.selectOaBdCarList(oaBdCar);
        return success(list);
    }

    /**
     * 导出车辆管理列表
     */
    @PreAuthorize("@ss.hasPermi('oa:car:export')")
    @Log(title = "车辆管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaBdCar oaBdCar)
    {
        List<OaBdCar> list = oaBdCarService.selectOaBdCarList(oaBdCar);
        ExcelUtil<OaBdCar> util = new ExcelUtil<OaBdCar>(OaBdCar.class);
        util.exportExcel(response, list, "车辆管理数据");
    }

    /**
     * 获取车辆管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:car:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaBdCarService.selectOaBdCarById(id));
    }

    /**
     * 新增车辆管理
     */
    @PreAuthorize("@ss.hasPermi('oa:car:add')")
    @Log(title = "车辆管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaBdCar oaBdCar)
    {
        return toAjax(oaBdCarService.insertOaBdCar(oaBdCar));
    }

    /**
     * 修改车辆管理
     */
    @PreAuthorize("@ss.hasPermi('oa:car:edit')")
    @Log(title = "车辆管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaBdCar oaBdCar)
    {
        return toAjax(oaBdCarService.updateOaBdCar(oaBdCar));
    }

    /**
     * 删除车辆管理
     */
    @PreAuthorize("@ss.hasPermi('oa:car:remove')")
    @Log(title = "车辆管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaBdCarService.deleteOaBdCarByIds(ids));
    }
}
