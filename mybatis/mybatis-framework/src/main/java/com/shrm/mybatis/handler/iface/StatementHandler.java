package com.shrm.mybatis.handler.iface;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public interface StatementHandler {

    Statement prepare(Connection connection);

    void parameterize(Statement statement, Object param);

    <T> List<T> query(Statement statement);
}
