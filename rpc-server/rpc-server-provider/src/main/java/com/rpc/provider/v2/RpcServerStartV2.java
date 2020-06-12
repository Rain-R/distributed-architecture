package com.rpc.provider.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/14 13:58
 * @since JDK 1.8
 */
public class RpcServerStartV2 {
    public static void main(String[] args) {


        ApplicationContext context= new AnnotationConfigApplicationContext(SpringConfig.class);

        ((AnnotationConfigApplicationContext)context).start();

    }
}
