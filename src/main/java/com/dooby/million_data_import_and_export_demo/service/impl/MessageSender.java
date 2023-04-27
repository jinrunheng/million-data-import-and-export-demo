package com.dooby.million_data_import_and_export_demo.service.impl;

import com.dooby.million_data_import_and_export_demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Dooby Kim
 * @Date 2023/4/25 10:21 下午
 * @Version 1.0
 */
@Service
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend(RabbitConfig.BUSINESS_EXCHANGE, RabbitConfig.BUSINESS_ROUTING_KEY, msg);
    }
}
