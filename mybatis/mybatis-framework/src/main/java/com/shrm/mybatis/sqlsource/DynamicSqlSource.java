package com.shrm.mybatis.sqlsource;

import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.parser.SqlSourceParser;
import com.shrm.mybatis.utils.GenericTokenParser;
import com.shrm.mybatis.sqlnode.DynamicContext;
import com.shrm.mybatis.sqlnode.iface.SqlNode;
import com.shrm.mybatis.sqlsource.iface.SqlSource;

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
        SqlSourceParser sqlSourceParser = new SqlSourceParser(context);
        SqlSource sqlSource = sqlSourceParser.parse();
        return sqlSource.getBoundSql(param);
    }
}
