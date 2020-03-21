package com.shrm.demo;

import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.builder.XMLConfigBuilder;
import com.shrm.mybatis.io.Resources;
import com.shrm.mybatis.utils.DocumentUtils;
import org.dom4j.Document;
import org.junit.Test;

import java.io.InputStream;

public class TestMybatis3_0 {

    @Test
    public void testInitConfiguration() {
        String location = "config/mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(location);

        Document document = DocumentUtils.readDocument(inputStream);

        XMLConfigBuilder configBuilder = new XMLConfigBuilder();

        Configuration configuration = configBuilder.parse(document.getRootElement());
        System.out.println(configuration);
    }

}
