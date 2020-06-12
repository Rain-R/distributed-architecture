package com.rpc.client;

import com.rpc.api.IHelloService;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/14 12:23
 * @since JDK 1.8
 */
public class Test {


    public static void main(String[] args) {

        RpcProxyClient client=new RpcProxyClient();
        IHelloService helloService=client.clientProxy(IHelloService.class,"127.0.0.1",8081);
        Object res=helloService.sayHello("666");
        System.out.println(res);

    }

}
