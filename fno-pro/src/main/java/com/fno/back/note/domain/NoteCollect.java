package com.fno.back.note.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 收藏夹对象 note_collect
 * 
 * @author fno
 * @date 2023-04-20
 */
@Data
public class NoteCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 笔记id */
    @Excel(name = "笔记id")
    private Long docId;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("docId", getDocId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
