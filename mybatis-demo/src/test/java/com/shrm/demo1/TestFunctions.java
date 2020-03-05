package com.shrm.demo1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestFunctions {

    /**
     * 引用对象
     */
    @Test
    public void test1() {

        Map<String, String> map = new HashMap<>();
        set(map);

        String str = "1";
        setStr(str);
        System.out.println(map.size());
    }
    private void setStr(String str) {
        str = "12334";
    }
    private void set(Map<String, String> map) {
        map.put("1", "a");
        map.put("2", "b");
    }

    @Test
    public void test2() {
        String methodName = "get" + getStr("bcmName");
        System.out.println(methodName);
    }
    private String getStr(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char ch = str.charAt(0);
        if (Character.isTitleCase(ch)) {
            return str;
        }
        return new StringBuffer().append(Character.toTitleCase(ch)).append(str.substring(1, str.length())).toString();
    }

    @Test
    public void tmpTest() {
        System.out.println(String.class == String.class);
    }

    @Test
    public void test3() {
        StringBuffer sb = new StringBuffer("abc");
        appendSb(sb);
        String substring = sb.substring(0, sb.length() - 1);
        System.out.println(substring.toString());
    }

    private void appendSb(StringBuffer sb) {
        sb.append("dec");
    }

    @Test
    public void test4() {
        String column = "adsAfGeFG";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < column.length(); i++) {
            char ch = column.charAt(i);
            if (Character.isUpperCase(ch)) {
                sb.append("_");
            }
            sb.append(Character.toUpperCase(ch));
        }
        System.out.println(sb.toString());
    }

    @Test
    public void test5() {
        System.out.println(Character.isUpperCase('_'));
        System.out.println(Character.toUpperCase('_'));
    }
}
