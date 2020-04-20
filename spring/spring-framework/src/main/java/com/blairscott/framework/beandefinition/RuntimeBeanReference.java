package com.blairscott.framework.beandefinition;

/**
 * 记录<property>标签中的ref属性值
 */
public class RuntimeBeanReference {

    private String ref;

    public RuntimeBeanReference(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
