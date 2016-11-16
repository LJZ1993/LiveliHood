package com.example.administrator.livelihood.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14.
 */
public class DateUtil {
    private String mCurrentTime;
    private static Date date;

    public static DateUtil getInstance() {
        date = new Date();
        return new DateUtil();
    }

    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/ddhh:mm:ss");
        mCurrentTime = format.format(date);
        return mCurrentTime;
    }

    public String getWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        String[] week = new String[]{"星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String weekDay = week[i - 1];
        return weekDay;
    }
}
