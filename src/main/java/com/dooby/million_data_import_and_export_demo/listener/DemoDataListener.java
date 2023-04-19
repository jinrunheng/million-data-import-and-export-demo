package com.dooby.million_data_import_and_export_demo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.dooby.million_data_import_and_export_demo.entity.DemoData;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author Dooby Kim
 * @Date 2023/4/19 7:48 下午
 * @Version 1.0
 */
@Slf4j
public class DemoDataListener implements ReadListener<DemoData> {

    /**
     * 每 1 条数据存储到数据库中
     */
    private static final int BATCH_COUNT = 1;

    /**
     * 缓存的数据
     */
    private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // print
            log.info(data.toString());
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        log.info("over");
    }

    private void saveData() {
        log.info("save data to db");
    }
}
