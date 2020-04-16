package com.blairscott.test;

import com.blairscott.ioc.BeanDefinition;
import com.blairscott.ioc.PropertyValue;
import com.blairscott.ioc.RuntimeBeanReference;
import com.blairscott.ioc.TypedStringValue;
import com.blairscott.po.User;
import com.blairscott.service.UserService;
import com.blairscott.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSpringV2 {
    // Bean容器
    private Map<String, Object> singletonMap = new HashMap<>();
    // 存放BeanDefinition集合
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();

    @Test
    public void test() throws Exception {
        UserService service = (UserService) getBean("userService");
        User user = new User();
        user.setUsername("wangwu");
        List<User> users = service.queryUsers(user);
    }

    @Before
    public void init() {
        loadAndRegisterBeanDefinitions("beans.xml");
    }

    private void loadAndRegisterBeanDefinitions(String location) {
        // TODO
    }

    private Object getBean(String beanName) {
        // 根据beanName从容器中获取bean
        Object singletonObj = singletonMap.get(beanName);
        if (singletonObj != null) {
            return singletonObj;
        }
        // 根据beanName获取BeanDefinition
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if (beanDefinition == null) {
            return null;
        }

        // 创建Bean
        singletonObj = createBean(beanDefinition);

        // 判断单例还是多例
        if (beanDefinition.isSingleton()) {
            singletonMap.put(beanName, singletonObj);
        } else if (beanDefinition.isPrototype()) {

        }
        return singletonObj;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        // 实例化
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
        String clazzName = beanDefinition.getClazzName();
        Class<?> clazzType = resolveClass(clazzName);
        Object bean = null;
        try {
            Constructor<?> constructor = clazzType.getDeclaredConstructor();
            bean = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
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
            setProperty(bean, name, valueToUse);
        }
    }

    private void setProperty(Object bean, String name, Object valueToUse) {
        Class<?> clazz = bean.getClass();
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(bean, valueToUse);
        } catch (Exception e) {
            e.printStackTrace();
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
        invokeMethod(bean, initMethod);
    }

    private void invokeMethod(Object bean, String initMethod) {
        Class<?> clazz = bean.getClass();
        try {
            Method method = clazz.getDeclaredMethod(initMethod);
            method.invoke(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
