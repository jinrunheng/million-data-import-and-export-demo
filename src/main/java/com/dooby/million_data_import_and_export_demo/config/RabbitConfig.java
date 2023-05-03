package com.dooby.million_data_import_and_export_demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Dooby Kim
 * @Date 2023/4/24 10:42 上午
 * @Version 1.0
 */
@Configuration
@Slf4j
public class RabbitConfig {

    // 发送导出 Excel 请求时调用
    public static final String BUSINESS_QUEUE = "queue.business";
    public static final String BUSINESS_EXCHANGE = "exchange.business";
    public static final String BUSINESS_ROUTING_KEY = "key.business";

    // 死信
    public static final String DEAD_LETTER_QUEUE = "queue.deadletter";
    public static final String DEAD_LETTER_EXCHANGE = "exchange.deadletter";
    public static final String DEAD_LETTER_ROUTING_KEY = "key.deadletter";

    /**
     * 声明业务队列
     *
     * @return
     */
    @Bean("businessQueue")
    public Queue businessQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // 声明绑定的死信交换机
        args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 声明绑定的死信路由
        args.put("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY);
        return QueueBuilder.durable(BUSINESS_QUEUE)
                .withArguments(args)
                .build();
    }

    /**
     * 声明死信队列
     *
     * @return
     */
    @Bean("deadletterQueue")
    public Queue deadletterQueue() {
        return new Queue(DEAD_LETTER_QUEUE);
    }

    /**
     * 声明业务交换机
     *
     * @return
     */
    @Bean("businessExchange")
    public DirectExchange businessExchange() {
        return new DirectExchange(BUSINESS_EXCHANGE);
    }

    /**
     * 声明死信交换机
     *
     * @return
     */
    @Bean("deadletterExchange")
    public DirectExchange deadletterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明业务绑定关系
     *
     * @return
     */
    @Bean
    public Binding businessBinding(
            @Qualifier("businessQueue") Queue queue,
            @Qualifier("businessExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(BUSINESS_ROUTING_KEY);
    }

    @Bean
    public Binding deadletterBinding(
            @Qualifier("deadletterQueue") Queue queue,
            @Qualifier("deadletterExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 开启消息确认
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        // 开启消息返回
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // 开启消息返回机制
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returned -> {
            // 说明消息不可达
            log.info("message:{}", returned.getMessage().toString());
            log.info("replyCode:{}", returned.getReplyCode());
            log.info("replyText:{}", returned.getReplyText());
            log.info("exchange:{}", returned.getExchange());
            log.info("routingKey:{}", returned.getRoutingKey());
        });
        // 开启消息确认机制
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("send msg to Broker success");
                log.info("correlationData : {}", correlationData);
            } else {
                log.info("send msg to Broker fail");
                log.info("cause : {}", cause);
            }
        });
        return rabbitTemplate;
    }
}
