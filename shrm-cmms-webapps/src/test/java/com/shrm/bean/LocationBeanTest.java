package com.shrm.bean;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationBeanTest {

    @Test
    public void test() {
        List<Map<String, String>> defaultList = getDefaultList();
        for (Map<String, String> map : defaultList) {
            LocationBean bean = new LocationBean();
        }
    }


    private List<Map<String, String>> getDefaultList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("code", "1000");
        map.put("value", "beijing");
        map.put("parentCode", "0000");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2000");
        map.put("value", "anhui");
        map.put("parentCode", "0000");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "1100");
        map.put("value", "dongcheng");
        map.put("parentCode", "1000");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "1200");
        map.put("value", "fengtai");
        map.put("parentCode", "1000");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2100");
        map.put("value", "hefei");
        map.put("parentCode", "2000");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2200");
        map.put("value", "wuhu");
        map.put("parentCode", "2000");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2110");
        map.put("value", "shushan");
        map.put("parentCode", "2100");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2120");
        map.put("value", "luyang");
        map.put("parentCode", "2100");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2210");
        map.put("value", "jujiang");
        map.put("parentCode", "2200");
        list.add(map);

        map = new HashMap<>();
        map.put("code", "2220");
        map.put("value", "gaoxin");
        map.put("parentCode", "2200");
        list.add(map);

        return list;
    }
}
