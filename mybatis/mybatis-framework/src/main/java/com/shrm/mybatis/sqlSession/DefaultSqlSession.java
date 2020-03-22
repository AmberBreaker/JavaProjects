package com.shrm.mybatis.sqlSession;

import com.shrm.mybatis.executor.CachingExecutor;
import com.shrm.mybatis.executor.iface.Executor;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;
import com.shrm.mybatis.sqlSession.iface.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementId, Object param) {
        List<T> results = selectList(statementId, param);
        if (results != null) {
            return results.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
        if (mappedStatement == null) {
            return null;
        }
        try {
//            Connection connection = configuration.getDataSource().getConnection();
            Executor executor = configuration.newExecutor();
            return (List<T>) executor.executeQuery(mappedStatement, param, configuration, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int insert(String statementId, Object param) {
        return 0;
    }
}
