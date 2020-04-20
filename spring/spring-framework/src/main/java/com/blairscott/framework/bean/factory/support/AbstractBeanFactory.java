package com.blairscott.framework.bean.factory.support;

import com.blairscott.framework.bean.factory.BeanFactory;
import com.blairscott.framework.bean.factory.registry.DefaultSingletonBeanRegistry;
import com.blairscott.framework.beandefinition.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) {
        Object bean = doGetBean(null, name);
        return bean;
    }

    @Override
    public Object getBean(Class<?> type, String name) {
        Object bean = doGetBean(type, name);
        return bean;
    }

    private Object doGetBean(Class<?> type, String name) {
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        singleton = createBean(beanDefinition);
        if (beanDefinition.isSingleton()) {
            addSingleton(name, singleton);
        } else if (beanDefinition.isPrototype()) {

        }
        return singleton;
    }

    public abstract Object createBean(BeanDefinition beanDefinition);

    public abstract BeanDefinition getBeanDefinition(String name);
}
