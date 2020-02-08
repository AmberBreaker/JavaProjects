package com.shrm.test.watermark;

import com.shrm.utils.WatermarkUtils;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class WorkmarkDemo {

    @Test
    public void test1(){
        String srcUrl = "/home/image/terraces.jpg"; // 待添加水印的图片
        String targetUrl = "/home/image/watermark/watermark.jpg"; // 水印图片

        try {
            targetUrl = URLDecoder.decode(targetUrl, "utf-8");
            srcUrl = URLDecoder.decode(srcUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        float num = 0.3f;
        Positions pos = Positions.BOTTOM_RIGHT;
        try {
            WatermarkUtils.watermark(srcUrl, targetUrl, num, pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2() {
        String vediofile = "/home/video/test.avi";
        String framefile = "/home/image/test.jpg";
        try {
            WatermarkUtils.fetchFrame(vediofile, framefile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        char c1 = 'a';
        char c2 = 'b';
        System.out.println(c1 + c2);
    }
}
