package com.blairscott.framework.reader;

import com.blairscott.framework.bean.factory.BeanDefinitionRegistry;
import com.blairscott.framework.bean.factory.support.DefaultListableBeanFactory;
import com.blairscott.framework.beandefinition.BeanDefinition;
import com.blairscott.framework.beandefinition.PropertyValue;
import com.blairscott.framework.beandefinition.RuntimeBeanReference;
import com.blairscott.framework.beandefinition.TypedStringValue;
import com.blairscott.framework.utils.ReflectUtils;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.util.List;

public class XmlBeanDefinitionDocumentReader {

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionDocumentReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void registerBeanDefinitions(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            String name = element.getName();
            if ("bean".equals(name)) {
                parseDefaultElement(element);
            } else {
                parseCustomElement(element);
            }
        }
    }

    private void parseDefaultElement(Element beanElement) {
        try {
            if (beanElement == null)
                return;
            String id = beanElement.attributeValue("id");
            String name = beanElement.attributeValue("name");
            String clazzName = beanElement.attributeValue("class");
            if (clazzName == null || "".equals(clazzName)) {
                return;
            }
            Class<?> clazzType = Class.forName(clazzName);
            String initMethod = beanElement.attributeValue("init-method");
            String scope = beanElement.attributeValue("scope");
            scope = scope != null && !scope.equals("") ? scope : "singleton";
            String beanName = id == null ? name : id;
            beanName = beanName == null ? clazzType.getSimpleName() : beanName;
            BeanDefinition beanDefinition = new BeanDefinition(beanName, clazzName);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setScope(scope);
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }
            registry.registerBeanDefinition(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
        if (propertyElement == null) {
            return;
        }
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        String ref = propertyElement.attributeValue("ref");
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
            return;
        }
        PropertyValue pv = null;
        if (value != null && !value.equals("")) {
            TypedStringValue typeStringValue = new TypedStringValue(value, null);
            Class<?> targetType = ReflectUtils.getTypeByFieldName(beanDefinition.getClazzName(), name);
            typeStringValue.setTargetType(targetType);
            pv = new PropertyValue(name, typeStringValue);
            beanDefinition.addPropertyValue(pv);
        } else if (ref != null && !ref.equals("")) {
            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            pv = new PropertyValue(name, reference);
            beanDefinition.addPropertyValue(pv);
        } else {
            return;
        }
    }

    private void parseCustomElement(Element beanElement) {
        // TODO
    }
}
