package com.fno.back.common.mapper;

import java.util.List;
import com.fno.back.common.domain.SysMessage;
import org.apache.ibatis.annotations.Param;

/**
 * 站内信Mapper接口
 * 
 * @author fno
 * @date 2023-07-31
 */
public interface SysMessageMapper 
{


    /**
     * 查询站内信
     * 
     * @param id 站内信主键
     * @return 站内信
     */
    public SysMessage selectSysMessageById(Long id);

    /**
     * 查询站内信列表
     * 
     * @param sysMessage 站内信
     * @return 站内信集合
     */
    public List<SysMessage> selectSysMessageList(SysMessage sysMessage);

    /**
     * 新增站内信
     * 
     * @param sysMessage 站内信
     * @return 结果
     */
    public int insertSysMessage(SysMessage sysMessage);

    /**
     * 修改站内信
     * 
     * @param sysMessage 站内信
     * @return 结果
     */
    public int updateSysMessage(SysMessage sysMessage);

    /**
     * 删除站内信
     * 
     * @param id 站内信主键
     * @return 结果
     */
    public int deleteSysMessageById(Long id);

    /**
     * 批量删除站内信
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysMessageByIds(Long[] ids);

    public void allMessageRead(@Param("userId") Long userId);
}
