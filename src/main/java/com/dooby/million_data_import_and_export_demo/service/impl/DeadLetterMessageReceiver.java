package com.dooby.million_data_import_and_export_demo.service.impl;

import com.dooby.million_data_import_and_export_demo.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author Dooby Kim
 * @Date 2023/4/27 6:13 下午
 * @Version 1.0
 */
@Service
@Slf4j
public class DeadLetterMessageReceiver {

    @RabbitListener(queues = RabbitConfig.DEAD_LETTER_QUEUE)
    public void receive(Message message) {
        log.info("receive deadletter : {}", message.getBody());
    }
}
