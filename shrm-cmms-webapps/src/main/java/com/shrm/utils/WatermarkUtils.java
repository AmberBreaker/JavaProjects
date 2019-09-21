package com.shrm.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WatermarkUtils {

    /**
     * 为图片添加水印
     * @param srcUrl 待添加水印的图片地址
     * @param targetUrl 水印图片地址
     * @param num 透明度 0.8
     * @param pos 显示位置:  Positions.BOTTOM_RIGHT
     * @throws IOException
     */
    public static void watermark(String srcUrl, String targetUrl, float num, Positions pos) throws IOException {
        Thumbnails.of(srcUrl)
                .watermark(pos, ImageIO.read(new File(targetUrl)), num) // 水印图片
                .outputQuality(1f) // 输出图片质量
                .toFile(srcUrl);
    }

    /**
     * 获取视频帧
     * @param videofile 视频文件路径
     * @param framefile 生成图片文件路径
     * @throws IOException
     */
    public static void fetchFrame(String videofile, String framefile) throws IOException {
        long start = System.currentTimeMillis();
        File targetFile = new File(framefile);
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videofile);

        ff.start();

        int length = ff.getLengthInFrames();

        int i = 0;

        Frame f = null;

        while (i < length) {
            // 过滤前100帧
            f = ff.grabFrame();
            if ((i > 100) && (f.image != null)) {
                break;
            }
            i++;
        }

        int owidth = f.imageWidth;
        int oheight = f.imageHeight;
        // 对截取的帧进行等比例缩放
        int width = 300;
        int height = (int) (((double) width / owidth) * oheight);
        Java2DFrameConverter converter = new Java2DFrameConverter();

        BufferedImage fecthedImage = converter.getBufferedImage(f);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        bi.getGraphics().drawImage(fecthedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                0, 0, null);

        ImageIO.write(bi, "jpg", targetFile);
        ff.stop();
    }
}
