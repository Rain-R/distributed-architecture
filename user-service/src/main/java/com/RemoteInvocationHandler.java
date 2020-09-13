package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 22:14
 * @since JDK 1.8
 */
@Component
public class RemoteInvocationHandler implements InvocationHandler{
    @Value("${gp.host}")
    String host;
    @Value("${gp.port}")
    int port;


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest=new RpcRequest();
        String className=method.getDeclaringClass().getName();
        String methodName=method.getName();
        Class<?>[] types=method.getParameterTypes();
        rpcRequest.setClassName(className);
        rpcRequest.setMethodName(methodName);
        rpcRequest.setTypes(types);
        rpcRequest.setParams(args);

        RpcClientNetTransport rpcClientNetTransport=new RpcClientNetTransport(host,port);


        return rpcClientNetTransport.send(rpcRequest);
    }
}
