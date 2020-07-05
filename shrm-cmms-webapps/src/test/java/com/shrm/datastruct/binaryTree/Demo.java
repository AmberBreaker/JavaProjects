package com.shrm.datastruct.binaryTree;

import com.alibaba.fastjson.JSON;
import com.shrm.datastruct.binaryTree.BinaryTreeFactory.BinaryTree;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

public class Demo {

    private BinaryTree getBinaryTree() {
        LinkedList<BinaryTreeData> testDataList = CreateDataFactory.getTestData();
        return BinaryTreeFactory.getInstance().createBinaryTree(testDataList);
    }

    @Test
    public void postSearchByStack() {

        Stack<BinaryTree> stack = new Stack<>();

        LinkedList<BinaryTree> binaryTreeList = new LinkedList<>();
        binaryTreeList.add(getBinaryTree());

        while (binaryTreeList.size() > 0 || !stack.isEmpty()) {
            while (binaryTreeList.size() > 0) {
                BinaryTree binaryTree = binaryTreeList.removeFirst();
                stack.push(binaryTree);
                System.out.println(binaryTree.getCode() + " - " + binaryTree.getName());
                binaryTreeList = binaryTree.getChildList();
            }
        }

    }

    @Test
    public void test() {
        getBinaryTree();
    }
}
