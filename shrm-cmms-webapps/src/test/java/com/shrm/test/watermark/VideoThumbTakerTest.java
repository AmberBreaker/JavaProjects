package com.shrm.test.watermark;

import com.shrm.service.FFMPEG;
import com.shrm.service.VideoThumbTaker;
import org.junit.Test;

import java.util.HashMap;

public class VideoThumbTakerTest {

    @Test
    public void testGetThumb() {

        VideoThumbTaker videoThumbTaker = new VideoThumbTaker("/home/Tools/ffmpeg/ffmpeg");

        try {
            videoThumbTaker.getThumb(
                "E:\\迅雷下载\\ffmpeg\\input\\gg.mp4",
                "G:\\thumbTest.jpg",
                800,
                600,
                0,
                0,
                11f
            );
            System.out.println("截图完成...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 视频添加水印
     */
    @Test
    public void videoTransferTest() {

        HashMap<String, String> dto = new HashMap<String, String>();
        dto.put("ffmpeg_path", "/home/Tools/ffmpeg/ffmpeg"); // ffmpeg安装路径
        dto.put("input_path", "/home/video/tmp/test.mp4"); // 待添加水印视频
        dto.put("video_converted_path", "/Users/blairscott/Desktop/test.mp4"); // 添加水印后视频存放
        dto.put("logo", "/home/video/watermark/watermark.jpg"); // 水印图片

        String secondsString = new FFMPEG().videoTransfer(dto);
        System.out.println("转换共用: " + secondsString + "秒");
    }

    /**
     * TODO: 线程传参问题
     * 失败，调用不了 new FFMPEG().videoTransfer(dto);
     * 发现问题：dto为Map类型，run()方法调用的过程中只要出现了 List/Map就不能执行
     * 数组可以成功执行
     */
    @Test
    public void test() {

        System.out.println("调用开始");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> dto = new HashMap<String, String>();
                dto.put("ffmpeg_path", "/home/Tools/ffmpeg/ffmpeg"); // ffmpeg安装路径
                dto.put("input_path", "/home/video/tmp/test.mp4"); // 待添加水印视频
                dto.put("video_converted_path", "/Users/blairscott/Desktop/test.mp4"); // 添加水印后视频存放
                dto.put("logo", "/home/video/watermark/watermark.jpg"); // 水印图片
                System.out.println("转化开始");
                new FFMPEG().videoTransfer(dto);
                System.out.println("转化结束");
            }
        }).start();

        System.out.println("调用结束");

    }

}
