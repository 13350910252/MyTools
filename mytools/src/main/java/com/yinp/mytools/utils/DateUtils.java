package com.yinp.mytools.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.Nullable;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2019/1/26 0026.
 */

public class DateUtils {

    /**
     * 格式日期
     *
     * @param time
     * @return
     */
    public static String getFormatDate(long time) {
        try {
            String format = "yyyy-MM-dd HH:mm";
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat(format);
            Date date = new Date(time);
            return format1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 得到本机时间并转换成 年-月-日 时:分:秒 24小时制
     *
     * @return
     */
    public static String getFormatDate(Date date) {
        @SuppressLint("SimpleDateFormat") Format format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getFormatDate(String time) {
        try {
            String format = "yyyy-MM-dd HH:mm";
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat(format);
            Date parse = format1.parse(time);
            assert parse != null;
            return format1.format(parse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转化格林尼治时间
     */
    public static String getDateFormat(String oldDateStr) {
        try {
            //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX

            //yyyy-MM-dd'T'HH:mm:ss.SSSZ
            @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = null;
            date = df.parse(oldDateStr);
            @SuppressLint("SimpleDateFormat") DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            assert date != null;
            return df2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转化格林尼治时间
     */
    public static String getDateFormat1(String oldDateStr) {
        try {
            //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX

            //yyyy-MM-dd'T'HH:mm:ss.SSSZ
            @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = null;
            date = df.parse(oldDateStr);
            @SuppressLint("SimpleDateFormat") DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            assert date != null;
            return df2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getTime(String user_time) {
        String re_time = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {

            d = sdf.parse(user_time);
            assert d != null;
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);

        } catch (ParseException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return re_time;
    }

    /*
     * 指定时间加小时
     * */
    public static String addDate(String day, int x) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");//24小时制
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null) return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);//24小时制
        //cal.add(Calendar.HOUR, x);12小时制
        date = cal.getTime();
        System.out.println("front:" + date);
        cal = null;
        return format.format(date);
    }

    /**
     * 与当前时间比较早晚
     *
     * @param time 需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public static boolean compareNowTime(String time, String nowTime) {
        boolean isDayu = false;

        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parse = dateFormat.parse(time);
            //获取当前时间
            Date parse1 = dateFormat.parse(nowTime);

            assert parse1 != null;
            assert parse != null;
            long diff = parse1.getTime() - parse.getTime();
            isDayu = diff <= 0;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return isDayu;
    }

    /**
     * 计算时间差
     *
     * @param starTime 开始时间
     * @param endTime  结束时间
     *                 返回类型 ==1----天，时，分。 ==2----时
     * @return 返回时间差
     */
    public static String getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            assert parse1 != null;
            assert parse != null;
            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long m = diff / (60 * 1000);

            timeString = String.valueOf(m);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;

    }

    public static String getNetTime() {
        new Thread(() -> {
            String webUrl = "http://www.baidu.com";//中国科学院国家授时中心
            try {
                URL url = new URL(webUrl);
                URLConnection uc = url.openConnection();
                uc.setReadTimeout(5000);
                uc.setConnectTimeout(5000);
                uc.connect();
                long correctTime = uc.getDate();
                @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(correctTime);
                String format = formatter.format(calendar.getTime());
                Log.e("time", format);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return "";
    }

    public static String getSystemTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Log.e("sysTime", str);
        return str;
    }

    public static String getNowTimeHour() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("HH");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Log.e("sysTime", str);
        return str;
    }

    public static String getNowTimeMinutes() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Log.e("sysTime", str);
        return str;
    }

    public static final ThreadLocal<SimpleDateFormat> yyyy_MM_dd_hhmmss = new ThreadLocal<SimpleDateFormat>() {
        @Nullable
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        }
    };
    public static final ThreadLocal<SimpleDateFormat> yyyyMMdd = new ThreadLocal<SimpleDateFormat>() {
        @Nullable
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        }
    };

    /**
     * 将字符串转化为对应格式的日期
     *
     * @param format
     * @param dateStr
     * @return
     * @description
     * @date 2014-7-22
     * @author zuolong
     */
    public static Date toDate(ThreadLocal<SimpleDateFormat> format,
                              String dateStr) {
        try {
            return format.get().parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 以下是计算时间差值，返回值比如1秒前，5分钟前等等
     */
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    /**
     * 获取对应格式的日期字符串
     *
     * @param format
     * @param date
     * @return
     * @description
     * @date 2014-7-22
     * @author zuolong
     */
    public static String toDateString(ThreadLocal<SimpleDateFormat> format,
                                      Object date) {
        try {
            return format.get().format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间对应格式的日期字符串
     *
     * @param format 日期格式
     * @return 当前格式日期时间
     */
    public static String toDateString(ThreadLocal<SimpleDateFormat> format) {
        return format.get().format(System.currentTimeMillis());
    }

    /**
     * 1990-12-31T16:00:00.000+0000
     *
     * @param oldDate
     * @return
     */
    public static String dealDateFormat(String oldDate) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy年MM月dd号");
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return df2.format(date1);
    }

    /**
     * 获取指定年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static class scrollDate {
        public static final int DEFAULT_START_YEAR = 1900;
        public static List<String> hourList = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"));

        /**
         * 默认是1990到当前年
         *
         * @return
         */
        public static List<String> setYear() {
            return setYear(DEFAULT_START_YEAR);
        }

        public static List<String> setYear(int startYear) {
            Calendar calendar = Calendar.getInstance();
            int curYear = calendar.get(Calendar.YEAR);
            return setYear(startYear, curYear);
        }

        public static List<String> setYear(int startYear, int endYear) {
            if (startYear < DEFAULT_START_YEAR) {
                startYear = DEFAULT_START_YEAR;
            }
            List<String> list = new ArrayList<>();
            if (startYear > endYear) {
                for (int i = startYear; i >= endYear; i--) {
                    list.add(String.valueOf(i));
                }
            } else {
                for (int i = startYear; i <= endYear; i++) {
                    list.add(String.valueOf(i));
                }
            }
            return list;
        }

        /**
         * 默认1到12月
         *
         * @return
         */
        public static List<String> setMonth() {
            return setMonth(1, 12);
        }

        public static List<String> setMonth(int startMonth, int endMonth) {
            if (startMonth > endMonth || startMonth < 1 || endMonth > 12) {
                return null;
            }
            List<String> list = new ArrayList<>();
            for (int i = startMonth; i <= endMonth; i++) {
                list.add(String.valueOf(i));
            }
            return list;
        }

        /**
         * 日期会随着年和月的改变而改变
         *
         * @param year
         * @param month
         * @return
         */
        public static List<String> setDay(int year, int month) {
            List<Integer> _31Days = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
            List<String> list = new ArrayList<>();
            int days;
            if (_31Days.contains(month)) {
                days = 31;
            } else if (month == 2) {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//闰年
                    days = 29;
                } else {
                    days = 28;
                }
            } else {
                days = 30;
            }
            for (int i = 1; i <= days; i++) {
                list.add(String.valueOf(i));
            }
            return list;
        }

        /**
         * 给小于10的数加个0，保持两位数的格式
         *
         * @param value
         * @return
         */
        public static String add0(int value) {
            if (value < 10) {
                return "0" + value;
            }
            return String.valueOf(value);
        }

        public static List<String> setMinute() {
            List<String> minuteList = new ArrayList<>();
            for (int i = 0; i <= 59; i++) {
                minuteList.add(String.valueOf(i));
            }
            return minuteList;
        }
    }

    public static class curData {
        /**
         * 当前日期  年月日
         *
         * @return
         */
        public static String curDate() {
            Calendar calendar = Calendar.getInstance();
            return curStringYear(calendar) + "-" + add0(curStringMonth(calendar)) + "-" + add0(curStringDay(calendar));
        }

        public static String curStringYear(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return String.valueOf(calendar.get(Calendar.YEAR));
        }

        public static String curStringMonth(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return String.valueOf(calendar.get(Calendar.MONTH) + 1);
        }

        public static String curStringDay(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        }

        public static int curIntYear(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return calendar.get(Calendar.YEAR);
        }

        /**
         * 当前时间时分秒
         *
         * @return
         */
        public static String curTime() {
            Calendar calendar = Calendar.getInstance();
            return add0(curStringHour(calendar)) + ":" + add0(curStringMinute(calendar)) + ":" + add0(curStringSecond(calendar));
        }

        public static String curStringHour(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        }

        public static String curStringMinute(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return String.valueOf(calendar.get(Calendar.MINUTE));
        }

        public static String curStringSecond(Calendar calendar) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            return String.valueOf(calendar.get(Calendar.SECOND));
        }

    }

    /**
     * 给小于10的数加个0，保持两位数的格式
     *
     * @param value
     * @return
     */
    public static String add0(int value) {
        if (value < 10) {
            return "0" + value;
        }
        return String.valueOf(value);
    }

    public static String add0(String value) {
        int data;
        try {
            data = Integer.parseInt(value);
        } catch (Exception ex) {
            return null;
        }
        if (data < 10) {
            return "0" + value;
        }
        return String.valueOf(value);
    }
}