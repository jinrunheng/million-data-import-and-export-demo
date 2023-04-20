package com.dooby.million_data_import_and_export_demo.utils;

/**
 * @Author Dooby Kim
 * @Date 2023/4/20 5:32 下午
 * @Version 1.0
 * @Desc 从系统变量中获取 aliyunSecretId 和 aliyunSecretKey
 */
public class PropertiesUtil {

    public static String getAliyunSecretId() {
        return System.getenv("ALIYUN_SECRET_ID");
    }

    public static String getAliyunSecretKey() {
        return System.getenv("ALIYUN_SECRET_KEY");
    }
}
