package com.dooby.million_data_import_and_export_demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Dooby Kim
 * @Date 2023/4/28 9:41 上午
 * @Version 1.0
 */
@Slf4j
public class JSONUtil {

    private JSONUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * 将 Java 对象序列化为 JSON 字符串
     *
     * @return
     */
    public static String objectToJson(Object obj) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 将 JSON 字符串反序列化为 Java 对象
     *
     * @param jsonStr
     * @return
     */
    public static Object jsonToObject(String jsonStr, Class<?> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            log.error("error", e);
            throw new RuntimeException(e);
        }
    }
}
