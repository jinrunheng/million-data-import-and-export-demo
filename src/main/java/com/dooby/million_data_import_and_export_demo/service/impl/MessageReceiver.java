package com.dooby.million_data_import_and_export_demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dooby.million_data_import_and_export_demo.config.RabbitConfig;
import com.dooby.million_data_import_and_export_demo.entity.Status;
import com.dooby.million_data_import_and_export_demo.entity.User;
import com.dooby.million_data_import_and_export_demo.enummeration.StatusEnum;
import com.dooby.million_data_import_and_export_demo.service.IStatusService;
import com.dooby.million_data_import_and_export_demo.service.IUserService;
import com.dooby.million_data_import_and_export_demo.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * @Author Dooby Kim
 * @Date 2023/4/24 7:57 下午
 * @Version 1.0
 */
@Slf4j
@Component
public class MessageReceiver {

    @Autowired
    private IUserService userService;

    @Autowired
    private OssService ossService;

    @Autowired
    private IStatusService statusService;

    @RabbitListener(queues = RabbitConfig.BUSINESS_QUEUE)
    public void receive(Message message) {
        // 发送的消息即为状态序列号
        String seq = new String(message.getBody());
        try {

            // 将数据写入到 Excel 文档中
            String fileName = "test.xlsx";
            String filePath = PathUtil.getTempPath() + fileName;

            // 定义每一个 sheet 页最多存储 5 万条数据，同时分页每次查询 5 万条数据
            // 处理导出数据到 Excel 的逻辑
            // 前一百个用户测试
            long totalCount = userService.count();
            long pageSize = 50000;
            long totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
            for (int i = 1; i <= totalPage; i++) {
                Page<User> page = new Page<>(i, pageSize);
                Page<User> pageResult = userService.page(page);
                List<User> records = pageResult.getRecords();
                EasyExcel.write(new File(filePath), User.class)
                        .sheet(i, "sheet" + i)
                        .doWrite(records);
            }

            // 上传 Excel 至 OSS
            String excelPath = ossService.uploadFile(fileName, filePath);

            Status updateStatus = Status.builder()
                    .seq(seq)
                    .status(StatusEnum.EXPORT_SUCCESS)
                    .path(excelPath).build();
            // update status
            statusService.updateById(updateStatus);
            log.info("upload success");
        } catch (Exception e) {
            log.error("error", e);
            // 将状态置为导出失败
            Status failStatus = Status.builder().seq(seq).status(StatusEnum.EXPORT_FAIL).build();
            statusService.updateById(failStatus);
            throw new RuntimeException(e);
        }
    }
}
