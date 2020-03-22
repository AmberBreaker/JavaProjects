package com.shrm.mybatis.sqlSession.iface;

import com.shrm.mybatis.mapping.Configuration;

/**
 * 仿工厂方法设计模式。
 */
public interface SqlSessionFactory {

    SqlSession openSqlSession();


}
