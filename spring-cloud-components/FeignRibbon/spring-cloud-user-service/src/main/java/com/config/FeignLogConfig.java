package com.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/7 22:34
 * @since JDK 1.8
 */
@Configuration
public class FeignLogConfig {
    @Bean
    Logger.Level feignLogger(){
        return  Logger.Level.BASIC;
    }
}
