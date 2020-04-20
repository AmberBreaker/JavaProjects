package com.blairscott.test;

import com.blairscott.dao.impl.UserDaoImpl;
import com.blairscott.po.User;
import com.blairscott.service.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.util.List;

public class TestSpringV1 {


    @Test
    public void test() throws Exception {
        UserServiceImpl service = new UserServiceImpl();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.105.108.215:3306/alidata?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("nxcswd");

        UserDaoImpl userDao = new UserDaoImpl(dataSource);
        service.setUserDao(userDao);
        // 调用UserService的方法
        User user = new User();
        user.setUsername("wangwu");
        List<User> users = service.queryUsers(user);

        System.out.println("结果：" + users);
    }
}
