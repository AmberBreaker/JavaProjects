package com.shrm.mybatis.utils;

public class StringUtil {

    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            return str;
        }
        return new StringBuilder().append(Character.toTitleCase(firstChar))
                .append(str.substring(1, str.length())).toString();
    }
}
