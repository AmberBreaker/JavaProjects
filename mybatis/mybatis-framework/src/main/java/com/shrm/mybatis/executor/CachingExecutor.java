package com.shrm.mybatis.executor;

import com.shrm.mybatis.executor.iface.Executor;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;
import com.shrm.mybatis.sqlsource.iface.SqlSource;

import java.sql.Connection;

/**
 * 处理二级缓存
 */
public class CachingExecutor implements Executor {

    private Executor delegate;

    public CachingExecutor(Executor executor) {
        this.delegate = executor;
    }

    @Override
    public Object executeQuery(MappedStatement mappedStatement, Object param, Configuration configuration, BoundSql boundSql) throws Exception {
        SqlSource sqlSource = mappedStatement.getSqlSource();
        boundSql = sqlSource.getBoundSql(param);
        // 如果没有二级缓存，则委托一级缓存去处理
        delegate = new SimpleExecutor();
        return delegate.executeQuery(mappedStatement, param, configuration, boundSql);
    }
}
