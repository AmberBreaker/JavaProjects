package com.shrm.mybatis.mapping;

/**
 * 从#{}中解析出来的参数信息，包括参数名称和类型
 */
public class ParameterMapping {

    private String name;

    private Class<?> type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ParameterMapping(String context) {
        this.name = context;
    }
}
