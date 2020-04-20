package com.blairscott.framework.bean.factory.support;


import com.blairscott.framework.bean.factory.AutowireCapableBeanFactory;
import com.blairscott.framework.beandefinition.BeanDefinition;
import com.blairscott.framework.beandefinition.PropertyValue;
import com.blairscott.framework.beandefinition.RuntimeBeanReference;
import com.blairscott.framework.beandefinition.TypedStringValue;
import com.blairscott.framework.utils.ReflectUtils;
import com.blairscott.framework.utils.StringUtils;

import java.util.List;

/**
 * 负责Bean的创建与装配
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory{

    @Override
    public Object createBean(BeanDefinition beanDefinition) {
        Object bean = createInstance(beanDefinition);
        if (bean == null) {
            return null;
        }
        // 属性填充
        populateBean(bean, beanDefinition);
        // 初始化（init）
        initBean(bean, beanDefinition);
        return bean;
    }

    /**
     * 通过set方法注入
     * @param beanDefinition
     * @return
     *
     * TODO 通过静态工厂创建
     * TODO 通过实例工厂创建
     */
    private Object createInstance(BeanDefinition beanDefinition) {
        try {
            String clazzName = beanDefinition.getClazzName();
            Class<?> clazzType = resolveClass(clazzName);
            Object bean = ReflectUtils.createObject(clazzType);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Class<?> resolveClass(String clazzName) {
        try {
            Class<?> clazz = Class.forName(clazzName);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void populateBean(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            Object valueToUse = null;
            if (value instanceof TypedStringValue) {
                TypedStringValue typedStringValue = (TypedStringValue) value;
                String stringValue = typedStringValue.getValue();
                Class<?> targetType = typedStringValue.getTargetType();
                if (targetType == String.class) {
                    valueToUse = stringValue;
                } else if (targetType == Integer.class) {
                    valueToUse = Integer.parseInt(stringValue);
                }

            } else if (value instanceof RuntimeBeanReference) {
                RuntimeBeanReference runtimeBean = (RuntimeBeanReference) value;
                String ref = runtimeBean.getRef();
                valueToUse = getBean(ref);
            }
            ReflectUtils.setProperty(bean, name,valueToUse);
        }
    }

    private void initBean(Object bean, BeanDefinition beanDefinition) {
        // TODO Aware接口会在此时被处理
        invokeInitMethod(bean, beanDefinition);
    }

    /**
     * bean标签配置了init-method属性。
     * TODO bean标签实现了InitializingBean接口。
     */
    private void invokeInitMethod(Object bean, BeanDefinition beanDefinition) {
        String initMethod = beanDefinition.getInitMethod();
        if (StringUtils.isEmpty(initMethod)) {
            return;
        }
        ReflectUtils.invokeMethod(bean, initMethod);
    }
}
