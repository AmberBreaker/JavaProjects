package com.shrm.mybatis.builder;

import com.shrm.mybatis.sqlnode.IfSqlNode;
import com.shrm.mybatis.sqlnode.MixedSqlNode;
import com.shrm.mybatis.sqlnode.StaticTextSqlNode;
import com.shrm.mybatis.sqlnode.TextSqlNode;
import com.shrm.mybatis.sqlnode.iface.SqlNode;
import com.shrm.mybatis.sqlsource.DynamicSqlSource;
import com.shrm.mybatis.sqlsource.RawSqlSource;
import com.shrm.mybatis.sqlsource.iface.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.List;

public class XMLScriptBuilder {

    private boolean isDynamic;

    public SqlSource parseScriptNode(Element selectElement) {
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
}
