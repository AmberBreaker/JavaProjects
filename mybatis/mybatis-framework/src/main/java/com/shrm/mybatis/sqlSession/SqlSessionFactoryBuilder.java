package com.shrm.mybatis.sqlSession;

import com.shrm.mybatis.builder.XMLConfigBuilder;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.sqlSession.iface.SqlSessionFactory;
import com.shrm.mybatis.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;
import java.io.Reader;

/**
 * 使用的是构造者模式
 */
public class SqlSessionFactoryBuilder {

    /** 构造者通过该api接收外部的构造条件*/
    public SqlSessionFactory build(InputStream inputStream) {
        Document document = DocumentUtils.readDocument(inputStream);
        XMLConfigBuilder configBuilder = new XMLConfigBuilder();
        Configuration configuration = configBuilder.parse(document.getRootElement());
        return build(configuration);
    }

    public SqlSessionFactory build(Reader reader) {
        return null;
    }

    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }

}
