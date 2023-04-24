package com.dooby.million_data_import_and_export_demo.service.impl;

import com.dooby.million_data_import_and_export_demo.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.Channel;

/**
 * @Author Dooby Kim
 * @Date 2023/4/24 7:57 下午
 * @Version 1.0
 */
@Slf4j
@Component
public class MessageReceiver {

    @RabbitListener(queues = RabbitConfig.BUSINESS_QUEUE)
    public void receive(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
    }
}
