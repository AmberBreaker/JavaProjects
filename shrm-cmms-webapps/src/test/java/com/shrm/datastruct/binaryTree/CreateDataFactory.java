package com.shrm.datastruct.binaryTree;

import java.util.LinkedList;

class CreateDataFactory {

    static LinkedList<BinaryTreeData> getTestData() {
        LinkedList<BinaryTreeData> returnList = new LinkedList<>();

        BinaryTreeData data1 = new BinaryTreeData();
        data1.setUuid(1);
        data1.setConditionNo("BCM_REPORT");
        data1.setConditionCode("BCM_REPORT");
        data1.setConditionDesc("客户端报表");
        data1.setIdx(0);

        BinaryTreeData data2 = new BinaryTreeData();
        data2.setUuid(2);
        data2.setConditionNo("BCM_REPORT");
        data2.setConditionCode("APL_CHNL");
        data2.setConditionDesc("进件渠道");
        data2.setIdx(10);

        BinaryTreeData data3 = new BinaryTreeData();
        data3.setUuid(3);
        data3.setConditionNo("BCM_REPORT");
        data3.setConditionCode("offlinepaper");
        data3.setConditionDesc("线下纸质");
        data3.setIdx(20);

        BinaryTreeData data4 = new BinaryTreeData();
        data4.setUuid(4);
        data4.setConditionNo("BCM_REPORT");
        data4.setConditionCode(null);
        data4.setConditionDesc(null);
        data4.setIdx(30);

        BinaryTreeData data5 = new BinaryTreeData();
        data5.setUuid(5);
        data5.setConditionNo("BCM_REPORT");
        data5.setConditionCode("offlinepad");
        data5.setConditionDesc("线下PAD");
        data5.setIdx(40);

        BinaryTreeData data6 = new BinaryTreeData();
        data6.setUuid(6);
        data6.setConditionNo("BCM_REPORT");
        data6.setConditionCode(null);
        data6.setConditionDesc(null);
        data6.setIdx(50);

        BinaryTreeData data7 = new BinaryTreeData();
        data7.setUuid(7);
        data7.setConditionNo("BCM_REPORT");
        data7.setConditionCode("recommender");
        data7.setConditionDesc("见者有份");
        data7.setIdx(60);

        BinaryTreeData data8 = new BinaryTreeData();
        data8.setUuid(8);
        data8.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data8.setIdx(70);

        BinaryTreeData data9 = new BinaryTreeData();
        data9.setUuid(9);
        data9.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data9.setIdx(80);

        BinaryTreeData data10 = new BinaryTreeData();
        data10.setUuid(10);
        data10.setConditionNo("BCM_REPORT");
        data10.setConditionCode("PD_TP");
        data10.setConditionDesc("产品类型");
        data10.setIdx(90);

        BinaryTreeData data11 = new BinaryTreeData();
        data11.setUuid(11);
        data11.setConditionNo("BCM_REPORT");
        data11.setConditionCode("TRADITION");
        data11.setConditionDesc("传统类型");
        data11.setIdx(100);

        BinaryTreeData data12 = new BinaryTreeData();
        data12.setUuid(12);
        data12.setConditionNo("BCM_REPORT");
        data12.setConditionCode("0001");
        data12.setConditionDesc("普卡");
        data12.setIdx(110);

        BinaryTreeData data13 = new BinaryTreeData();
        data13.setUuid(13);
        data13.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data13.setIdx(120);

        BinaryTreeData data14 = new BinaryTreeData();
        data14.setUuid(14);
        data14.setConditionNo("BCM_REPORT");
        data14.setConditionCode("0002");
        data14.setConditionDesc("金卡");
        data14.setIdx(130);

        BinaryTreeData data15 = new BinaryTreeData();
        data15.setUuid(15);
        data15.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data15.setIdx(140);

        BinaryTreeData data16 = new BinaryTreeData();
        data16.setUuid(16);
        data16.setConditionNo("BCM_REPORT");
        data16.setConditionCode("003");
        data16.setConditionDesc("白金卡");
        data16.setIdx(150);

        BinaryTreeData data17 = new BinaryTreeData();
        data17.setUuid(17);
        data17.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data17.setIdx(160);

        BinaryTreeData data18 = new BinaryTreeData();
        data18.setUuid(18);
        data18.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data18.setIdx(170);

        BinaryTreeData data19 = new BinaryTreeData();
        data19.setUuid(19);
        data19.setConditionNo("BCM_REPORT");
        data19.setConditionCode("HIGHYIEDLD");
        data19.setConditionDesc("高收益产品");
        data19.setIdx(180);

        BinaryTreeData data20 = new BinaryTreeData();
        data20.setUuid(20);
        data20.setConditionNo("BCM_REPORT");
        data20.setConditionCode("0101");
        data20.setConditionDesc("通宝分期");
        data20.setIdx(190);

        BinaryTreeData data21 = new BinaryTreeData();
        data21.setUuid(21);
        data21.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data21.setIdx(200);

        BinaryTreeData data22 = new BinaryTreeData();
        data22.setUuid(22);
        data22.setConditionNo("BCM_REPORT");
        data22.setConditionCode("0102");
        data22.setConditionDesc("通宝白领");
        data22.setIdx(210);

        BinaryTreeData data23 = new BinaryTreeData();
        data23.setUuid(23);
        data23.setConditionNo("BCM_REPORT");
        data8.setConditionCode(null);
        data8.setConditionDesc(null);
        data23.setIdx(220);

        BinaryTreeData data24 = new BinaryTreeData();
        data24.setUuid(24);
        data24.setConditionNo("BCM_REPORT");
        data24.setConditionCode("0103");
        data24.setConditionDesc("白金理财");
        data24.setIdx(230);

        returnList.add(data1);
        returnList.add(data2);
        returnList.add(data3);
        returnList.add(data4);
        returnList.add(data5);
        returnList.add(data6);
        returnList.add(data7);
        returnList.add(data8);
        returnList.add(data9);
        returnList.add(data10);
        returnList.add(data11);
        returnList.add(data12);
        returnList.add(data13);
        returnList.add(data14);
        returnList.add(data15);
        returnList.add(data16);
        returnList.add(data17);
        returnList.add(data18);
        returnList.add(data19);
        returnList.add(data20);
        returnList.add(data21);
        returnList.add(data22);
        returnList.add(data23);
        returnList.add(data24);
        return returnList;
    }


}
