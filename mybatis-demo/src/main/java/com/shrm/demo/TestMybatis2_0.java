package com.shrm.demo;

import com.shrm.config.Configuration;
import com.shrm.config.MappedStatement;
import com.shrm.pojo.User;
import com.shrm.sqlsource.BoundSql;
import com.shrm.sqlsource.ParameterMapping;
import com.shrm.sqlsource.iface.SqlSource;
import com.shrm.utils.SimpleTypeRegistry;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 1:42:40
 * 目的是使用XML来表达mybatis的全局配置信息和业务相关的映射信息。
 * 其次优化数据连接的创建（使用连接池）。
 */
public class TestMybatis2_0 {

    public List<Object> queryByJDBC(Configuration configuration, String statementId, Object param) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /*
             * 1、获取Connection。
             * 此处使用DataSource去优化Connection的获取。
             * DataSource是通过XML配置来产生的。
             * XML信息是通过Configuration对象保存的。
             */
            connection = getConnection(configuration);

            /*
             * 2、获取JDBC可执行的SQL语句。
             * 【SQL信息】是配置在映射文件中的，是通过【select等statement】标签来配置的。
             * 不同脚本的【SQL信息】是封装到不同类型的【SqlNode】对象中的。
             * 【SqlNode】信息是保存到【SqlSource】中的。
             * 【SqlSource】的信息是封装到【MappedStatement】对象中的。
             * 【MappedStatement】对象是被保存到【Configuration】对象中的。
             */
            MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
            if (mappedStatement == null) {
                return null;
            }
            // 获取SqlSource
            SqlSource sqlSource = mappedStatement.getSqlSource();
            BoundSql boundSql = sqlSource.getBoundSql(param);
            String sql = boundSql.getSql();

            /**
             * 3、获取预处理statement
             */
            String statementType = mappedStatement.getStatementType();
            if ("prepared".equals(statementType)) {
                preparedStatement = connection.prepareStatement(sql);

                /**
                 * 4、设置参数
                 */
                handleParameter(preparedStatement, mappedStatement, boundSql, param);

                /**
                 * 5、执行查询，返回结果集
                 */
                resultSet = preparedStatement.executeQuery();

                /**
                 * 6、处理结果集
                 */
                return handleResultSet(resultSet, mappedStatement);
            } else if ("simple".equals(statementType)) {
                // 创建简单的statement对象
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析XML，获取配置文件
     * TODO
     */
    private Configuration loadConfiguration() {

        // 解析XML


        return null;
    }

    /**
     * 获取Connection
     */
    private Connection getConnection(Configuration configuration) throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
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
        }
        return results;
    }

    @Test
    public void test() {
        // 加载XML配置文件，包括全局配置文件和映射配置文件
        Configuration configuration = loadConfiguration();
        String statementId = "test.findUserById";

        User user = new User();
        user.setId(1);
        // 执行JDBC代码，并返回已经映射的结果
        User result = (User) queryByJDBC(configuration, statementId, user);
        System.out.println(result);
    }
}
