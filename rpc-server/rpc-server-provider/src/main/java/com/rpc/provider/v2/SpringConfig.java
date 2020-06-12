package com.rpc.provider.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/14 13:31
 * @since JDK 1.8
 */
@Configuration
@ComponentScan(basePackages = "com.rpc.provider.v2")
public class SpringConfig {

    @Bean(name = "rpcProxyServer")
    public RpcProxyServer rpcProxyServer(){
        return new RpcProxyServer(8081);
    }





}
