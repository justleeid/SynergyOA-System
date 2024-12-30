package com.fno.back.note.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.TreeEntity;

/**
 * 文件夹对象 note_folder
 * 
 * @author fno
 * @date 2023-04-20
 */
@Data
public class NoteFolder extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文件夹名称 */
    @Excel(name = "文件夹名称")
    private String name;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String ifDel;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("ifDel", getIfDel())
            .toString();
    }
}
