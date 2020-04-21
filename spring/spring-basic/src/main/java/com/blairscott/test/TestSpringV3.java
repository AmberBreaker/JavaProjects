package com.blairscott.test;

import com.blairscott.framework.bean.factory.support.DefaultListableBeanFactory;
import com.blairscott.framework.reader.XmlBeanDefinitionReader;
import com.blairscott.framework.resource.ClasspathResource;
import com.blairscott.framework.resource.Resource;
import com.blairscott.po.User;
import com.blairscott.service.UserService;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestSpringV3 {
    @Test
    public void test() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        Resource resource = new ClasspathResource("beans.xml");
        InputStream inputStream = resource.getResource();

        reader.loadBeanDefinitions(inputStream);

        UserService service = (UserService) factory.getBean("userService");
        User userExample = new User();
        userExample.setUsername("wangwu");
        List<User> users = service.queryUsers(userExample);
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println();
    }
}
