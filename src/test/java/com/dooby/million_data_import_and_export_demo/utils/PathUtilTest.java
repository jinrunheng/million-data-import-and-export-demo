package com.dooby.million_data_import_and_export_demo.utils;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Dooby Kim
 * @Date 2023/4/19 7:23 下午
 * @Version 1.0
 */
class PathUtilTest {

    @Test
    void getCurrentPath() {
        String fileName = PathUtil.getCurrentPath() + "/temp/" + System.currentTimeMillis() + ".xlsx";
        System.out.println(fileName);
    }
}