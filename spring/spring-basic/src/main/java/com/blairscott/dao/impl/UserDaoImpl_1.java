package com.blairscott.dao.impl;

import com.blairscott.dao.UserDao;
import com.blairscott.po.User;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl_1 implements UserDao {

    private DataSource dataSource;

    public UserDaoImpl_1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> queryUserList(String sqlId, Object param) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            String sql = "select * from user where username = ?";
            preparedStatement = connection.prepareStatement(sql);
            User user = (User) param;
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
            List<User> results = new ArrayList<>();
            Class<?> clazz = User.class;
            while (resultSet.next()) {
                User instance = (User) clazz.newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(instance, resultSet.getObject(i));
                }
                results.add(instance);
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
