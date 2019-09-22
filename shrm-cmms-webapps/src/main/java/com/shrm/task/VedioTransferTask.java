package com.shrm.task;

import com.shrm.service.FFMPEG;

import java.util.HashMap;
import java.util.Map;

public class VedioTransferTask implements Runnable {

    Map<String, String> param;

    public VedioTransferTask (Map<String, String> param) {
        this.param = param;
    }

    @Override
    public void run() {
        System.out.println("添加水印 -- 开始");
        new FFMPEG().videoTransfer(param);
        System.out.println("添加水印 -- 结束");
    }
}
