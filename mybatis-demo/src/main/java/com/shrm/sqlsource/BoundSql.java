package com.shrm.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装解析之后的SQL信息，以及解析占位符?产生的参数信息
 */
public class BoundSql {

    // 解析之后的SQL语句。
    private String sql;

    // 解析过程中产生的SQL参数信息。
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMappings(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }
}
