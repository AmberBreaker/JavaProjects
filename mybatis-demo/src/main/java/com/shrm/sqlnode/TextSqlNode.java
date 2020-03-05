package com.shrm.sqlnode;

import com.shrm.sqlnode.iface.SqlNode;

/**
 * 封装的是带有$()的文本字符串
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    @Override
    public void apply(DynamicContext dynamicContext) {

    }

    /**
     * 判断保存数据是否动态
     */
    public boolean isDynamic() {
        if (sqlText.indexOf("${") > -1) {
            return true;
        }
        return false;
    }
}
