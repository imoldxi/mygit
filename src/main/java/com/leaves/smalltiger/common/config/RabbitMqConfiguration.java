package com.leaves.smalltiger.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author James
 * @date 2019/12/7 15:19
 */
@Configuration
public class RabbitMqConfiguration {
    /**
     * 声明一个名为hello的队列
     */
    @Bean
    public Queue testQueue() {
        return new Queue("hello");
    }
}
