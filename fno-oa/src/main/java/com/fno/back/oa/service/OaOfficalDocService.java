package com.fno.back.oa.service;

import cn.hutool.core.date.DateUtil;
import com.fno.back.common.constant.BillTypeConstants;
import com.fno.back.common.service.SerialService;
import com.fno.back.common.util.OfficeUtil;
import com.fno.back.oa.domain.OaOfficalDoc;
import com.fno.back.oa.domain.OaOfficalDocRedheadtpl;
import com.fno.back.oa.mapper.OaOfficalDocMapper;
import com.fno.back.oa.mapper.OaOfficalDocRedheadtplMapper;
import com.fno.common.config.FnoConfig;
import com.fno.common.constant.Constants;
import com.fno.common.core.domain.entity.SysDept;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.StringUtils;
import com.fno.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公文发文Service业务层处理
 *
 */
@Service
public class OaOfficalDocService
{
    @Autowired
    private OaOfficalDocService oaOfficalDocService;
    @Autowired
    private OaOfficalDocMapper oaOfficalDocMapper;
    @Autowired
    private OaOfficalDocRedheadtplMapper oaOfficalDocRedheadtplMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SerialService serialService;

    /**
     * 查询公文发文
     * 
     * @param id 公文发文主键
     * @return 公文发文
     */
    public OaOfficalDoc selectOaOfficalDocById(Long id)
    {
        OaOfficalDoc doc = oaOfficalDocMapper.selectOaOfficalDocById(id);
        if(BillTypeConstants.OA_OFFICAL_DOC.equals(doc.getBillType())){
            OaOfficalDocRedheadtpl tpl = oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplById(doc.getRedHeadId());
            doc.setCompanyName(tpl.getCompany());
            doc.setRedTitle(tpl.getRedTitle());
        }
        return doc;
    }

    /**
     * 查询公文发文列表
     * 
     * @param oaOfficalDoc 公文发文
     * @return 公文发文
     */
    public List<OaOfficalDoc> selectOaOfficalDocList(OaOfficalDoc oaOfficalDoc)
    {
        oaOfficalDoc.setTenantId(SecurityUtils.getTenantId());
        return oaOfficalDocMapper.selectOaOfficalDocList(oaOfficalDoc);
    }




    /**
     * 查询公文发文列表
     *
     * @param oaOfficalDoc 收文
     * @return 收文
     */
    public List<OaOfficalDoc> receiveList(OaOfficalDoc oaOfficalDoc)
    {
        oaOfficalDoc.setTenantId(SecurityUtils.getTenantId());
        //当前用户的所属部门
        SysDept dept = sysDeptMapper.selectDeptById(SecurityUtils.getDeptId());
        List<Long> userParentDeptIdList = new ArrayList<>();
        if(dept!=null){
            String[] pList = dept.getAncestors().split(",");
            for(String idStr : pList){
                userParentDeptIdList.add(Long.parseLong(idStr));
            }
        }
        oaOfficalDoc.setUserParentDeptIdList(userParentDeptIdList);
        return oaOfficalDocMapper.receiveList(oaOfficalDoc);
    }




    /**
     * 新增公文发文
     * 
     * @param oaOfficalDoc 公文发文
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertOaOfficalDoc(OaOfficalDoc oaOfficalDoc) throws Exception {
        //如果是公文起草，则需要生成模版
        if(BillTypeConstants.OA_OFFICAL_DOC.equals(oaOfficalDoc.getBillType())){
            OaOfficalDocRedheadtpl tpl = oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplById(oaOfficalDoc.getRedHeadId());
            String html = buildHtml(oaOfficalDoc,tpl);
            oaOfficalDoc.setHtml(html);
        }
        oaOfficalDoc.setTenantId(SecurityUtils.getTenantId());
        oaOfficalDoc.setCreateTime(new Date());
        oaOfficalDoc.setCreateBy(SecurityUtils.getUserId());
        //单据编号
        String billCode = serialService.generateBillCodeByBillType(oaOfficalDoc.getBillType());
        oaOfficalDoc.setBillCode(billCode);
        oaOfficalDocMapper.insertOaOfficalDoc(oaOfficalDoc);
        //如果是公文起草，生成公文文件
        if(BillTypeConstants.OA_OFFICAL_DOC.equals(oaOfficalDoc.getBillType())) {
            oaOfficalDocService.buildFile(oaOfficalDoc.getId());
        }
    }


    /**
     * 修改公文发文
     * 
     * @param oaOfficalDoc 公文发文
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateOaOfficalDoc(OaOfficalDoc oaOfficalDoc) throws Exception {
        //如果是公文起草，生成公文文件
        if(BillTypeConstants.OA_OFFICAL_DOC.equals(oaOfficalDoc.getBillType())) {
            if(oaOfficalDoc.getRedHeadId()!=null){
                OaOfficalDocRedheadtpl tpl = oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplById(oaOfficalDoc.getRedHeadId());
                String html = buildHtml(oaOfficalDoc,tpl);
                oaOfficalDoc.setHtml(html);
            }
        }
        oaOfficalDoc.setUpdateTime(new Date());
        oaOfficalDoc.setUpdateBy(SecurityUtils.getUserId());
        int i =  oaOfficalDocMapper.updateOaOfficalDoc(oaOfficalDoc);
        //如果是公文起草，生成公文文件
        if(BillTypeConstants.OA_OFFICAL_DOC.equals(oaOfficalDoc.getBillType())) {
            oaOfficalDocService.buildFile(oaOfficalDoc.getId());
        }
        return 1;
    }




    private String buildHtml(OaOfficalDoc doc,OaOfficalDocRedheadtpl tpl){
        String html = "";
        html = html + "<html>" +
                "<body>" +
                    "<div style='padding:40px;'>" +
                        "<div style='text-align: center;padding-top:20px'>" +
                            "<span style='color:#FF0000; font-size: 40px;'>" +
                                "<strong>"+tpl.getRedTitle()+"</strong>" +
                            "</span>" +
                        "</div>" +
                        "<div style='text-align: center;padding:25px 25px 3px 25px'>" +
                            "<span style='color: #222222; font-size: 16px;'>"+doc.getQizi()+"（"+doc.getYear()+"）第【"+doc.getNum()+"】号</span>" +
                        "</div>" +
                        "<table style='border-bottom:2px solid #FF0000;width:100%;'>" +
                            "<tr style='width:100%'>" +
                                "<td style='100%'>&nbsp;</td>" +
                            "</tr>" +
                        "</table>" +
                        "<div style='text-align: center;padding: 25px 25px 0px 25px;'>" +
                            "<span style='color: #222222; font-size: 18px;font-weight:900;'>"+doc.getTitle()+"</span>" +
                        "</div>" +
                        "<br />" +

                        doc.getContent()
                                .replace("<p","<div style='line-height:36px;font-size:20px;text-indent:40px;margin-top:-10px;'")
                                .replace("</p>","</div>")+
                        "<div style='position: relative;padding-top:50px;'>" +
                            "<div style='text-align: right;font-size:20px;padding-right:50px;line-height:36px;'>"+tpl.getCompany()+"</div>" +
                            "<div style='text-align: right;font-size:20px;padding-right:50px;line-height:36px;'>"+ DateUtil.format(doc.getSendDate(),"yyyy年MM月dd日") +"</div>" +
                            "<div id='seal'></div>"+
                        "</div>" +
                    "</div>" +
                "</body>" +
                "</html>";

        return html;
    }

    /**
     * 批量删除公文发文
     * 
     * @param ids 需要删除的公文发文主键
     * @return 结果
     */
    public int deleteOaOfficalDocByIds(Long[] ids)
    {
        return oaOfficalDocMapper.deleteOaOfficalDocByIds(ids);
    }

    /**
     * 删除公文发文信息
     * 
     * @param id 公文发文主键
     * @return 结果
     */
    public int deleteOaOfficalDocById(Long id)
    {
        return oaOfficalDocMapper.deleteOaOfficalDocById(id);
    }




    /*****
     * 生成文件
     */
    public void buildFile(Long id) throws Exception {
        OaOfficalDoc doc = oaOfficalDocMapper.selectOaOfficalDocById(id);
        OaOfficalDocRedheadtpl tpl = oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplById(doc.getRedHeadId());
        if(StringUtils.isBlank(tpl.getImg())){
            throw new RuntimeException("公章中没有上传图片！");
        }
        if(StringUtils.isBlank(tpl.getCompany())){
            throw new RuntimeException("公章中没有填写公司名称！");
        }
        String content = doc.getHtml();
        String path = FnoConfig.getProfile()+"/oa/officalDoc/pdf/"+doc.getYear()+"/"+doc.getNum()+"/";
        File folder = new File(path);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String rPath = "/oa/officalDoc/pdf/"+doc.getYear()+"/"+doc.getNum()+"/"+doc.getTitle()+".pdf";
        String realPath = FnoConfig.getProfile()+rPath;
        //生成pdf文件
        OfficeUtil.createdPdfByItextHtml(content,realPath);
        if("1".equals(doc.getDraftStatus())){//已经签章
            oaOfficalDocService.sealDoc(doc.getId());
        }else{
            doc.setUrl(Constants.RESOURCE_PREFIX+rPath);
            doc.setUpdateTime(new Date());
            doc.setUpdateBy(SecurityUtils.getUserId());
            oaOfficalDocMapper.updateOaOfficalDoc(doc);
        }
    }


    /*****
     * 签章
     * @param id
     * @throws Exception
     */
    public void sealDoc(Long id ) throws Exception {
        OaOfficalDoc doc = oaOfficalDocMapper.selectOaOfficalDocById(id);
        OaOfficalDocRedheadtpl tpl = oaOfficalDocRedheadtplMapper.selectOaOfficalDocRedheadtplById(doc.getRedHeadId());
        //盖章
        String rPath = "/oa/officalDoc/pdf/"+doc.getYear()+"/"+doc.getNum()+"/"+doc.getTitle()+".pdf";
        String realPath = FnoConfig.getProfile()+rPath;
        String sealOutPath = "/oa/officalDoc/pdf/"+doc.getYear()+"/"+doc.getNum()+"/"+doc.getTitle()+"_seal.pdf";
        String sealOutReadPath = FnoConfig.getProfile()+"/oa/officalDoc/pdf/"+doc.getYear()+"/"+doc.getNum()+"/"+doc.getTitle()+"_seal.pdf";
        String sealImgPath = FnoConfig.getProfile() + StringUtils.substringAfter(tpl.getImg(), Constants.RESOURCE_PREFIX);
        OfficeUtil.addSignSeal(realPath,sealOutReadPath,sealImgPath,tpl.getCompany(),tpl.getTransparent());
        doc.setUrl(Constants.RESOURCE_PREFIX+sealOutPath);
        doc.setDraftStatus("1");
        doc.setUpdateTime(new Date());
        doc.setUpdateBy(SecurityUtils.getUserId());
        oaOfficalDocMapper.updateOaOfficalDoc(doc);
    }


}
