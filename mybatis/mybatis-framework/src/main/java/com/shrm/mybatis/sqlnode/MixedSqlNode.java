package com.shrm.mybatis.sqlnode;

import com.shrm.mybatis.sqlnode.iface.SqlNode;

import java.util.List;

public class MixedSqlNode implements SqlNode {

    private List<SqlNode> sqlNodes;

    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes = sqlNodes;
    }

    /**
     * 对外提供对封装数据的操作
     * @param dynamicContext
     */
    @Override
    public void apply(DynamicContext dynamicContext) {
        for (SqlNode sqlNode : sqlNodes) {
            sqlNode.apply(dynamicContext);
        }
    }
}
