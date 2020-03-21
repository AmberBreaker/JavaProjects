package com.shrm.mybatis.sqlsource;

import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.parser.SqlSourceParser;
import com.shrm.mybatis.utils.GenericTokenParser;
import com.shrm.mybatis.sqlnode.DynamicContext;
import com.shrm.mybatis.sqlnode.iface.SqlNode;
import com.shrm.mybatis.sqlsource.iface.SqlSource;

/**
 * 封装最多带有#{}的sql信息
 *
 * 也就是#{}需要被处理一次就可以了，就可以使用占位符长期使用。这一点与${}很不一样。
 * ${}每次被调用时，就需要去解析一次SQL语句。
 */
public class RawSqlSource implements SqlSource {

    /* 封装已经解析完成的sql语句*/
    private SqlSource sqlSource;

    /* 解析阶段去做的*/
    public RawSqlSource(SqlNode mixedSqlNode) {
        // #{}处理的时候，不需要入参对象的支持，所以可以传null
        DynamicContext context = new DynamicContext(null);
        // 处理SqlNode
        mixedSqlNode.apply(context);

        // TODO
        SqlSourceParser sqlSourceParser = new SqlSourceParser(context);
        sqlSource = sqlSourceParser.parse();
    }

    /* 执行阶段去做的*/
    @Override
    public BoundSql getBoundSql(Object param) {
        return sqlSource.getBoundSql(param);
    }
}
