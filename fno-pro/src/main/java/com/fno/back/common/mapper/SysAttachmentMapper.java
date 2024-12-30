package com.fno.back.common.mapper;

import java.util.List;
import com.fno.back.common.domain.SysAttachment;

/**
 * 附件中心Mapper接口
 * 
 * @author fno
 * @date 2023-08-05
 */
public interface SysAttachmentMapper 
{
    /**
     * 查询附件中心
     * 
     * @param id 附件中心主键
     * @return 附件中心
     */
    public SysAttachment selectSysAttachmentById(Long id);

    /**
     * 查询附件中心列表
     * 
     * @param sysAttachment 附件中心
     * @return 附件中心集合
     */
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment);

    /**
     * 计算附件数量
     */
    public int countSysAttachement(SysAttachment sysAttachment);

    /**
     * 新增附件中心
     * 
     * @param sysAttachment 附件中心
     * @return 结果
     */
    public int insertSysAttachment(SysAttachment sysAttachment);

    /**
     * 修改附件中心
     * 
     * @param sysAttachment 附件中心
     * @return 结果
     */
    public int updateSysAttachment(SysAttachment sysAttachment);

    /**
     * 删除附件中心
     * 
     * @param id 附件中心主键
     * @return 结果
     */
    public int deleteSysAttachmentById(Long id);

    /**
     * 批量删除附件中心
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysAttachmentByIds(Long[] ids);
}
