package com.shrm.getui;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;

//通知模板-提醒用户更新
public class pushTest01 {
    
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	@Test
	public void test01() {		
		// STEP1：获取应用基本信息
		// 个推案例测试
	    String appId = "4vKVMbaS2P9FkfC67qqLW3";
	    String appKey = "PBoYPTwolv9gcOct25JIU";
	    String masterSecret = "LcoYHObGS087tEKouamXy"; // 主密钥
	    String clientId = "f1ef4a623915a5c3b860be88e64242f8";
		
		//全民工作TransmissionTemplate template = new TransmissionTemplate();
	    String appId1 = "FKikTtUHcm9TFPP978vo87"; 
	    String appKey1 = "UCgmZXwiPC61Mv7iVmtWF7";
	    String masterSecret1 = "ikumrNQVJT6dhnl0PAwDw"; // 主密钥
	    
	    geTuiDemo(appId,appKey,masterSecret);
	    QuanMin(appId1,appKey1,masterSecret1);
	    
		
	}
	private void QuanMin(String appId1, String appKey1, String masterSecret1) {
		IGtPush push = new IGtPush(url, appKey1, masterSecret1);
		NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId1);
        template.setAppkey(appKey1);
        //通知栏标题与内容
        template.setTitle("通知标题");
        template.setText("通知内容");
        //设置通知标题与内容
        template.setLogoUrl("");
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        //1强制启动应用 2等待应用开启
        template.setTransmissionType(1);
        template.setTransmissionContent("透传内容");
        // STEP3：设置推送其他参数
        AppMessage message = new AppMessage();
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId1);
        message.setAppIdList(appIdList);
        message.setData(template);
        IPushResult pushResult = push.pushMessageToApp(message);
        System.out.println("QuanMin--"+pushResult.getResultCode());
        System.out.println("QuanMin--"+pushResult.getResponse().toString());		
	}
	private void geTuiDemo(String appId, String appKey, String masterSecret) {
		IGtPush push = new IGtPush(url, appKey, masterSecret);
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        //通知栏标题与内容
        template.setTitle("通知标题");
        template.setText("通知内容");
        //设置通知标题与内容
        template.setLogoUrl("");
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        //1强制启动应用 2等待应用开启
        template.setTransmissionType(1);
        template.setTransmissionContent("透传内容");
        // STEP3：设置推送其他参数
        AppMessage message = new AppMessage();
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);
        message.setData(template);
        IPushResult pushResult = push.pushMessageToApp(message);
        System.out.println("GeTui--"+pushResult.getResultCode());
        System.out.println("GeTui--"+pushResult.getResponse().toString());		
	}
}
