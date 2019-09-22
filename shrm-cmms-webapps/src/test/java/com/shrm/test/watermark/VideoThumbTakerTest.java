package com.shrm.test.watermark;

import com.shrm.service.FFMPEG;
import com.shrm.service.VideoThumbTaker;
import com.shrm.task.VedioTransferTask;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class VideoThumbTakerTest {

    @Test
    public void testGetThumb() {

        VideoThumbTaker videoThumbTaker = new VideoThumbTaker("/home/Tools/ffmpeg/ffmpeg");

        try {
            videoThumbTaker.getThumb(
                "/home/video/tmp/test.mp4",
                "/Users/blairscott/Desktop/test.png",
                200,
                200,
                0,
                0,
                5f
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
     * 失败，调用不了 new FFMPEG().videoTransfer(dto);
     * 发现问题：dto为Map类型，run()方法调用的过程中只要出现了 List/Map就不能执行
     * 数组可以成功执行
     */
    @Test
    public void videoTransferTest2() {

        System.out.println("调用开始");

        new Thread(new Runnable() {
            @Override
            public void run() {
                final HashMap<String, String> dto = new HashMap<String, String>();
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

    /**
     * 新建线程可以解决 videoTransferTest2 遇到的问题
     */
    public void videoTransferTest3() {
        System.out.println("开始调用");
        Map<String, String> dto = new HashMap<String, String>();
        dto.put("ffmpeg_path", "/home/Tools/ffmpeg/ffmpeg"); // ffmpeg安装路径
        dto.put("input_path", "/home/video/tmp/test.mp4"); // 待添加水印视频
        dto.put("video_converted_path", "/Users/blairscott/Desktop/test.mp4"); // 添加水印后视频存放
        dto.put("logo", "/home/video/watermark/watermark.jpg"); // 水印图片

        VedioTransferTask task = new VedioTransferTask(dto);
        task.run();
        System.out.println("结束调用");

    }

}


