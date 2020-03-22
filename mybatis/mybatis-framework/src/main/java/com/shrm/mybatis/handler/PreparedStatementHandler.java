package com.shrm.mybatis.handler;

import com.shrm.mybatis.handler.iface.StatementHandler;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.MappedStatement;

import java.sql.*;
import java.util.List;

public class PreparedStatementHandler implements StatementHandler {

    private BoundSql boundSql;

    public PreparedStatementHandler(MappedStatement mappedStatement, BoundSql boundSql) {
        this.boundSql = boundSql;
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
    }

    @Override
    public <T> List<T> query(Statement statement) {
        // TODO
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
