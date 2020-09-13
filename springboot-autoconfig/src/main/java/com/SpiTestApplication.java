package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/23 20:23
 * @since JDK 1.8
 */
@SpringBootApplication
public class SpiTestApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext= SpringApplication.run(SpiTestApplication.class,args);
//        Object o =applicationContext.getBean(SpiDemoEntity.class);
//        //如果么有TestBan这个类 就不会加载SpiDemoEntity这个类，这就是SPI的机制中的条件控制
//
//        System.out.println(o);
    }
}
