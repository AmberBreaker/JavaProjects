package com.shrm.mybatis.parser;

import com.shrm.mybatis.sqlnode.DynamicContext;
import com.shrm.mybatis.sqlsource.ParameterMappingTokenHandler;
import com.shrm.mybatis.sqlsource.StaticSqlSource;
import com.shrm.mybatis.sqlsource.iface.SqlSource;
import com.shrm.mybatis.utils.GenericTokenParser;

/**
 * 提高处理#{}的复用性
 */
public class SqlSourceParser {

    private DynamicContext context;

    public SqlSourceParser(DynamicContext context) {
        this.context = context;
    }

    /**
     * 将 #{} 解析为占位符（?）并保存参数信息
     */
    public SqlSource parse() {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);

        // 获取可以真正执行的sql语句
        String sql = parser.parse(context.getSql()); // TODO

        return new StaticSqlSource(sql, handler.getParameterMappings());
    }
}
