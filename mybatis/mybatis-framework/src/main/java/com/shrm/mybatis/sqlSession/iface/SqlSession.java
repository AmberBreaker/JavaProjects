package com.shrm.mybatis.sqlSession.iface;

import java.util.List;

/**
 * 提供CRUD功能
 */
public interface SqlSession {

    <T> T selectOne(String statementId, Object param);

    <T> List<T> selectList(String statementId, Object param);

    int insert(String statementId, Object param);

}
