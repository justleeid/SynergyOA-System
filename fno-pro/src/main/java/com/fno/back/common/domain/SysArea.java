package com.fno.back.common.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fno.common.annotation.Excel;
import com.fno.common.core.domain.BaseEntity;

/**
 * 省市字典对象 sys_area
 * 
 * @author fno
 * @date 2023-10-12
 */
@Data
public class SysArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区域ID */
    private String id;

    /** 上级区域ID */
    @Excel(name = "上级区域ID")
    private String parentId;

    /** 行政区域等级 1-省 2-市 3-区县 4-街道镇 */
    @Excel(name = "行政区域等级 1-省 2-市 3-区县 4-街道镇")
    private Integer level;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 完整名称 */
    @Excel(name = "完整名称")
    private String wholeName;

    /** 本区域经度 */
    @Excel(name = "本区域经度")
    private String lon;

    /** 本区域维度 */
    @Excel(name = "本区域维度")
    private String lat;

    /** 电话区号 */
    @Excel(name = "电话区号")
    private String cityCode;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String zipCode;

    /** 行政区划代码 */
    @Excel(name = "行政区划代码")
    private String areaCode;

    /** 名称全拼 */
    @Excel(name = "名称全拼")
    private String pinYin;

    /** 首字母简拼 */
    @Excel(name = "首字母简拼")
    private String simplePy;

    /** 区域名称拼音的第一个字母 */
    @Excel(name = "区域名称拼音的第一个字母")
    private String perPinYin;



    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("level", getLevel())
            .append("name", getName())
            .append("wholeName", getWholeName())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("cityCode", getCityCode())
            .append("zipCode", getZipCode())
            .append("areaCode", getAreaCode())
            .append("pinYin", getPinYin())
            .append("simplePy", getSimplePy())
            .append("perPinYin", getPerPinYin())
            .toString();
    }
}
