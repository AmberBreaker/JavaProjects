package com.shrm.sqlnode;

import com.shrm.sqlnode.iface.SqlNode;

/**
 * 封装的是带有#()的文本字符串
 */
public class StaticTextSqlNode implements SqlNode {

    private String sqlText;

    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {
        dynamicContext.appendSql(this.sqlText);
    }
}
