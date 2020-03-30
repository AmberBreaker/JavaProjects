package com.shrm.mybatis.handler.iface;

import java.sql.Statement;

public interface ParameterHandler {

    void handleParameter(Statement statement, Object param);
}
