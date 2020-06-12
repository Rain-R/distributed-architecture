package com.rpc.client;

import com.rpc.api.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 22:32
 * @since JDK 1.8
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setParams(args);
        rpcRequest.setHost(host);
        rpcRequest.setPort(port);
        rpcRequest.setMethodName(method.getName());
        RpcNetTransport rpcNetTransport=new RpcNetTransport();
        Object result=rpcNetTransport.send(rpcRequest);

        return result;
    }
}
