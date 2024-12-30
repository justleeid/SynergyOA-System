package com.fno.back.note.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 笔记对象 note_doc
 * 
 * @author fno
 * @date 2023-04-20
 */
@Data
public class NoteDoc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String ifDel;


    private Long folderId;


    private String ifCollect;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("ifDel", getIfDel())
            .toString();
    }
}
