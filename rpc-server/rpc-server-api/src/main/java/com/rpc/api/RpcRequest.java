package com.rpc.api;

import java.io.Serializable;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 17:24
 * @since JDK 1.8
 */
public class RpcRequest implements Serializable {


    private  String className;

    private  String methodName;

    private  Object[] params;

    private  String host;

    int port;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
