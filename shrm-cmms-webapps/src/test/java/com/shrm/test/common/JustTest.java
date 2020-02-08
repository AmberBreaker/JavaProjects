package com.shrm.test.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class JustTest {

    String[] arr = new String[]{"1", "2", "3"};

    @Test
    public void test1() {
        List<String> list = Arrays.asList(arr);
    }

    @Test
    public void test2() {
//        ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));
        ArrayList<String> list = new ArrayList<>(arr.length);
        list.addAll(Arrays.asList(arr));
    }

    @Test
    public void test3() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
//        for (int i = 0; i < list.size(); i++) {
//            list.remove(i);
//        }
//        System.out.println(list.size());
        for (String str : list) {
            if ("a".equals(str)) {
                list.remove(str);
            }
        }
    }

    @Test
    public void test4() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            if ("a".equals(str)) {
                iter.remove();
            }
        }
        System.out.println(list);
    }
}
