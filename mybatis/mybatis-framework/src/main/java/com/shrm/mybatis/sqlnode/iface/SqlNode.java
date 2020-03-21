package com.shrm.mybatis.sqlnode.iface;

import com.shrm.mybatis.sqlnode.DynamicContext;

/**
 * 1、封装SQL节点的信息
 * 2、提供对封装对SQL节点的解析功能
 */
public interface SqlNode {

    /**
     * 解析功能。
     * 最终解析出来的SQL语句，封装到DynamicContext中的StringBuffer对象中。
     * 解析的过程中，可能要用到入参对象。
     * 此时解析出来的SQL语句，只是根据动态标签的逻辑完成了字符串的拼接，还未被解析。
     */
    void apply(DynamicContext dynamicContext);
}
