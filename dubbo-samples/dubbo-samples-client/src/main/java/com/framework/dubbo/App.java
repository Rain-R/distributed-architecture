package com.framework.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext(new String[] {"classpath:META-INF/spring/application.xml"});

        HelloService helloService=applicationContext.getBean(HelloService.class);
        System.err.println(helloService.say("hello world "));
    }
}
