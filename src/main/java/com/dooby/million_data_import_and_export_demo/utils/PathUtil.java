package com.dooby.million_data_import_and_export_demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * @Author Dooby Kim
 * @Date 2023/4/19 6:58 下午
 * @Version 1.0
 */
@Slf4j
public class PathUtil {

    private PathUtil() {
        throw new IllegalStateException("Utility Class");
    }

    public static String getCurrentPath() {
        URL url = PathUtil.class.getResource("/");
        String path = null;
        File file = new File(url.getPath());
        try {
            path = URLDecoder.decode(file.getParent(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("error", e);
        }
        return path;
    }

    public static String getTempPath() {
        String path = getCurrentPath() + "/temp/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }
}
