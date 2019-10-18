package com.shrm.test.Collection;

import org.junit.Test;

import java.util.*;

public class CollectionSort {

    public List<Map<String, Object>> getDefaultList() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key1", "10");
        map1.put("key2", "abc");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key1", "30");
        map2.put("key2", "bcd");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("key1", "20");
        map3.put("key2", "cde");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map3);
        return list;
    }

    @Test
    public void test1() {
        List<Map<String, Object>> list = getDefaultList();
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return (((String) o1.get("key1")).trim()).compareTo(((String) o2.get("key1")).trim());
            }
        });
        for (Map<String, Object> map : list) {
            System.err.println((String) map.get("key1"));
        }
    }
}
