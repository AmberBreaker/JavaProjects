package com.shrm.mybatis.handler;

import com.shrm.mybatis.handler.iface.ParameterHandler;
import com.shrm.mybatis.mapping.BoundSql;
import com.shrm.mybatis.mapping.MappedStatement;
import com.shrm.mybatis.mapping.ParameterMapping;
import com.shrm.mybatis.utils.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DefaultParameterHandler implements ParameterHandler {

    private MappedStatement mappedStatement;

    private BoundSql boundSql;

    public DefaultParameterHandler(MappedStatement mappedStatement, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
    }

    @Override
    public void handleParameter(Statement statement, Object param) {
        // 从MappedStatement对象中获取入参类型
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();
        try {
            if (SimpleTypeRegistry.isSimpleType(parameterTypeClass)) { // 如果入参是8中基本类型或String类型
                preparedStatement.setObject(1, param);
            } else if (parameterTypeClass == Map.class) { // 如果入参是Map类型
                // TODO

            } else { // 如果入参是POJO类型
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                for (int i = 0; i < parameterMappings.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    // 封装#{}里面的属性名称
                    String name = parameterMapping.getName();

                    // 利用反射，根据属性名称去入参对象中获取指定的属性值
                    Field field = parameterTypeClass.getDeclaredField(name);
                    field.setAccessible(true);
                    Object value = field.get(param);
                    // 设置statement占位符中的值（可以用parameterMap中的type对Object类型的value进行类型处理）
                    preparedStatement.setObject(i + 1, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
