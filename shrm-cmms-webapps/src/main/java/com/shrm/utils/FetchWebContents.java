package com.shrm.utils;

import org.apache.commons.io.FileUtils;

import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class FetchWebContents {



    /**
     * 通过网页URL获取源码
     * @param getUrl
     */
    public static void catchHtmlCode(String getUrl){

        String filePath = "D:/test/test.html";
        BufferedReader buffreader = null;
        BufferedWriter writer = null;
        try {
            URL u = new URL(getUrl);
            URLConnection connection = u.openConnection();
            InputStream inputStream = connection.getInputStream();

            buffreader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer buff = new StringBuffer();
            String line;
            while (null != (line = buffreader.readLine())) {
                buff.append(line);
            }
            String html = buff.toString();
            Document doc = Jsoup.parse(html);
            Element body = doc.body();
            Element div = body.select(".head_wrapper").first();
            /*替换指定的html标签内容*/
            div.html("<h1 style='font-size:30px'>我已将指定的html标签内容替换为空</h3>");

            writer = new BufferedWriter(new FileWriter(filePath, false));
            writer.write(doc.html());
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            FileUtils.closeStream(writer);
        }
    }
}
