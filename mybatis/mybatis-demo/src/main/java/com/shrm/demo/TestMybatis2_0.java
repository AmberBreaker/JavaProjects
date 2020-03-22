package com.shrm.demo;

import com.shrm.config.Configuration;
import com.shrm.config.MappedStatement;
import com.shrm.pojo.User;
import com.shrm.sqlnode.IfSqlNode;
import com.shrm.sqlnode.MixedSqlNode;
import com.shrm.sqlnode.StaticTextSqlNode;
import com.shrm.sqlnode.TextSqlNode;
import com.shrm.sqlnode.iface.SqlNode;
import com.shrm.sqlsource.BoundSql;
import com.shrm.sqlsource.DynamicSqlSource;
import com.shrm.sqlsource.ParameterMapping;
import com.shrm.sqlsource.RawSqlSource;
import com.shrm.sqlsource.iface.SqlSource;
import com.shrm.mybatis.utils.DocumentUtils;
import com.shrm.mybatis.utils.SimpleTypeRegistry;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 目的是使用XML来表达mybatis的全局配置信息和业务相关的映射信息。
 * 其次优化数据连接的创建（使用连接池）。
 */
public class TestMybatis2_0 {

    private Configuration configuration = new Configuration();

    private String namespace;

    private boolean isDynamic;

    public List<Object> queryByJDBC(Configuration configuration, String statementId, Object param) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /*
             * 1、获取Connection。
             * 此处使用DataSource去优化Connection的获取。
             * DataSource是通过XML配置来产生的。
             * XML信息是通过Configuration对象保存的。
             */
            connection = getConnection(configuration);

            /*
             * 2、获取JDBC可执行的SQL语句。
             * 【SQL信息】是配置在映射文件中的，是通过【select等statement】标签来配置的。
             * 不同脚本的【SQL信息】是封装到不同类型的【SqlNode】对象中的。
             * 【SqlNode】信息是保存到【SqlSource】中的。
             * 【SqlSource】的信息是封装到【MappedStatement】对象中的。
             * 【MappedStatement】对象是被保存到【Configuration】对象中的。
             */
            MappedStatement mappedStatement = configuration.getMappedStatementById(statementId);
            if (mappedStatement == null) {
                return null;
            }
            // 获取SqlSource
            SqlSource sqlSource = mappedStatement.getSqlSource();
            // 执行sqlSource去获取Sql信息
            BoundSql boundSql = sqlSource.getBoundSql(param);
            // 获取jdbc可直接执行的sql语句
            String sql = boundSql.getSql();

            /**
             * 3、获取预处理statement
             */
            String statementType = mappedStatement.getStatementType();
            if ("prepared".equals(statementType)) {
                preparedStatement = connection.prepareStatement(sql);

                /**
                 * 4、设置参数
                 */
                handleParameter(preparedStatement, mappedStatement, boundSql, param);

                /**
                 * 5、执行查询，返回结果集
                 */
                resultSet = preparedStatement.executeQuery();

                /**
                 * 6、处理结果集
                 */
                return handleResultSet(resultSet, mappedStatement);
            } else if ("simple".equals(statementType)) {
                // 创建简单的statement对象
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析XML，获取配置文件
     */
    private void loadConfiguration(String location) {

        // 读取指定路径的配置文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        // 根据InputStream流，创建Document对（使用sax解析）
        Document document = DocumentUtils.readDocument(inputStream); // TODO
        parseConfiguration(document.getRootElement());
        // 按照XML标签中的语义去解析

    }

    private void parseConfiguration(Element rootElement) {
        Element environments = rootElement.element("environments");
        parseEnvironments(environments);
        Element mapperElements = rootElement.element("mappers");
        parseMapper(mapperElements);
    }

    private void parseEnvironments(Element environments) {
        String def = environments.attributeValue("default");
        List<Element> elements = environments.elements("environment");
        for (Element envElement : elements) {
            if ("dev".equals(def)) {
                parseEnvironment(envElement);
                break;
            }
        }
    }

    private void parseEnvironment(Element envElement) {
        Element dataSource = envElement.element("datasource");
        parseDataSource(dataSource);
    }

    private void parseDataSource(Element dataSourceElements) {
        String typeValue = dataSourceElements.attributeValue("type");
        if ("DBCP".equals(typeValue)) {
            BasicDataSource dataSource = new BasicDataSource();
            Properties dsProperties = new Properties();
            List<Element> properties = dataSourceElements.elements("property");
            for (Element element : properties) {
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                dsProperties.setProperty(name, value);
            }
            dataSource.setDriverClassName(dsProperties.getProperty("driver"));
            dataSource.setUrl(dsProperties.getProperty("url"));
            dataSource.setUsername(dsProperties.getProperty("username"));
            dataSource.setPassword(dsProperties.getProperty("password"));
            this.configuration.setDataSource(dataSource);
        }
    }

    private void parseMapper(Element mapperElements) {
        List<Element> mappers = mapperElements.elements("mapper");
        Element mapper = mappers.get(0);
        String resource = mapper.attributeValue("resource");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        Document document = DocumentUtils.readDocument(inputStream);
        parseXmlMapper(document.getRootElement());
    }

    private void parseXmlMapper(Element rootElement) {
        this.namespace = rootElement.attributeValue("namespace");
        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements) {
            parseStatementElement(selectElement);
        }
    }

    private void parseStatementElement(Element selectElement) {

        String statementId = this.namespace + "." +selectElement.attributeValue("id");
        Class<?> parameterType = resolveType(selectElement.attributeValue("parameterType")); // TODO
        Class<?> resultType = resolveType(selectElement.attributeValue("resultType"));

        String statementType = selectElement.attributeValue("statementType");
        statementType = statementType == null ? "prepared" : statementType;

        // 解析SQL信。
        SqlSource sqlSource = createSqlSource(selectElement); // TODO
        MappedStatement mappedStatement = new MappedStatement(sqlSource, statementType, statementId, parameterType, resultType); // TODO
        configuration.addMappedStatement(statementId, mappedStatement);
    }

    private SqlSource createSqlSource(Element selectElement) {
        // 去解析select等CURD标签中的SQL脚本信息
        SqlSource sqlSource = parseScriptNode(selectElement);
        return sqlSource;
    }

    private SqlSource parseScriptNode(Element selectElement) {
        // 1、解析出来所有的SqlNode信息
        MixedSqlNode rootSqlNode = parseDynamicTags(selectElement);
        // isDynamic是parseDynamicTags过程中得到的值。
        SqlSource sqlSource;

        // 1、将SqlNode中的信息封装到SqlSource中，并且要根据是否动态节点去选择不同到SqlSource
        // 如果 isDynamic 为true，则说明SqlNode集合信息里包含${}SqlNode节点信息或者元动态标签的节点信息。
        if (this.isDynamic) { // 包含${}或者动态SQL标签
            sqlSource = new DynamicSqlSource(rootSqlNode); // TODO ===================
        } else { //
            sqlSource = new RawSqlSource(rootSqlNode);
        }

        return sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element tagElement) {
        List<SqlNode> sqlNodeList = new ArrayList<>();
        int nodeCount = tagElement.nodeCount();

        // 获取select标签的子标签
        for (int i = 0; i < nodeCount; i++) {
            Node node = tagElement.node(i);
            // 区分是文本标签还是元素标签
            if (node instanceof Text) {
                // 文本
                String text = node.getText();
                TextSqlNode textSqlNode = new TextSqlNode(text);
                if (textSqlNode.isDynamic()) {
                    this.isDynamic = true;
                    sqlNodeList.add(textSqlNode);
                } else {
                    sqlNodeList.add(new StaticTextSqlNode(text));
                }
            } else if (node instanceof Element) {
                // 元素
                this.isDynamic = true;
                // 获取动态标签的标签名称
                String nodeName = node.getName();

                if ("if".equals(nodeName)) {
                    // 封装IfSqlNode信息（test信息、子标签信息）。
                    Element ifNode = (Element) node;
                    String testAttribute = ifNode.attributeValue("test"); // if标签test属性值。
                    MixedSqlNode rootSqlNode = parseDynamicTags(ifNode); // if标签test标签值。

                    // IfSqlNode就是封装if标签信息的。
                    IfSqlNode ifSqlNode = new IfSqlNode(testAttribute, rootSqlNode); // if标签子标签信息。
                    sqlNodeList.add(ifSqlNode);
                }
            }
        }
        return new MixedSqlNode(sqlNodeList);
    }

    private Class<?> resolveType(String parameterTypeStr) {
        try {
            return Class.forName(parameterTypeStr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Connection
     */
    private Connection getConnection(Configuration configuration) throws SQLException {
        DataSource dataSource = configuration.getDataSource();
        return dataSource.getConnection();
    }

    /**
     * 参数处理
     */
    private void handleParameter(PreparedStatement preparedStatement, MappedStatement mappedStatement,
                                 BoundSql boundSql, Object param) throws Exception {
        // 从MappedStatement对象中获取入参类型
        Class<?> parameterTypeClass = mappedStatement.getParameterTypeClass();

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

    }

    /**
     * 处理结果集
     * TODO
     */
    private List<Object> handleResultSet(ResultSet resultSet, MappedStatement mappedStatement) throws
            SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<?> resultTypeClass = mappedStatement.getResultTypeClass();
        List<Object> results = new ArrayList<>();
        while (resultSet.next()) {
            // 利用反射new一个对象
            Object result = resultTypeClass.newInstance();

            // 要获取每一列的值，然后封装到结果对象中对应的属性名称上
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                // 获取🈚️每一列的名称
                String columnName = metaData.getColumnName(i + 1);
                // 获取每一列的值
                Object value = resultSet.getObject(columnName);
                // 列明和属性名要完全一致
                Field field = resultTypeClass.getDeclaredField(columnName);
                field.setAccessible(true);
                // 给映射的对象赋值
                field.set(result, value);
            }
            results.add(result);
        }
        return results;
    }

    @Test
    public void test() {
        // 加载XML配置文件，包括全局配置文件和映射配置文件
        loadConfiguration("config/mybatis-config.xml");
        String statementId = "test.findUserById";

        User user = new User();
//        user.setId(1);
        user.setUsername("王五");
        List<Object> userList = (List<Object>) queryByJDBC(configuration, statementId, user);
        for (Object users : userList) {
            System.out.println(users.toString());
        }
    }
}
