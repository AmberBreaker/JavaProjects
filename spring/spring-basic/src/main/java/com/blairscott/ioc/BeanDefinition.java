package com.blairscott.ioc;

import com.blairscott.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置文件中的<bean>标签
 */
public class BeanDefinition {

    private static final String SCOPE_SINGLETON = "singleton";
    private static final String SCOPE_PROTOTYPE = "prototype";
    // id
    private String beanName;
    // class
    private String clazzName;
    // init-method
    private String initMethod;
    // scope
    private String scope;
    // <property>
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public BeanDefinition(String beanName, String clazzName) {
        this.beanName = beanName;
        this.clazzName = clazzName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }

    public boolean isSingleton() {
        return StringUtils.isEmpty(scope) || SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return scope != null && SCOPE_PROTOTYPE.equals(scope);
    }
}
