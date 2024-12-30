package com.fno.back.common.controller;

import com.fno.back.common.domain.SysUserCommunicateBook;
import com.fno.back.common.service.SysCommunicateBookService;
import com.fno.common.annotation.Log;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.common.core.domain.entity.SysUser;
import com.fno.common.core.page.TableDataInfo;
import com.fno.common.enums.BusinessType;
import com.fno.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 个人通讯录Controller
 * 
 * @author fno
 * @date 2023-07-31
 */
@RestController
@RequestMapping("/oa/communicateBook")
public class SysUserCommunicateBookController extends BaseController
{
    @Autowired
    private SysCommunicateBookService sysCommunicateBookService;

    /**
     * 查询个人通讯录列表
     */
    @PreAuthorize("@ss.hasPermi('oa:communicateBook:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUserCommunicateBook sysUserCommunicateBook)
    {
        startPage();
        List<SysUserCommunicateBook> list = sysCommunicateBookService.selectOaUserCommunicateBookList(sysUserCommunicateBook);
        return getDataTable(list);
    }

    /****
     * 查询所有的通讯录信息
     * @param sysUserCommunicateBook
     * @return
     */
    @GetMapping("/listHomeBook")
    public TableDataInfo listHomeBook(SysUserCommunicateBook sysUserCommunicateBook)
    {
        startPage();
        List<SysUserCommunicateBook> list = sysCommunicateBookService.selectOaUserCommunicateBookList(sysUserCommunicateBook);
        return getDataTable(list);
    }

    /****
     * 查询所有的通讯录信息
     * @param sysUserCommunicateBook
     * @return
     */
    @GetMapping("/listAppBook")
    public AjaxResult listAppBook(SysUserCommunicateBook sysUserCommunicateBook)
    {
        List<SysUserCommunicateBook> list = sysCommunicateBookService.selectOaUserCommunicateBookList(sysUserCommunicateBook);
        return success(list);
    }

    /**
     * 导出个人通讯录列表
     */
    @PreAuthorize("@ss.hasPermi('oa:communicateBook:export')")
    @Log(title = "个人通讯录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysUserCommunicateBook sysUserCommunicateBook)
    {
        List<SysUserCommunicateBook> list = sysCommunicateBookService.selectOaUserCommunicateBookList(sysUserCommunicateBook);
        ExcelUtil<SysUserCommunicateBook> util = new ExcelUtil<SysUserCommunicateBook>(SysUserCommunicateBook.class);
        util.exportExcel(response, list, "个人通讯录数据");
    }

    /**
     * 获取个人通讯录详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:communicateBook:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCommunicateBookService.selectOaUserCommunicateBookById(id));
    }

    /**
     * 新增个人通讯录
     */
    @PreAuthorize("@ss.hasPermi('oa:communicateBook:add')")
    @Log(title = "个人通讯录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysUserCommunicateBook sysUserCommunicateBook)
    {
        return toAjax(sysCommunicateBookService.insertOaUserCommunicateBook(sysUserCommunicateBook));
    }

    /**
     * 修改个人通讯录
     */
    @PreAuthorize("@ss.hasPermi('oa:communicateBook:edit')")
    @Log(title = "个人通讯录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserCommunicateBook sysUserCommunicateBook)
    {
        return toAjax(sysCommunicateBookService.updateOaUserCommunicateBook(sysUserCommunicateBook));
    }

    /**
     * 删除个人通讯录
     */
    @PreAuthorize("@ss.hasPermi('oa:communicateBook:remove')")
    @Log(title = "个人通讯录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCommunicateBookService.deleteOaUserCommunicateBookByIds(ids));
    }



    /*****
     * 查询通讯录
     * @return
     */
    @GetMapping("/querySysCommunicateBook")
    public TableDataInfo querySysCommunicateBook(SysUser sysUser){
        startPage();
        List<Map> result =  sysCommunicateBookService.querySysCommunicateBook(sysUser);
        return getDataTable(result);
    }
}
