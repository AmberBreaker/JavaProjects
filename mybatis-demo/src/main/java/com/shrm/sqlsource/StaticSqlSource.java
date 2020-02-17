package com.shrm.sqlsource;

import com.shrm.sqlsource.iface.SqlSource;

/**
 *
 */
public class StaticSqlSource implements SqlSource {
    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
