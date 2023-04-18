package com.dooby.million_data_import_and_export_demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author Dooby Kim
 * @Date 2023/4/18 10:26 下午
 * @Version 1.0
 */
@Service
@Slf4j
public class FileService {

    @Async
    public void importFile() {
        log.info("import file");
    }


    @Async
    public void exportFile() {
        log.info("export file");
    }
}
