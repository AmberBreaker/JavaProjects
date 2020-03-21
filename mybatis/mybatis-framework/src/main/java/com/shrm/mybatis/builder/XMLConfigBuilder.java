package com.shrm.mybatis.builder;

import com.shrm.mybatis.io.Resources;
import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.utils.DocumentUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        configuration = new Configuration();
    }

    public Configuration parse(Element rootElement) {
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);
        Element mappers = rootElement.element("mappers");
        parseMappers(mappers);
        return configuration;
    }

    private void parseEnvironments(Element environments) {
        String def = environments.attributeValue("default");
        List<Element> environment = environments.elements("environment");
        for (Element env : environment) {
            if ("dev".equals(def)) {
                parseEnvironment(env);
                break;
            }
        }
    }

    private void parseEnvironment(Element envElement) {
        Element datasource = envElement.element("datasource");
        parseDataSource(datasource);
    }

    private void parseDataSource(Element dsElement) {
        String typeValue = dsElement.attributeValue("type");
        if ("DBCP".equals(typeValue)) {
            BasicDataSource dataSource = new BasicDataSource();
            Properties dsProperties = new Properties();
            List<Element> properties = dsElement.elements("property");
            for (Element property : properties) {
                String name = property.attributeValue("name");
                String value = property.attributeValue("value");
                dsProperties.setProperty(name, value);
            }
            dataSource.setDriverClassName(dsProperties.getProperty("driver"));
            dataSource.setUrl(dsProperties.getProperty("url"));
            dataSource.setUsername(dsProperties.getProperty("username"));
            dataSource.setPassword(dsProperties.getProperty("password"));
            this.configuration.setDataSource(dataSource);
        }
    }

    private void parseMappers(Element rootElement) {
        List<Element> mappers = rootElement.elements("mapper");
        for (Element mapper : mappers) {
            String resource = mapper.attributeValue("resource");
            InputStream inputStream = Resources.getResourceAsStream(resource);
            Document document = DocumentUtils.readDocument(inputStream);
            XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(this.configuration);
            mapperBuilder.parseXmlMapper(document.getRootElement());
        }
    }
}
