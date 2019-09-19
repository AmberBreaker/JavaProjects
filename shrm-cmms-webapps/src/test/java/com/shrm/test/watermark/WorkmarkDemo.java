package com.shrm.test.watermark;

import com.shrm.utils.WatermarkUtils;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WorkmarkDemo {

    @Test
    public void test1(){
//        String srcUrl = "/home/images/tmp/green.jpg";
//        String targetUrl = "/home/images/dir/write.jpg";
        String targetUrl = "/home/images/tmp/green.jpg";
        String srcUrl = "/home/images/dir/write.jpg";
        int width = 100;
        int height = 100;
        float num = 0.8f;
        Positions pos = Positions.BOTTOM_RIGHT;
        try {
            WatermarkUtils.watermark(srcUrl, targetUrl, width, height, num, pos);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File file = new File("/home/images/tmp/267A8D74FFF88D859DB84A0A9F23DA74.png");
//        BufferedImage read = null;
//        try {
//            read = ImageIO.read(file);
//
//            File f = new File("/home/images/dir/267A8D74FFF88D859DB84A0A9F23DA74.png");
//            ImageIO.write(read, "png", f);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            read.flush();
//        }
    }

}
