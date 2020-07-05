package com.shrm.datastruct.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BinaryTreeFactory {

    private static BinaryTreeFactory binaryTreeFactory = null;

    private BinaryTreeFactory(){}

    static BinaryTreeFactory getInstance() {
        if (binaryTreeFactory == null) {
            binaryTreeFactory = new BinaryTreeFactory();
        }
        return binaryTreeFactory;
    }

    BinaryTree createBinaryTree(LinkedList<BinaryTreeData> dataList) {
        if (dataList == null || dataList.size() == 0) {
            return null;
        }
        BinaryTree binaryTree = null;
        BinaryTreeData treeData = dataList.removeFirst();
        if (treeData.getConditionCode() != null) {
            binaryTree = new BinaryTree(treeData);
            while (true) {
                BinaryTree childTree = createBinaryTree(dataList);
                if (childTree == null) {
                    break;
                }
                binaryTree.addChildList(childTree);
            }
        }
        return binaryTree;
    }

    static class BinaryTree {

        private String code;

        private String name;

        private LinkedList<BinaryTree> childList;

        private BinaryTree(BinaryTreeData data) {
            this.code = data.getConditionCode();
            this.name = data.getConditionDesc();
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        private void addChildList(BinaryTree childTree) {
            if (childList == null) {
                childList = new LinkedList<>();
            }
            this.childList.add(childTree);
        }

        public LinkedList<BinaryTree> getChildList() {
            return childList;
        }
    }

}
