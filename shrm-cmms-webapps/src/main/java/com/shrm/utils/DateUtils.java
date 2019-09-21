package com.shrm.utils;

public class DateUtils {

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
            int returnNumber = 0;
            if (instr != null && b[0].length() != 0) {
                returnNumber = Integer.valueOf(b[0]) * 60 * 60 + Integer.valueOf(b[1]) * 60 + Integer.valueOf(b[2]);
                returnValue = String.valueOf(returnNumber);
            }else{
                returnValue = null;
            }
        }
        return returnValue;
    }
}
