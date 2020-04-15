package com.blairscott.ioc;

/**
 * 记录<property>标签中的ref属性值
 */
public class RuntimeBeanReference {

    private Object ref;

    public RuntimeBeanReference(Object ref) {
        this.ref = ref;
    }

    public Object getRef() {
        return ref;
    }

    public void setRef(Object ref) {
        this.ref = ref;
    }
}
