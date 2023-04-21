package com.dooby.million_data_import_and_export_demo;

import com.alibaba.excel.EasyExcel;
import com.dooby.million_data_import_and_export_demo.entity.DemoData;
import com.dooby.million_data_import_and_export_demo.listener.DemoDataListener;
import com.dooby.million_data_import_and_export_demo.utils.PathUtil;
import org.junit.jupiter.api.Test;

import javax.servlet.ReadListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author Dooby Kim
 * @Date 2023/4/19 6:56 下午
 * @Version 1.0
 */
public class EasyExcelTest {


    //@Test
    public void simpleRead() {
        String fileName = PathUtil.getTempPath() + "demo.xlsx";
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    //@Test
    public void simpleWrite() {
        String fileName = PathUtil.getTempPath() + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(new File(fileName), DemoData.class).sheet("测试").doWrite(this::createData);
    }

    private List<DemoData> createData() {
        DemoData d1 = DemoData.builder().userId(1).userName("Jack").birthday(new Date()).build();
        DemoData d2 = DemoData.builder().userId(2).userName("Tom").birthday(new Date()).build();
        DemoData d3 = DemoData.builder().userId(3).userName("Gin").birthday(new Date()).build();
        return List.of(d1, d2, d3);
    }
}
