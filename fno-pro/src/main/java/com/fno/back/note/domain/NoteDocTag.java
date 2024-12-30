package com.fno.back.note.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 笔记标签关系对象 note_doc_tag
 * 
 * @author fno
 * @date 2023-04-20
 */
@Data
public class NoteDocTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 标签id */
    @Excel(name = "标签id")
    private Long tagId;

    /** 文档id */
    @Excel(name = "文档id")
    private Long docId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("tagId", getTagId())
            .append("docId", getDocId())
            .toString();
    }
}
