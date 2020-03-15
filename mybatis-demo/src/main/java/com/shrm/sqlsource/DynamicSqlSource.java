package com.shrm.sqlsource;

import com.shrm.sqlnode.DynamicContext;
import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.sqlsource.iface.SqlSource;
import com.shrm.utils.GenericTokenParser;

/**
 * 封装带有${}或者动态SQL标签的SQL信息
 * 与#{}不同，${}每次被调用时，就需要去解析一次SQL语句。
 */
public class DynamicSqlSource implements SqlSource {

    private SqlNode rootSqlNode;

    public DynamicSqlSource(SqlNode mixedSqlNode) {
        this.rootSqlNode = mixedSqlNode;
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        // #{}处理的时候，不需要入参对象的支持，所以可以传null
        DynamicContext context = new DynamicContext(param);
        // 处理SqlNode（先去处理动态标签和${}，拼接成一条sql文本，该sql文本还包含#{}）
        this.rootSqlNode.apply(context);

        // 将 #{} 解析为占位符（?）并保存参数信息
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);

        // 获取可以真正执行的sql语句
        String sql = parser.parse(context.getSql());
        return new BoundSql(sql, handler.getParameterMappings());
    }
}
