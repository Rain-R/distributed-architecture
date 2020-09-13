package com.framework.dubbo;

import org.apache.dubbo.container.Main;

/**
 * Hello world!
 *
 */
public class ProviderApp
{
    public static void main( String[] args ) throws Exception
    {
//        System.out.println( "Hello World!" );
//        Main.main(new String[]{ "classpath:META-INF/spring/application.xml"  });

//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext(new String[] {"classpath:META-INF/spring/application.xml"});
//        context.start();
//        System.in.read(); // 按任意键退出
        Main.main(args);

    }
}
