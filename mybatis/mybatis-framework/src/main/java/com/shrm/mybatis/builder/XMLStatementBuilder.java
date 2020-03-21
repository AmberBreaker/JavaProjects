package com.shrm.mybatis.builder;

import com.shrm.mybatis.mapping.Configuration;
import com.shrm.mybatis.mapping.MappedStatement;
import com.shrm.mybatis.sqlsource.iface.SqlSource;
import org.dom4j.Element;

public class XMLStatementBuilder {

    private Configuration configuration;

    private boolean isDynamic;

    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public XMLStatementBuilder() {
        this.configuration = new Configuration();
    }

    public void parseStatementElement(Element selectElement, String namespace) {
        String statementId = selectElement.attributeValue("id");
        if (statementId == null || "".equals(statementId)) {
            return;
        }
        statementId = namespace + "." + statementId;
        Class<?> parameterType = resolveType(selectElement.attributeValue("parameterType"));
        Class<?> resultType = resolveType(selectElement.attributeValue("resultType"));

        String statementType = selectElement.attributeValue("statementType");
        statementType = statementType == null ? "prepared" : statementType;

        // 解析SQL信。
        SqlSource sqlSource = createSqlSource(selectElement);
        MappedStatement mappedStatement = new MappedStatement(sqlSource, statementType, statementId, parameterType, resultType); // TODO
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    // 去解析select等CURD标签中的SQL脚本信息
    private SqlSource createSqlSource(Element selectElement) {
        XMLScriptBuilder scriptBuilder = new XMLScriptBuilder();
        SqlSource sqlSource = scriptBuilder.parseScriptNode(selectElement);
        return sqlSource;
    }

    private Class<?> resolveType(String parameterTypeStr) {
        try {
            return Class.forName(parameterTypeStr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
