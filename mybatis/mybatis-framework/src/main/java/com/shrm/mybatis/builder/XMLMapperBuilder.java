package com.shrm.mybatis.builder;

import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.utils.DocumentUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private String namespace;

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public XMLMapperBuilder() {
        this.configuration = new Configuration();
    }

    public void parseXmlMapper(Element rootElement) {
        this.namespace = rootElement.attributeValue("namespace");
        List<Element> selectElements = rootElement.elements("select");
        selectElements.addAll(rootElement.elements("update"));
        selectElements.addAll(rootElement.elements("delete"));
        selectElements.addAll(rootElement.elements("update"));
        for (Element selectElement : selectElements) {
            XMLStatementBuilder statementBuilder = new XMLStatementBuilder(this.configuration);
            statementBuilder.parseStatementElement(selectElement, namespace);
        }
    }

}
