package com.redission;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/29 19:27
 * @since JDK 1.8
 */

@ConditionalOnClass(Redisson.class)
@EnableConfigurationProperties(RedissonProperties.class)
@Configuration
public class RedissonAutoConfiguration {


      @Bean
      public RedissonClient redissonClient(RedissonProperties redissonProperties){
          Config config=new Config();
          String  prefix="redis://";
          if(redissonProperties.isSsl()){
              prefix="reidss://";
          }
           config.useSingleServer().setAddress(
                   prefix+redissonProperties.getHost()+":"+redissonProperties.getPort()
           ).setTimeout(redissonProperties.getTimeout()).setPassword(redissonProperties.getPassword());
          return  Redisson.create(config);

      }




}
