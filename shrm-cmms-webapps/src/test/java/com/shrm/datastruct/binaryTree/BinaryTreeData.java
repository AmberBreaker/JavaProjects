package com.shrm.datastruct.binaryTree;

/**
 *
 */
public class BinaryTreeData {

    private int uuid;

    private String conditionNo;

    private String conditionCode;

    private String conditionDesc;

    private int idx;

    public int getUuid() {
        return uuid;
    }

    void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getConditionNo() {
        return conditionNo;
    }

    @SuppressWarnings("SameParameterValue")
    void setConditionNo(String conditionNo) {
        this.conditionNo = conditionNo;
    }

    String getConditionCode() {
        return conditionCode;
    }

    void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    String getConditionDesc() {
        return conditionDesc;
    }

    void setConditionDesc(String conditionDesc) {
        this.conditionDesc = conditionDesc;
    }

    public int getIdx() {
        return idx;
    }

    void setIdx(int idx) {
        this.idx = idx;
    }

}
