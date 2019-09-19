package com.shrm.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class WatermarkUtils {

    /**
     * 为图片添加水印
     * @param srcUrl 源图片地址
     * @param targetUrl 目标图片地址
     * @param width 宽
     * @param height 高
     * @param num 质量比例如 0.8
     * @param pos 显示位置:  Positions.BOTTOM_RIGHT
     * @throws IOException
     */
    public static void watermark(String srcUrl, String targetUrl, int width, int height, float num, Positions pos) throws IOException {
        Thumbnails.of(srcUrl)
                .size(width, height)
                .watermark(pos, ImageIO.read(new File(targetUrl)), num)
                .outputQuality(num)
                .toFile(targetUrl);
    }

}
