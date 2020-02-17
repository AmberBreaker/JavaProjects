package com.shrm.sqlnode;

import com.shrm.sqlnode.iface.SqlNode;

public class IfSqlNode implements SqlNode {

    private String test;

    private SqlNode rootSqlNode;

    public IfSqlNode(String test, SqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {

    }
}
