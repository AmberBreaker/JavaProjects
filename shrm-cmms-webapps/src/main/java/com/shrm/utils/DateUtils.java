package com.shrm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    /**
     * TODO 需要整改
     * 如果未返回null，则成功(值为转换用时，单位:秒)
     * @param instr
     * @return
     */
    public static String returnSecond(String instr){
        String returnValue = null;
        if (instr != null) {
            String[] a = instr.split("\\.");
            String[] b = a[0].split(":");
            int returnNumber;
            if (b[0].length() != 0) {
                returnNumber = Integer.valueOf(b[0]) * 60 * 60 + Integer.valueOf(b[1]) * 60 + Integer.valueOf(b[2]);
                returnValue = String.valueOf(returnNumber);
            }else{
                returnValue = null;
            }
        }
        return returnValue;
    }

    public static String getWeekStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK, -1);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMinimum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        return sdf.format(cal.getTime());
    }

    public static String getWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK, -1);
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        System.out.println(getWeekStart(cal.getTime()));
        System.out.println(getWeekEnd(cal.getTime()));
        cal.add(Calendar.DAY_OF_WEEK, -1);
        System.out.println(getWeekStart(cal.getTime()));
        System.out.println(getWeekEnd(cal.getTime()));
    }
}
