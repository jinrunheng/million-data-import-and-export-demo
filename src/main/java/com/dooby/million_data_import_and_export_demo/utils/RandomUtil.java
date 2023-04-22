package com.dooby.million_data_import_and_export_demo.utils;

import java.util.Random;

/**
 * @Author Dooby Kim
 * @Date 2023/4/22 12:33 下午
 * @Version 1.0
 */
public class RandomUtil {

    private RandomUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * 生成 [from...to] 范围的随机整数
     *
     * @param from
     * @param to
     * @return
     */
    public static int getRandom(int from, int to) {
        return new Random().nextInt(to - from + 1) + from;
    }
}
