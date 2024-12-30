package com.fno.back.cam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 视频监控提醒对象 cam_message_notice
 * 
 * @author fno
 * @date 2023-04-08
 */
public class CamMessageNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 提醒信息内容 */
    @Excel(name = "提醒信息内容")
    private String message;

    /** 语音提醒 */
    @Excel(name = "语音提醒")
    private String voiceFile;


    public void setId(Long id) 
    {
        this.id = id;
    }
    public Long getId() 
    {
        return id;
    }


    public void setTitle(String title) 
    {
        this.title = title;
    }
    public String getTitle() 
    {
        return title;
    }


    public void setType(String type) 
    {
        this.type = type;
    }
    public String getType() 
    {
        return type;
    }


    public void setMessage(String message) 
    {
        this.message = message;
    }
    public String getMessage() 
    {
        return message;
    }


    public void setVoiceFile(String voiceFile) 
    {
        this.voiceFile = voiceFile;
    }
    public String getVoiceFile() 
    {
        return voiceFile;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("type", getType())
            .append("message", getMessage())
            .append("voiceFile", getVoiceFile())
            .toString();
    }
}
