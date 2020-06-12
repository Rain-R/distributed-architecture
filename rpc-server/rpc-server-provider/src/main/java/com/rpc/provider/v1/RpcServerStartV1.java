package com.rpc.provider.v1;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/14 12:26
 * @since JDK 1.8
 */
public class RpcServerStartV1 {

    public static void main(String[] args) {

        HelloServiceImpl helloService=new HelloServiceImpl();

        RpcProxyServer rpcProxyServer=new RpcProxyServer();
        rpcProxyServer.publish(helloService,8080);


    }
}
