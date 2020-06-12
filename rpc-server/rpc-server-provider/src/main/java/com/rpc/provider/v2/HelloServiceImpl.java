package com.rpc.provider.v2;

import com.rpc.api.IHelloService;
import com.rpc.rpcannotation.RpcService;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 17:21
 * @since JDK 1.8
 */
@RpcService(value = IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String message) {
        return "rpc-server-> say hello:"+message;
    }
}
