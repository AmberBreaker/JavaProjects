package com.blairscott.framework.bean.factory;

import com.blairscott.framework.beandefinition.BeanDefinition;

/**
 * 提供对于BeanDefinition访问操作
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

}
