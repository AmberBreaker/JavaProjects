package com.shrm.mybatis.sqlsource.iface;

import com.shrm.mybatis.mapping.BoundSql;

/**
 * 1、实现类要封装未解析的SQL信息。
 * 2、对未解析对SQL信息提供解析的功能。
 */
public interface SqlSource {

    /**
     * String username = "王五"
     * statement的使用方式，也就是动态拼接SQL语句
     *  "select * from users where username = " + username;
     *
     * "select * from users where username = ? and sex = ?";
     * preparedStatement.setString(1, "王五"); // 【王五】得去入参对象username属性中获取。
     * preparedStatement.setString(2, "男"); // 【男】得去入参对象sex属性中获取。
     */
    BoundSql getBoundSql(Object param);

}