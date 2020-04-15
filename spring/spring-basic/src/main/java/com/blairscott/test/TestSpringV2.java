package com.blairscott.test;

import com.blairscott.ioc.BeanDefinition;
import com.blairscott.po.User;
import com.blairscott.service.UserService;
import org.junit.Test;

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
        // TODO
        return null;
    }

}
