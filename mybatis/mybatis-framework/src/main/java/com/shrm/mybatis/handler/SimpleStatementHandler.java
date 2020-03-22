package com.shrm.mybatis.handler;

import com.shrm.mybatis.handler.iface.StatementHandler;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.MappedStatement;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SimpleStatementHandler implements StatementHandler {

    private BoundSql boundSql;

    public SimpleStatementHandler(MappedStatement mappedStatement, BoundSql boundSql) {
        this.boundSql = boundSql;
    }


    @Override
    public Statement prepare(Connection connection) {
        // TODO
        return null;
    }

    @Override
    public void parameterize(Statement statement, Object param) {

    }

    @Override
    public <T> List<T> query(Statement statement) {
        return null;
    }
}
