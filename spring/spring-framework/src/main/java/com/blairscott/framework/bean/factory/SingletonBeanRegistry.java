package com.blairscott.framework.bean.factory;

/**
 * 访问单例Bean实例集合的接口
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);

    void addSingleton(String name, Object bean);

}
