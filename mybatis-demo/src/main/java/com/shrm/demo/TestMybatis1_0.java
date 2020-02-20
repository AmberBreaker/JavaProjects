package com.shrm.demo;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TestMybatis1_0 {

    /**
     * 加载properties文件
     */
    public Properties loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("properties/db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public ResultSet executeJDBC(Properties properties, String param) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password")
            );
            preparedStatement = connection.prepareStatement(properties.getProperty("db.sql"));
            preparedStatement.setString(1, param);
            resultSet = preparedStatement.executeQuery();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Test
    public void test() {
        Properties properties = loadProperties();
        ResultSet resultSet = executeJDBC(properties, "王五");
        try {
            while (resultSet.next()) {
                System.out.println(
                        "username : " + resultSet.getString("username") +
                        " address : " + resultSet.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
