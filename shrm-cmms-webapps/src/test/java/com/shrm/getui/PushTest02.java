package com.shrm.getui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;


//͸��ģ��--����
public class PushTest02 {
 
	 private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
 
//	��͸��ģ�塿 --����
//	����
//	͸����Ϣ��ָ��Ϣ���ݵ��ͻ���ֻ����Ϣ���ݣ�չ����ʽ�ɿͻ������ж��塣�ͻ��˿��Զ���֪ͨ��չ����ʽ��Ҳ���Զ���֪ͨ����֮��Ķ��������߲����κ�չ�֡���
	@Test
	public void test02() {

		// STEP1��a��ȡӦ�û�����Ϣ
		// ���ư�������
		String appId = "4vKVMbaS2P9FkfC67qqLW3";
		String appKey = "PBoYPTwolv9gcOct25JIU";
		String masterSecret = "LcoYHObGS087tEKouamXy"; // ����Կ
		String clientId = "f1ef4a623915a5c3b860be88e64242f8";

		// ȫ����
		String appId1 = "FKikTtUHcm9TFPP978vo87";
		String appKey1 = "UCgmZXwiPC61Mv7iVmtWF7";
		String masterSecret1 = "ikumrNQVJT6dhnl0PAwDw"; // ����Կ

		geTuiDemo(appId, appKey, masterSecret);
		QuanMin(appId1, appKey1, masterSecret1);

	}
	
	@SuppressWarnings("deprecation")
	private void QuanMin(String appId1, String appKey1, String masterSecret1) {
		IGtPush push = new IGtPush(url, appKey1, masterSecret1);
		
	    // STEP1��ѡ��ģ��
		TransmissionTemplate template = new TransmissionTemplate();
	     
	    // ����APPID��APPKEY
	    template.setAppId(appId1);
	    template.setAppkey(appKey1);
	    template.setTransmissionType(1);
	    String date = (Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+" "+
	                   Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+"";
	    template.setTransmissionContent("����͸����Ϣ������  "+date);
	 
	    // STEP3������������������
	    AppMessage message = new AppMessage();
	    message.setData(template);
	    List<String> appIdList = new ArrayList<String>();
	    appIdList.add(appId1);
	    message.setAppIdList(appIdList);
	    // STEP4��ִ������
	    IPushResult pushResult = push.pushMessageToApp(message);
	    System.out.println("ȫ��---"+pushResult.getResultCode());
	    System.out.println("ȫ��---"+pushResult.toString());
		
	}

	private void geTuiDemo(String appId, String appKey, String masterSecret) {

		IGtPush push = new IGtPush(url, appKey, masterSecret);
		
	    // STEP1��ѡ��ģ��
		TransmissionTemplate template = new TransmissionTemplate();
	     
	    // ����APPID��APPKEY
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    template.setTransmissionType(1);
	    String date = (Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+" "+
	                   Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+Calendar.getInstance().get(Calendar.MINUTE)+"";
	    template.setTransmissionContent("����͸����Ϣ������  "+date);
	 
	    // STEP3������������������
	    AppMessage message = new AppMessage();
	    message.setData(template);
	    List<String> appIdList = new ArrayList<String>();
	    appIdList.add(appId);
	    message.setAppIdList(appIdList);
	    // STEP4��ִ������
	    IPushResult pushResult = push.pushMessageToApp(message);
	    System.out.println("����---"+pushResult.getResultCode());
	    System.out.println("����---"+pushResult.toString());
   }
}
