package com.rpc.provider.v1;

import com.rpc.api.IHelloService;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 17:21
 * @since JDK 1.8
 */
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String message) {
        return "rpc-server-> say hello:"+message;
    }
}
