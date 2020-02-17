package com.shrm.sqlsource;

import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.sqlsource.iface.SqlSource;

/**
 * 封装最多带有#{}的sql信息
 *
 * 也就是#{}需要被处理一次就可以了，就可以使用占位符长期使用。这一点与${}很不一样。
 * ${}每次被调用时，就需要去解析一次SQL语句。
 */
public class RawSqlSource implements SqlSource {

    public RawSqlSource(SqlNode mixedSqlNode) {

    }

    @Override
    public BoundSql getBoundSql(Object param) {
        return null;
    }
}
