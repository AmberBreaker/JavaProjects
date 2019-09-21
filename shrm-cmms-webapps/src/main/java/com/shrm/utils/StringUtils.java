package com.shrm.utils;

/**
 * 字符串工具
 */
public class StringUtils {

    /**字符串业务逻辑式判空*/
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

}
