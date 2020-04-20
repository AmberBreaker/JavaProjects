package com.blairscott.framework.bean.factory;

public interface BeanFactory {

    Object getBean(String name);

    Object getBean(Class<?> type, String name);

}
