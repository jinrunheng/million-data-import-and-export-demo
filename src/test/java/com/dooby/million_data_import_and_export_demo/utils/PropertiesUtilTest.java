package com.dooby.million_data_import_and_export_demo.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Dooby Kim
 * @Date 2023/4/20 5:39 下午
 * @Version 1.0
 */
class PropertiesUtilTest {

    @Test
    void getAliyunSecretId() {
        System.out.println(PropertiesUtil.getAliyunSecretId());
    }

    @Test
    void getAliyunSecretKey() {
        System.out.println(PropertiesUtil.getAliyunSecretKey());
    }
}