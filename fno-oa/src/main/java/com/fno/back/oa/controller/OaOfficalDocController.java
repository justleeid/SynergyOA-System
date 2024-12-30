package com.fno.back.oa.controller;

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
import com.fno.back.oa.domain.OaOfficalDoc;
import com.fno.back.oa.service.OaOfficalDocService;
import com.fno.common.utils.poi.ExcelUtil;
import com.fno.common.core.page.TableDataInfo;

/**
 * 公文发文Controller
 * 
 * @author fno
 * @date 2023-11-05
 */
@RestController
@RequestMapping("/oa/officalDoc")
public class OaOfficalDocController extends BaseController
{
    @Autowired
    private OaOfficalDocService oaOfficalDocService;

    /**
     * 查询公文发文列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaOfficalDoc oaOfficalDoc)
    {
        startPage();
        List<OaOfficalDoc> list = oaOfficalDocService.selectOaOfficalDocList(oaOfficalDoc);
        return getDataTable(list);
    }


    /**
     * 查询公文发文列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:list')")
    @GetMapping("/receiveList")
    public TableDataInfo receiveList(OaOfficalDoc oaOfficalDoc)
    {
        startPage();
        List<OaOfficalDoc> list = oaOfficalDocService.receiveList(oaOfficalDoc);
        return getDataTable(list);
    }

    /**
     * 导出公文发文列表
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:export')")
    @Log(title = "公文发文", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaOfficalDoc oaOfficalDoc)
    {
        List<OaOfficalDoc> list = oaOfficalDocService.selectOaOfficalDocList(oaOfficalDoc);
        ExcelUtil<OaOfficalDoc> util = new ExcelUtil<OaOfficalDoc>(OaOfficalDoc.class);
        util.exportExcel(response, list, "公文发文数据");
    }

    /**
     * 获取公文发文详细信息
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(oaOfficalDocService.selectOaOfficalDocById(id));
    }

    /**
     * 新增公文发文
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:add')")
    @Log(title = "公文发文", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OaOfficalDoc oaOfficalDoc) throws Exception {
        oaOfficalDocService.insertOaOfficalDoc(oaOfficalDoc);
        return success(oaOfficalDoc);
    }

    /**
     * 修改公文发文
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:edit')")
    @Log(title = "公文发文", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OaOfficalDoc oaOfficalDoc) throws Exception {
        oaOfficalDocService.updateOaOfficalDoc(oaOfficalDoc);
        return success(oaOfficalDoc);
    }

    /**
     * 删除公文发文
     */
    @PreAuthorize("@ss.hasPermi('oa:officalDoc:remove')")
    @Log(title = "公文发文", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(oaOfficalDocService.deleteOaOfficalDocByIds(ids));
    }


    /****
     * 签章（定稿）
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/sealDoc/{id}")
    public AjaxResult sealDoc(@PathVariable("id")Long id) throws Exception {
        oaOfficalDocService.sealDoc(id);
        return success();
    }
}
