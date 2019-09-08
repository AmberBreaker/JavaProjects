package com.shrm.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    public static InputStream streamImg(Object logoPic) throws IOException {
        if (logoPic instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) logoPic;
            InputStream stream = file.getInputStream();
            return stream;
        }
        return null;
    }
}
