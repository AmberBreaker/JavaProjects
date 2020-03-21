package com.shrm.mybatis.mapping;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 封装XML文件中的配置信息
 * 针对mybatis-config.xml、*Mapper.xml
 */
public class Configuration {

    // 封装MappedStatement对象集合
    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 封装数据源对象
    private DataSource dataSource;

    public MappedStatement getMappedStatementById(String statementId) {
        return this.mappedStatements.get(statementId);
    }

    public void addMappedStatement(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId, mappedStatement);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
