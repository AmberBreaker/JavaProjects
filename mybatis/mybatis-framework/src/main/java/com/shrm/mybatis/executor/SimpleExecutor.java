package com.shrm.mybatis.executor;

import com.shrm.mybatis.handler.iface.StatementHandler;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;
import com.shrm.mybatis.mapping.ParameterMapping;
import com.shrm.mybatis.sqlsource.iface.SqlSource;
import com.shrm.mybatis.utils.SimpleTypeRegistry;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleExecutor extends BaseExecutor {

    @Override
    public Object executeQueryFromDatabase(MappedStatement mappedStatement, Object param, Configuration configuration, BoundSql boundSql) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = configuration.getDataSource().getConnection();
            if (mappedStatement == null) {
                return null;
            }
//            SqlSource sqlSource = mappedStatement.getSqlSource();
//            BoundSql boundSql = sqlSource.getBoundSql(param);
            StatementHandler statementHandler = configuration.newStatementHandler(mappedStatement, boundSql);
            // 去创建statement
            statement = statementHandler.prepare(connection);
            // 参数设置
            statementHandler.parameterize(statement, param);
            // 执行statement

            return statementHandler.query();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 参数处理
     */
    private void handleParameter(PreparedStatement preparedStatement, MappedStatement mappedStatement,
                                 BoundSql boundSql, Object param) throws Exception {
        // 从MappedStatement对象中获取入参类型
        Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();

        if (SimpleTypeRegistry.isSimpleType(parameterTypeClass)) { // 如果入参是8中基本类型或String类型
            preparedStatement.setObject(1, param);
        } else if (parameterTypeClass == Map.class) { // 如果入参是Map类型
            // TODO

        } else { // 如果入参是POJO类型
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                // 封装#{}里面的属性名称
                String name = parameterMapping.getName();

                // 利用反射，根据属性名称去入参对象中获取指定的属性值
                Field field = parameterTypeClass.getDeclaredField(name);
                field.setAccessible(true);
                Object value = field.get(param);
                // 设置statement占位符中的值（可以用parameterMap中的type对Object类型的value进行类型处理）
                preparedStatement.setObject(i + 1, value);
            }
        }

    }

    /**
     * 处理结果集
     * TODO
     */
    private List<Object> handleResultSet(ResultSet resultSet, MappedStatement mappedStatement) throws
            SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        List<Object> results = new ArrayList<>();
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
        return results;
    }

    /**
     * 获取Connection
     */
    private Connection getConnection(Configuration configuration) throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }
}
