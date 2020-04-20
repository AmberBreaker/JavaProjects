package com.blairscott.framework.reader;

import com.blairscott.framework.bean.factory.BeanDefinitionRegistry;
import com.blairscott.framework.bean.factory.support.DefaultListableBeanFactory;
import com.blairscott.framework.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

public class XmlBeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(InputStream inputStream) {
        Document document = DocumentReader.createDocument(inputStream);
        XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(registry);
        documentReader.registerBeanDefinitions(document.getRootElement());
    }
}
