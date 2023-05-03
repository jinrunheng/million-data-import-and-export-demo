package com.dooby.million_data_import_and_export_demo.controller;

import com.dooby.million_data_import_and_export_demo.config.RabbitConfig;
import com.dooby.million_data_import_and_export_demo.entity.Status;
import com.dooby.million_data_import_and_export_demo.enummeration.StatusEnum;
import com.dooby.million_data_import_and_export_demo.service.IStatusService;
import com.dooby.million_data_import_and_export_demo.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author Dooby Kim
 * @Date 2023/4/18 11:20 下午
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private IStatusService statusService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    @ResponseBody
    public Result<Status> sendMsg() {
        String seq = UUID.randomUUID().toString();
        // 设置状态为"文件导出中"
        Status status = Status.builder()
                .seq(seq)
                .status(StatusEnum.EXPORTING)
                .build();
        statusService.save(status);
        // 异步处理
        // messageSender.sendMsg(seq);
        rabbitTemplate.convertSendAndReceive(
                RabbitConfig.BUSINESS_EXCHANGE,
                RabbitConfig.BUSINESS_ROUTING_KEY,
                seq);

        return Result.ok("文件导出中", status);
    }

    @PostMapping("/status")
    @ResponseBody
    public Result<Status> getStatus(@RequestBody String seq) {
        log.info(seq);
        Status status = statusService.getById(seq);
        return Result.ok("状态查询成功", status);
    }
}
