package com.shrm.mybatis.handler.iface;

import java.sql.ResultSet;
import java.util.List;

public interface ResultSetHandler {

    <T> List<T> handleResultSet(ResultSet resultSet);

}
