package com.fno.back.common.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.fno.back.common.domain.Serial;
import com.fno.back.common.service.SerialService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 编号规则Controller
 * 
 * @author fno
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/common/serial")
public class SerialController extends BaseController
{
    @Autowired
    private SerialService serialService;

    /**
     * 查询编号规则列表
     */
    @PreAuthorize("@ss.hasPermi('common:serial:list')")
    @GetMapping("/list")
    public TableDataInfo list(Serial serial)
    {
        startPage();
        List<Serial> list = serialService.selectSerialList(serial);
        return getDataTable(list);
    }

    /**
     * 导出编号规则列表
     */
    @PreAuthorize("@ss.hasPermi('common:serial:export')")
    @Log(title = "编号规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Serial serial)
    {
        List<Serial> list = serialService.selectSerialList(serial);
        ExcelUtil<Serial> util = new ExcelUtil<Serial>(Serial.class);
        util.exportExcel(response, list, "编号规则数据");
    }

    /**
     * 获取编号规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('common:serial:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(serialService.selectSerialById(id));
    }

    /**
     * 新增编号规则
     */
    @PreAuthorize("@ss.hasPermi('common:serial:add')")
    @Log(title = "编号规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Serial serial)
    {
        return toAjax(serialService.insertSerial(serial));
    }

    /**
     * 修改编号规则
     */
    @PreAuthorize("@ss.hasPermi('common:serial:edit')")
    @Log(title = "编号规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Serial serial)
    {
        return toAjax(serialService.updateSerial(serial));
    }

    /**
     * 删除编号规则
     */
    @PreAuthorize("@ss.hasPermi('common:serial:remove')")
    @Log(title = "编号规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serialService.deleteSerialByIds(ids));
    }


    /****
     *
     * @param key
     * @return
     */
    @GetMapping("getSerialByKey/{key}")
    public AjaxResult getSerialByKey(@PathVariable("key") String key){
        return AjaxResult.success(serialService.getSerialByKey(key));
    }



}
