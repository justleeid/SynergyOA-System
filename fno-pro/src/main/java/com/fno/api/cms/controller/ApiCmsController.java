package com.fno.api.cms.controller;

import com.fno.back.cms.domain.CmsCatalog;
import com.fno.back.cms.domain.CmsContent;
import com.fno.back.cms.service.CmsCatalogService;
import com.fno.back.cms.service.CmsContentService;
import com.fno.back.common.constant.CommonConstants;
import com.fno.common.annotation.Anonymous;
import com.fno.common.core.controller.BaseController;
import com.fno.common.core.domain.AjaxResult;
import com.fno.back.cms.domain.PTreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @des
 * @author Ly
 * @date 2023/4/30
 */
@RestController
@RequestMapping("/cms")
public class ApiCmsController extends BaseController {

    @Autowired
    private CmsCatalogService catalogService;
    @Autowired
    private CmsContentService contentService;


    /**
     * 获取
     *
     * @param
     * @return 结果
     */
    @Anonymous
    @GetMapping("/getCatalogByParentId")
    public AjaxResult getCatalogByParentId()
    {

        CmsCatalog query = new CmsCatalog();
        query.setVisible(CommonConstants.YES);
        List<CmsCatalog> catas = catalogService.selectCmsCatalogList(query);
        List<PTreeSelect> list = catalogService.buildTreeSelect(catas);

        for(PTreeSelect tree:list){
            for(PTreeSelect c:tree.getChildren()){

                CmsContent cq = new CmsContent();
                cq.setCatalogId(c.getId());
                List<CmsContent> contentList = contentService.selectCmsContentList(cq);

                c.setContentList(contentList);
            }
        }


        return success(list);
    }


    /*****
     * 获取内容
     * @param id
     * @return
     */
    @Anonymous
    @PostMapping("/getContentDetail")
    public AjaxResult getContentDetail(Long id){
        CmsContent content = contentService.selectCmsContentById(id);
        return success(content);
    }


}
