package com.shrm.sqlsource;

import com.shrm.sqlsource.iface.SqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储被解析之后的SQL信息和参数信息
 * 封装DynamicSqlSource 和 RawSqlSource解析之后的信息
 */
public class StaticSqlSource implements SqlSource {

    // 解析之后的SQL语句。
    private String sql;

    // 解析过程中产生的SQL参数信息。
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return new BoundSql(sql, parameterMappings);
    }
}
