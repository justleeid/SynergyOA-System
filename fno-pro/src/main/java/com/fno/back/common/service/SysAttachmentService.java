package com.fno.back.common.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fno.common.config.FnoConfig;
import com.fno.common.utils.DateUtils;
import com.fno.common.utils.SecurityUtils;
import com.fno.common.utils.file.FileUploadUtils;
import com.fno.common.utils.file.FileUtils;
import com.fno.framework.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fno.back.common.mapper.SysAttachmentMapper;
import com.fno.back.common.domain.SysAttachment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 附件中心Service业务层处理
 * 
 * @author fno
 * @date 2023-08-05
 */
@Service
public class SysAttachmentService
{
    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询附件中心
     * 
     * @param id 附件中心主键
     * @return 附件中心
     */
    public SysAttachment selectSysAttachmentById(Long id)
    {
        return sysAttachmentMapper.selectSysAttachmentById(id);
    }

    /**
     * 查询附件中心列表
     * 
     * @param sysAttachment 附件中心
     * @return 附件中心
     */
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.selectSysAttachmentList(sysAttachment);
    }


    /**
     * 计算附件数量
     */
    public int countSysAttachement(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.countSysAttachement(sysAttachment);
    }

    /**
     * 新增附件中心
     * 
     * @param sysAttachment 附件中心
     * @return 结果
     */
    public int insertSysAttachment(SysAttachment sysAttachment)
    {
        sysAttachment.setCreateTime(DateUtils.getNowDate());
        return sysAttachmentMapper.insertSysAttachment(sysAttachment);
    }

    /**
     * 修改附件中心
     * 
     * @param sysAttachment 附件中心
     * @return 结果
     */
    public int updateSysAttachment(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.updateSysAttachment(sysAttachment);
    }

    /**
     * 批量删除附件中心
     * 
     * @param ids 需要删除的附件中心主键
     * @return 结果
     */
    public int deleteSysAttachmentByIds(Long[] ids)
    {
        return sysAttachmentMapper.deleteSysAttachmentByIds(ids);
    }

    /**
     * 删除附件中心信息
     * 
     * @param id 附件中心主键
     * @return 结果
     */
    public int deleteSysAttachmentById(Long id)
    {
        return sysAttachmentMapper.deleteSysAttachmentById(id);
    }


    /****
     * 上传附件
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public Map upload(MultipartFile file,String billType,Long businessId) throws IOException {
        Map result = new HashMap();
        // 上传文件路径
        String filePath = FnoConfig.getAttachmentPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        String url = serverConfig.getUrl() + fileName;
        result.put("url", url);
        result.put("fileName", fileName);
        result.put("newFileName", FileUtils.getName(fileName));
        result.put("originalFilename", file.getOriginalFilename());

        SysAttachment attach = new SysAttachment();
        attach.setUrl(fileName);
        attach.setSize(file.getSize());
        attach.setBillType(billType);
        attach.setBusinessId(businessId);
        attach.setCreateTime(new Date());
        attach.setExt(url.substring(url.lastIndexOf(".")+1));
        attach.setCreateBy(SecurityUtils.getUserId());
        attach.setUserId(SecurityUtils.getUserId());
        attach.setDeptId(SecurityUtils.getDeptId());
        sysAttachmentMapper.insertSysAttachment(attach);

        return result;
    }
}
