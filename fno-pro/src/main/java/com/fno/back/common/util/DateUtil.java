package com.fno.back.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @PackageName com.fno.common.util
 * @ClassName DateUtil
 * @Description 日期处理工具类
 * @Author Shouli Wang
 * @Date 2023/9/7 16:15
 * @Version 1.0
 */
public class DateUtil {

    /**
     * 计算两个日期之间的天数
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @return
     */
    public static int calculateDateDifference(Date startDate, Date endDate) {
        if (startDate == null && endDate == null) {
            return 0;
        }

        if (startDate != null && endDate == null) {
            endDate = new Date();
        }

        if (startDate == null && endDate != null) {
            return 0;
        }

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        long millisecondsDifference = endCal.getTimeInMillis() - startCal.getTimeInMillis();
        long daysDifference = millisecondsDifference / (1000 * 60 * 60 * 24); // 转换为天数

        return (int) daysDifference;
    }

    public static Date getCurrentDate() {
        // 获取当前日期
        Date currentDate = new Date();

        // 创建日期格式化器
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // 将日期格式化为字符串
            String formattedDate = dateFormat.format(currentDate);

            // 将格式化后的字符串转换回Date类型
            Date formattedDateObj = dateFormat.parse(formattedDate);

            return formattedDateObj;
        } catch (ParseException e) {
            // 处理日期解析异常
            e.printStackTrace();
            return null; // 或者抛出异常，取决于您的需求
        }
    }
}

