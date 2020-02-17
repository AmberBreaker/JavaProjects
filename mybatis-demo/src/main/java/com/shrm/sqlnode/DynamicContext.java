package com.shrm.sqlnode;

import java.util.HashMap;

/**
 * 动态上下，就是封装的入参信息，即解析过程中的SQL信息
 */
public class DynamicContext {

    private StringBuffer sb = new StringBuffer();

    private HashMap<String, Object> binding = new HashMap<>();

    public String getSql() {
        return sb.toString();
    }

    public void appendSql(String sqlText) {
        this.sb.append(sqlText + " ");
    }

    public HashMap<String, Object> getBinding() {
        return binding;
    }

    public void addBinding(String name, Object param) {
        this.binding.put(name, param);
    }
}
