package com.blairscott.framework.bean.factory.registry;

import com.blairscott.framework.bean.factory.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return this.singletonObjects.get(name);
    }

    @Override
    public void addSingleton(String name, Object bean) {
        this.singletonObjects.put(name, bean);
    }
}
