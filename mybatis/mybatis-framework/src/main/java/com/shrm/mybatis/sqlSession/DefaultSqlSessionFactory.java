package com.shrm.mybatis.sqlSession;

import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.sqlSession.iface.SqlSession;
import com.shrm.mybatis.sqlSession.iface.SqlSessionFactory;

/**
 *
 * 只负责创建SqlSession，不负责配置文件的解析工作。
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(this.configuration);
    }
}
