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
            StatementHandler statementHandler = configuration.newStatementHandler(mappedStatement, boundSql);
            // 去创建statement
            statement = statementHandler.prepare(connection);
            // 参数设置
            statementHandler.parameterize(statement, param);
            // 执行statement

            return statementHandler.query(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Connection
     */
    private Connection getConnection(Configuration configuration) throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }
}
