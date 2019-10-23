package com.shrm.test.appPush;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.junit.Test;

import java.util.*;

public class AppPushDemo {

    // STEP1：获取应用基本信息
    private static String appId = "6fRTUXYzQeAFxjilFw3yf8";
    private static String appKey = "jm4M8XSpEg6igFyOgPDOy1";
    private static String masterSecret = "w79Qw4SU8W8gu1wcBv8Jb7"; // 主密钥
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";

    @Test
    public void test() {
        IGtPush iGtPush = new IGtPush(url, appKey, masterSecret);
        Style0 style = new Style0();

        // STEP2：设置推送标题、推送内容。
        style.setTitle("请输入通知栏标题"); // 通知栏标题
        style.setText("请输入通知栏内容"); // 通知栏内容
        style.setLogo("push.png"); // 推送图标

        // STEP3：设置响铃、震动等推送效果
        style.setRing(true); // 设置响铃
        style.setVibrate(true); // 设置震动

        // STEP：选择通知模版
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);

        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<>();
        appIds.add(appId);

        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600); // 单位：ms

        // STEP6：执行推送
        IPushResult ret = iGtPush.pushMessageToApp(message);
        Map<String, Object> response = ret.getResponse();

        for (String key : response.keySet()) {
            System.out.println("key = " + key + ", value = " + response.get(key));
        }
    }
}
