package com.shrm.basic;

import org.junit.Test;

import java.util.*;

public class JavaTest {

    public List<Map<String, Object>> getTestListMap() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map11 = new HashMap<>();
        map11.put("key1", "value11");
        map11.put("key2", "value12");
        map11.put("key3", "https://11.com");

        Map<String, Object> map12 = new HashMap<>();
        map12.put("key1", "value11");
        map12.put("key2", "value12");
        map12.put("key3", "https://12.com");

        Map<String, Object> map21 = new HashMap<>();
        map21.put("key1", "value21");
        map21.put("key2", "value22");
        map21.put("key3", "https://21.com");

        Map<String, Object> map22 = new HashMap<>();
        map22.put("key1", "value21");
        map22.put("key2", "value22");
        map22.put("key3", "https://22.com");

        list.add(map11);
        list.add(map12);
        list.add(map21);
        list.add(map22);
        return list;
    }

    @Test
    public void fun01() {
        Map<String, Map<String, Object>> groupMap = new HashMap<>();
        List<Map<String, Object>> list = getTestListMap();
        for (Map<String, Object> map : list) {
            String key1 = (String) map.get("key1");
            if (groupMap.containsKey(key1)) {
                Map<String, Object> m = groupMap.get(key1);
                List<String> key3List = (List) m.get("key3");
                key3List.add((String) map.get("key3"));
            } else {
                Map<String, Object> m = new HashMap();
                m.putAll(map);
                List<String> l = new ArrayList<>();
                l.add((String) map.get("key3"));
                m.put("key3", l);
                groupMap.put(key1, m);
            }
        }

        List<Map<String, Object>> returnList = new ArrayList<>();
        Iterator<String> iterator = groupMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();  //获取key
            Map<String, Object> m = groupMap.get(key);
            returnList.add(m);
        }
        System.out.println(returnList);
    }
}
