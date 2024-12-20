package com.lwj.springbootexample.util;

import cn.hutool.core.date.DatePattern;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DateUtil {

    private static final Object lockObj = new Object();


    public static Map<String,ThreadLocal<SimpleDateFormat>> sdfMap = new ConcurrentHashMap<>();

    public static SimpleDateFormat getDateFormat(String pattern){
        ThreadLocal<SimpleDateFormat> threadLocal = sdfMap.get(pattern);
        if(threadLocal == null){
            synchronized (lockObj){
                threadLocal = sdfMap.get(pattern);
                if(threadLocal == null){
                    threadLocal = new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }

    private static final String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getWeekOfDate(String dt) {
        SimpleDateFormat dateFormat = sdfMap.get("yyyy-MM-dd").get();
        try {
            return getWeekOfDate(dateFormat.parse(dt));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String today(){
        LocalDate date = LocalDate.now();
        return date.toString();
    }

    public static String format(Date date, String pattern){
        return getDateFormat(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern){
        Date date = null;
        SimpleDateFormat sdf = sdfMap.get(pattern).get();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    public static String formatDate(Date date){
        return getDateFormat("yyyy-MM-dd").format(date);
    }

    public static String formatDateTime(Date date){
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date parseDate(String dateStr){
        return parse(dateStr, "yyyy-MM-dd");
    }
    public static Date parseDateTime(String dateStr){
        return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date offset(Date date,int offset, int field){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, offset);
        return cal.getTime();
    }
}
