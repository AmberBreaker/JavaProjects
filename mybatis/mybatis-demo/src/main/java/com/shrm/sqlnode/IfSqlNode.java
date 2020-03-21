package com.shrm.sqlnode;

import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.mybatis.utils.OgnlUtils;

/**
 * 封装if标签中的信息
 */
public class IfSqlNode implements SqlNode {

    private String test;

    private SqlNode rootSqlNode;

    public IfSqlNode(String test, SqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        boolean isTrue = OgnlUtils.evaluateBoolean(test, context.getBinding().get("_parameter")); // TODO
        if (isTrue) {
            rootSqlNode.apply(context);
//            context.appendSql(context.getSql());
        }
    }
}
