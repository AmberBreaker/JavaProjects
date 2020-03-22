package com.shrm.demo;

import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.builder.XMLConfigBuilder;
import com.shrm.mybatis.io.Resources;
import com.shrm.mybatis.sqlSession.DefaultSqlSession;
import com.shrm.mybatis.sqlSession.DefaultSqlSessionFactory;
import com.shrm.mybatis.sqlSession.SqlSessionFactoryBuilder;
import com.shrm.mybatis.sqlSession.iface.SqlSession;
import com.shrm.mybatis.sqlSession.iface.SqlSessionFactory;
import com.shrm.mybatis.utils.DocumentUtils;
import com.shrm.pojo.User;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class TestMybatis3_0 {

    @Test
    public void testInitConfiguration() {
        String location = "config/mybatis-config.xml";
        Configuration configuration = getConfiguration(location);
        System.out.println(configuration);
    }

    private Configuration getConfiguration(String location) {

        InputStream inputStream = Resources.getResourceAsStream(location);

        Document document = DocumentUtils.readDocument(inputStream);

        XMLConfigBuilder configBuilder = new XMLConfigBuilder();

        return configBuilder.parse(document.getRootElement());
    }



    @Test
    public void testQueryUserById() {
        String location = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(location);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();

        String statementId = "test.findUserById";
        User user = new User();
        user.setUsername("王五");
        List<User> users = sqlSession.selectList(statementId, user);
        for (User res : users) {
            System.out.println(res);
        }
    }

}
