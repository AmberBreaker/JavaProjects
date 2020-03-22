package com.shrm.mybatis.executor;

import com.shrm.mybatis.executor.iface.Executor;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;

import java.sql.Connection;

/**
 * 只处理一级缓存的逻辑
 */
public abstract class BaseExecutor implements Executor {

    @Override
    public Object executeQuery(MappedStatement mappedStatement, Object param, Configuration configuration, BoundSql boundSql) throws Exception {
        // 如果一级缓存没有，则直接查询数据库。
        return executeQueryFromDatabase(mappedStatement, param, configuration, boundSql);
    }

    public abstract Object executeQueryFromDatabase(MappedStatement mappedStatement, Object param, Configuration configuration, BoundSql boundSql) throws Exception;

}
