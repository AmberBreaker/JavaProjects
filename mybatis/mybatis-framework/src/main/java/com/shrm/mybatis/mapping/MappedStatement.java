package com.shrm.mybatis.mapping;

import com.shrm.mybatis.sqlsource.iface.SqlSource;

/**
 * 用来封装映射文件中的CRUD标签脚本内容，如select标签
 */
public class MappedStatement {

    private SqlSource sqlSource;

    private String statementType;

    private String statementId;

    private Class<?> parameterTypeClass;

    private Class<?> resultTypeClass;

    public MappedStatement () {}

    public MappedStatement(SqlSource sqlSource, String statementType, String statementId, Class<?> parameterTypeClass, Class<?> resultTypeClass) {
        this.sqlSource = sqlSource;
        this.statementType = statementType;
        this.statementId = statementId;
        this.parameterTypeClass = parameterTypeClass;
        this.resultTypeClass = resultTypeClass;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public Class<?> getParameterTypeClass() {
        return parameterTypeClass;
    }

    public void setParameterTypeClass(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }

    public Class<?> getResultTypeClass() {
        return resultTypeClass;
    }

    public void setResultTypeClass(Class<?> resultTypeClass) {
        this.resultTypeClass = resultTypeClass;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }
}
