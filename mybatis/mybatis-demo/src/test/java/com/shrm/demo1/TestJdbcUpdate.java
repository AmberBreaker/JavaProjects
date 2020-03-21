package com.shrm.demo1;

import com.shrm.demo.TestMybatis1_0;
import com.shrm.pojo.SqlValueNode;
import com.shrm.pojo.User;
import com.shrm.mybatis.utils.StringUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestJdbcUpdate {

    TestMybatis1_0 test = new TestMybatis1_0();

    @Test
    public void test() {

        User user = new User();
        user.setId(28);
        user.setUsername("Jerry");
        Properties properties = test.loadProperties();


        Connection connection = null;
        try {
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            PreparedStatement ps = getPs(connection, "users", user, " id = 27");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private <T> PreparedStatement getPs(Connection connection, String tableName, T bean, String whereClause) throws Exception {
        StringBuffer sql = new StringBuffer("update " + tableName + " set ");

        Class<?> beanClass = bean.getClass();
        Field[] declaredFields = beanClass.getDeclaredFields();

        List<SqlValueNode> list = new ArrayList<>();
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            String getMethodStr = "get" + StringUtil.capitalize(fieldName);
            Method getMethod = beanClass.getMethod(getMethodStr);
            Object valueObj = getMethod.invoke(bean);
            if (valueObj != null) {
                String columnName = getColumnName(fieldName);
                sql.append(columnName + "=?,");
                SqlValueNode valueNode = new SqlValueNode();
                valueNode.setFieldType(field.getType());
                valueNode.setFieldValue(valueObj);
                list.add(valueNode);
            }
        }
        sql = new StringBuffer(sql.substring(0, sql.length() - 1));
        if (whereClause != null && whereClause.length() > 0) {
            sql.append(" where ");
            sql.append(whereClause);
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        int i = 0;
        for (SqlValueNode valueNode : list) {
            Class<?> fieldType = valueNode.getFieldType();
            Object fieldValue = valueNode.getFieldValue();
            if (fieldType == String.class) {
                preparedStatement.setString(i + 1, (String) fieldValue);
            } else if (fieldType == Timestamp.class) {
                preparedStatement.setTimestamp(i + 1, (Timestamp) fieldValue);
            } else if (fieldType == Integer.class) {
                preparedStatement.setInt(i + 1, (Integer) fieldValue);
            }
            i++;
        }
        return preparedStatement;
    }

    private String getColumnName(String fieldName) {

        return fieldName;
    }
}
