package com.shrm.sqlsource;

import com.shrm.sqlnode.DynamicContext;
import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.sqlsource.iface.SqlSource;
import com.shrm.utils.GenericTokenParser;

/**
 * 封装最多带有#{}的sql信息
 *
 * 也就是#{}需要被处理一次就可以了，就可以使用占位符长期使用。这一点与${}很不一样。
 * ${}每次被调用时，就需要去解析一次SQL语句。
 */
public class RawSqlSource implements SqlSource {

    /* 封装已经解析完成的sql语句*/
    private StaticSqlSource staticSqlSource;

    public RawSqlSource(SqlNode mixedSqlNode) {
        // #{}处理的时候，不需要入参对象的支持，所以可以传null
        DynamicContext context = new DynamicContext(null);
        // 处理SqlNode
        mixedSqlNode.apply(context);

        // 将 #{} 解析为占位符（?）并保存参数信息
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);

        // 获取可以真正执行的sql语句
        String sql = parser.parse(context.getSql()); // TODO

        staticSqlSource = new StaticSqlSource(sql, handler.getParameterMappings());
    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return staticSqlSource.getBoundSql(param);
    }
}
