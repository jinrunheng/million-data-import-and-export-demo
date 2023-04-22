package com.dooby.million_data_import_and_export_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dooby.million_data_import_and_export_demo.mapper")
public class MillionDataImportAndExportDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MillionDataImportAndExportDemoApplication.class, args);
    }

}
