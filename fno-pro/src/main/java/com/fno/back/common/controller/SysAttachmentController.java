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
import com.fno.back.common.domain.SysAttachment;
import com.fno.back.common.service.SysAttachmentService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件中心Controller
 * 
 * @author fno
 * @date 2023-08-05
 */
@RestController
@RequestMapping("/common/sysAttachment")
public class SysAttachmentController extends BaseController
{
    @Autowired
    private SysAttachmentService sysAttachmentService;


    /**
     * 查询附件中心列表
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysAttachment sysAttachment)
    {
        startPage();
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        return getDataTable(list);
    }


    /**
     * 计算附件数量
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:list')")
    @GetMapping("/countSysAttachement")
    public AjaxResult countSysAttachement(SysAttachment sysAttachment)
    {
        int count = sysAttachmentService.countSysAttachement(sysAttachment);
        return success(count);
    }

    /**
     * 导出附件中心列表
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:export')")
    @Log(title = "附件中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysAttachment sysAttachment)
    {
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        ExcelUtil<SysAttachment> util = new ExcelUtil<SysAttachment>(SysAttachment.class);
        util.exportExcel(response, list, "附件中心数据");
    }

    /**
     * 获取附件中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysAttachmentService.selectSysAttachmentById(id));
    }

    /**
     * 新增附件中心
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:add')")
    @Log(title = "附件中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.insertSysAttachment(sysAttachment));
    }

    /**
     * 修改附件中心
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:edit')")
    @Log(title = "附件中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysAttachment sysAttachment)
    {
        return toAjax(sysAttachmentService.updateSysAttachment(sysAttachment));
    }

    /**
     * 删除附件中心
     */
    @PreAuthorize("@ss.hasPermi('common:sysAttachment:remove')")
    @Log(title = "附件中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysAttachmentService.deleteSysAttachmentByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('common:sysAttachment:list')")
    @GetMapping("/formAttachmentlist")
    public AjaxResult formAttachmentlist(SysAttachment sysAttachment)
    {
        List<SysAttachment> list = sysAttachmentService.selectSysAttachmentList(sysAttachment);
        return success(list);
    }

    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file,String billType,String businessId) throws Exception
    {
        try
        {
            sysAttachmentService.upload(file,billType,Long.parseLong(businessId));
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
        return success();
    }
}
