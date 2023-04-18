package com.dooby.million_data_import_and_export_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Author Dooby Kim
 * @Date 2023/4/18 10:14 下午
 * @Version 1.0
 * @Desc 异步线程池配置类
 */
@Configuration
@EnableAsync
public class AsyncTaskConfig {

    @Bean
    public Executor fileExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 设置线程池的核心线程数
        threadPoolTaskExecutor.setCorePoolSize(5);
        // 设置线程池最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(10);
        // 设置缓冲队列的长度
        threadPoolTaskExecutor.setQueueCapacity(10);
        // 设置线程池关闭时，是否要等待所有线程结束后再关闭
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池等待所有线程结束的时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        // 设置所有线程的前缀名称
        threadPoolTaskExecutor.setThreadNamePrefix("file-async-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
