package com.dooby.million_data_import_and_export_demo.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author Dooby Kim
 * @Date 2023/4/28 9:26 上午
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private Boolean success;
    private String message;
    private T data;

    public static <T> Result<T> ok(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> ok(String message) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setSuccess(false);
        result.setMessage("操作失败");
        return result;
    }
}
