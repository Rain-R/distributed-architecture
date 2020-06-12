package com.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 22:22
 * @since JDK 1.8
 */
public class RpcProxyClient {

  public <T> T clientProxy(Class<T> interfaceCls,String host, final int port){

      return  (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),new Class<?>[]{interfaceCls},new RemoteInvocationHandler(host,port));

  }
}
