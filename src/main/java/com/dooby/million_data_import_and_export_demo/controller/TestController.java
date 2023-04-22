package com.dooby.million_data_import_and_export_demo.controller;

import com.dooby.million_data_import_and_export_demo.service.impl.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Dooby Kim
 * @Date 2023/4/18 11:20 下午
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    private FileService fileService;

    @GetMapping("/test")
    public String test() {
        fileService.exportFile();
        return "success";
    }
}
