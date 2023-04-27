package com.dooby.million_data_import_and_export_demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dooby.million_data_import_and_export_demo.config.RabbitConfig;
import com.dooby.million_data_import_and_export_demo.entity.User;
import com.dooby.million_data_import_and_export_demo.service.IUserService;
import com.dooby.million_data_import_and_export_demo.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.BUSINESS_QUEUE)
    public void receive(Message message) {
        // 处理导出数据到 Excel 的逻辑
        String msg = new String(message.getBody());
        log.info("receive message : {}", msg);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 前一百用户测试
        queryWrapper.last("limit 0,100");
        List<User> users = userService.list(queryWrapper);
        // 将数据写入到 Excel 文档中
        String fileName = "test.xlsx";
        String filePath = PathUtil.getTempPath() + fileName;
        EasyExcel.write(new File(filePath), User.class).sheet("测试").doWrite(users);
        // 上传 Excel 至 OSS
        String excelViewPath = ossService.uploadFile(fileName, filePath);
        // 回传消息
        // rabbitTemplate.send();
        log.info("upload success");
    }
}
