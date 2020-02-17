package com.shrm.sqlsource;

import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.sqlsource.iface.SqlSource;

/**
 * 封装带有${}或者动态SQL标签的SQL信息
 * 与#{}不同，${}每次被调用时，就需要去解析一次SQL语句。
 */
public class DynamicSqlSource implements SqlSource {

    public DynamicSqlSource(SqlNode mixedSqlNode) {

    }

    @Override
    public BoundSql getBoundSql(Object param) {

        return null;
    }
}
