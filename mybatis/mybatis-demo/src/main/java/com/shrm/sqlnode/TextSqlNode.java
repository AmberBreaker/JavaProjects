package com.shrm.sqlnode;

import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.mybatis.utils.GenericTokenParser;
import com.shrm.mybatis.utils.OgnlUtils;
import com.shrm.mybatis.utils.SimpleTypeRegistry;
import com.shrm.mybatis.utils.TokenHandler;

/**
 * 封装的是带有$()的文本字符串
 */
public class TextSqlNode implements SqlNode {

    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    // 处理${}
    @Override
    public void apply(DynamicContext context) {
        BindingTokenParser handler = new BindingTokenParser(context);
        GenericTokenParser parser = new GenericTokenParser("${", "}", handler);
        String sql = parser.parse(this.sqlText);
        context.appendSql(sql);
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

    private static class BindingTokenParser implements TokenHandler {

        private DynamicContext context;

        public BindingTokenParser(DynamicContext context) {
            this.context = context;

        }

        /**
         * expression：比如说${username}，那么expression就是username username也就是Ognl表达式
         */
        @Override
        public String handleToken(String expression) {
            Object paramObject = context.getBinding().get("_parameter");
            if (paramObject == null) {
                // context.getBindings().put("value", null);
                return "";
            } else if (SimpleTypeRegistry.isSimpleType(paramObject.getClass())) {
                // context.getBindings().put("value", paramObject);
                return String.valueOf(paramObject);
            }
            Object value = OgnlUtils.getValue(expression, paramObject);
            return value == null ? "" : String.valueOf(value);
        }
    }
}
