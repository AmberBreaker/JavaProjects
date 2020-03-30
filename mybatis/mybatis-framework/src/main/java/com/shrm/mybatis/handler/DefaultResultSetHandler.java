package com.shrm.mybatis.handler;

import com.shrm.mybatis.handler.iface.ResultSetHandler;
import com.shrm.mybatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultResultSetHandler implements ResultSetHandler {

    private MappedStatement mappedStatement;

    public DefaultResultSetHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }


    /**
     * 处理结果集
     * TODO
     */
    @Override
    public <T> List<T> handleResultSet(ResultSet resultSet) {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        List<Object> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                // 利用反射new一个对象
                Object result = resultTypeClass.newInstance();

                // 要获取每一列的值，然后封装到结果对象中对应的属性名称上
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    // 获取🈚️每一列的名称
                    String columnName = metaData.getColumnName(i + 1);
                    // 获取每一列的值
                    Object value = resultSet.getObject(columnName);
                    // 列明和属性名要完全一致
                    Field field = resultTypeClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    // 给映射的对象赋值
                    field.set(result, value);
                }
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return (List<T>) results;
    }
}
