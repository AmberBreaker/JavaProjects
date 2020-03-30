package com.shrm.mybatis.handler;

import com.shrm.mybatis.handler.iface.ParameterHandler;
import com.shrm.mybatis.handler.iface.ResultSetHandler;
import com.shrm.mybatis.handler.iface.StatementHandler;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;

import java.sql.*;
import java.util.List;

public class PreparedStatementHandler implements StatementHandler {

    private BoundSql boundSql;

    private ParameterHandler parameterHandler;

    private ResultSetHandler resultSetHandler;

    public PreparedStatementHandler(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql) {
        this.boundSql = boundSql;
        parameterHandler = configuration.newParameterHandler(mappedStatement, boundSql);
        resultSetHandler = configuration.newResultSetHandler(mappedStatement);
    }

    @Override
    public Statement prepare(Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(boundSql.getSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public void parameterize(Statement statement, Object param) {
        // TODO
//        handleParameter(preparedStatement, mappedStatement, boundSql, param);
        parameterHandler.handleParameter(statement, param);
    }

    @Override
    public <T> List<T> query(Statement statement) {
        // TODO
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetHandler.handleResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
