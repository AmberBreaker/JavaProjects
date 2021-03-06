package com.shrm.service;

import com.shrm.utils.DateUtils;
import com.shrm.utils.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FFMPEG {

    public static String dealString(String str) {
        Matcher m = java.util.regex.Pattern.compile("^frame=.*" ).matcher(str);
        String msg = "";
        while (m.find()) {
            msg = m.group();
        }
        return msg;
    }

    /**
     * 如果是数字就是成功的时间(秒数)
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 获取视频格式(转码前的格式和转码后的格式都可以调用)
     * @param outputPath
     * @return
     */
    public static String returnVideoFormat(String outputPath){
        return outputPath.substring(outputPath.lastIndexOf(".")+1);
    }

    /**
     * @Param dto 参数传递对象
     * dto中包含的参数:
     * (必填)1.ffmpeg_path: ffmpeg执行文件地址,如 d:\\ffmpeg\\ffmpeg.exe Linux下直接调用ffmpeg命令(当然你事先已经有这个程序了)
     * (必填)2.input_path:原视频路径
     * (必填)3.video_converted_path:转换后视频输出路径<br>
     * (可选)4.screen_size:视频尺寸 长度乘宽度 乘号用英文小写"x"如 512x480<br>
     * (可选)5.logo:水印地址(其实在ffmpeg中有一个专门的watermark参数,logo跟它有何不同,我还没看,不过对我来说效果一样 貌似需要png图片才行)<br>
     * (可选,如果填写必须有logo才行,默认为0)6.xaxis:水印logo的横坐标(只有logo参数为一个正确路径才行) 比如0<br>
     * (可选,如果填写必须有logo才行,默认为0)6.yaxis:水印logo的纵坐标(只有logo参数为一个正确路径才行) 比如0<br>
     * (可选)vb:视频比特率,传入一个数值,单位在程序里面拼接了k
     * (可选)ab:音频比特率,传入一个数值,单位在程序里面拼接了k
     */
    public String videoTransfer(final Map<String, String> dto) {
        List<String> cmd = new ArrayList<String>();
        cmd.add(dto.get("ffmpeg_path"));
        cmd.add("-y");
        cmd.add("-i");
        cmd.add(dto.get("input_path"));
        if (null != dto.get("screen_size")) {
            cmd.add("-s");
            cmd.add(dto.get("screen_size"));
        }
        if (null != dto.get("logo")) {
            String logo = dto.get("logo");
            cmd.add("-vf");
            String xaxis = StringUtils.isEmpty(dto.get("xaxis")) ? "0" : dto.get("xaxis");
            String yaxis = StringUtils.isEmpty(dto.get("yaxis")) ? "0" : dto.get("yaxis");

            String logoString = "movie=" + logo + "[logo],[in][logo]overlay=x="
                    + xaxis + ":y=" + yaxis + "[out]";
            cmd.add(logoString);
        }
        cmd.add("-strict");
        cmd.add("-2");
        if (!StringUtils.isEmpty(dto.get("vb"))) {
            cmd.add("-vb");
            cmd.add(dto.get("vb") + "k");
        }

        if (!StringUtils.isEmpty(dto.get("ab"))) {
            cmd.add("-ab");
            cmd.add(dto.get("ab") + "k");
        }
        cmd.add("-q:v");
        cmd.add("4");
        cmd.add(dto.get("video_converted_path"));

        String converted_time = CmdExecutor.exec(cmd);
        return DateUtils.returnSecond(converted_time); // 返还转换时间
    }

}

