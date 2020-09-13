package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:39
 * @since JDK 1.8
 */

@ComponentScan("com")
@Configuration
public class Bootstrap {

    public static void main(String[] args) {
        ApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(Bootstrap.class);


    }

}
