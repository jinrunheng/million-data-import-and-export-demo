package com.dooby.million_data_import_and_export_demo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Dooby Kim
 * @Date 2023/4/22 12:39 下午
 * @Version 1.0
 */
class RandomUtilTest {

    @Test
    void getRandom() {
        for (int i = 0; i < 100; i++) {
            int random = RandomUtil.getRandom(3, 10);
            Assertions.assertTrue(random >= 3 && random <= 10);
        }
    }
}