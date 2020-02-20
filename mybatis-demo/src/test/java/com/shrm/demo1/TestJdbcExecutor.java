package com.shrm.demo1;

import com.shrm.demo.TestMybatis1_0;
import com.shrm.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class TestJdbcExecutor {

    TestMybatis1_0 initService = new TestMybatis1_0();

    @Test
    public void test1() {
        Properties properties = initService.loadProperties();
        ResultSet resultSet = initService.executeJDBC(properties, "王五");
        List<User> users = dealResultSet(resultSet, User.class);
        for (User user : users) {
            System.out.println(user);
        }

    }

    public <T> List<T> dealResultSet(ResultSet resultSet, Class<T> clazz) {
        List<T> returnList = new ArrayList<>();
        try {
            Field[] declaredFields = clazz.getDeclaredFields();
            while (resultSet.next()) {
                T beanClass = clazz.newInstance();
                for (Field field : declaredFields) {
                    String fieldName = field.getName();
                    Method beanSetMethod = clazz.getDeclaredMethod("set" + capitalize(fieldName), field.getType());
                    Object rs = getValueWithType(field, resultSet);
                    beanSetMethod.invoke(beanClass, rs);
                }
                returnList.add(beanClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnList;
    }

    private Object getValueWithType(Field field, ResultSet resultSet) {
        String fieldName = field.getName();
        Class<?> fieldType = field.getType();
        Object returnObj = null;
        try {
            if (String.class == fieldType) {
                returnObj = resultSet.getString(fieldName);
            } else if (Integer.class == fieldType) {
                returnObj = resultSet.getInt(fieldName);
            } else if (Date.class == fieldType) {
                returnObj = resultSet.getDate(fieldName);
            } else {
                returnObj = resultSet.getObject(fieldName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnObj;
    }

    private String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            return str;
        }
        return new StringBuilder().append(Character.toTitleCase(firstChar))
                .append(str.substring(1, str.length())).toString();
    }
}
