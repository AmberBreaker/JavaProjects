package com.shrm.test.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CollectionOperator {

    private static void doWrong(ArrayList<String> list) {
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        System.out.println(list);
        System.out.println("未按照需求删除");
    }

    private static void doRight(ArrayList<String> list) {
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        Iterator<String> iter = list.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        System.out.println(list);
        System.out.println("全部删除");
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        System.out.println("删除List集合中的所有元素，List中初始元素: a, b, c, d");
        System.out.println("错误方式");
        doWrong(list);
        System.out.println("正确方式");
        doRight(list);
    }
}
