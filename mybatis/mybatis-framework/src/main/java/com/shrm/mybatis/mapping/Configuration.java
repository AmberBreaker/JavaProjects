package com.shrm.mybatis.mapping;

import com.shrm.mybatis.executor.CachingExecutor;
import com.shrm.mybatis.executor.SimpleExecutor;
import com.shrm.mybatis.executor.iface.Executor;
import com.shrm.mybatis.handler.DefaultParameterHandler;
import com.shrm.mybatis.handler.DefaultResultSetHandler;
import com.shrm.mybatis.handler.PreparedStatementHandler;
import com.shrm.mybatis.handler.SimpleStatementHandler;
import com.shrm.mybatis.handler.iface.ParameterHandler;
import com.shrm.mybatis.handler.iface.ResultSetHandler;
import com.shrm.mybatis.handler.iface.StatementHandler;

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

    private boolean isCached = true;

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

    public Executor newExecutor() {
        Executor executor = null;
        // 可以通过配置文件，去指定使用哪种Executor
        // 默认创建的是SimpleExecutor
        executor = new SimpleExecutor();
        // 默认优先使用二级缓存执行器（CachingExecutor）
        if (isCached) {
            executor = new CachingExecutor(executor);
        }

        return executor;
    }

    public StatementHandler newStatementHandler(MappedStatement mappedStatement, BoundSql boundSql) {
        StatementHandler statementHandler = null;
        switch (mappedStatement.getStatementType()){
            case "prepared" :
                statementHandler = new PreparedStatementHandler(this, mappedStatement, boundSql);
                // TODO: 参数【configuration】能不能用 this 代替
                break;

            case "simple" :
                statementHandler = new SimpleStatementHandler(mappedStatement, boundSql);
                break;

            default :
                break;
        }

        return statementHandler;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, boundSql);
    }

    public ResultSetHandler newResultSetHandler(MappedStatement mappedStatement) {
        return new DefaultResultSetHandler(mappedStatement);
    }
}
