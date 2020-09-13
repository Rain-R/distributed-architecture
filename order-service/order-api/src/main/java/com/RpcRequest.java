package com;

import java.io.Serializable;

/**
 * rpc协议的格式
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:33
 * @since JDK 1.8
 */
public class RpcRequest implements Serializable {


    String className;
    String methodName;
    Object[] params;
    Class[] types;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

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

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }
}
