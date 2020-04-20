package com.blairscott.framework.beandefinition;

/**
 * 记录<property>标签中的value属性值，及其类型
 */
public class TypedStringValue {

    private String value;

    private Class<?> targetType;

    public TypedStringValue(String value, Class<?> targetType) {
        this.value = value;
        this.targetType = targetType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }
}
