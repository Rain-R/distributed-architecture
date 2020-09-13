package com.framework.demos.springbootrabbitsamples.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/13 16:15
 * @since JDK 1.8
 */
@Configuration
public class MQConfig {


    @Bean
    public Queue queue(){
        return  null;

    }
}
