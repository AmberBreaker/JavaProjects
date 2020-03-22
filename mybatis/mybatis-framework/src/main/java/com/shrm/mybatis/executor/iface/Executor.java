package com.shrm.mybatis.executor.iface;

import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;

public interface Executor {

    Object executeQuery(MappedStatement mappedStatement, Object param, Configuration configuration, BoundSql boundSql) throws Exception;

}
