package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/23 9:09
 * @since JDK 1.8
 */

@Configuration
public class MyConfig {

   @Bean
    public  SpiDemoEntity spiDemoEntity(){
       return  new SpiDemoEntity();
   }


}
