package com.netty.tomcat.http;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/4/4 20:21
 * @since JDK 1.8
 */
public abstract class Servlet {
    public void service(Request request, Response response) throws Exception {

        //由service方法来决定，是调用doGet或者调用doPost
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }

    }

    public abstract void doGet(Request request, Response response) throws Exception;

    public abstract void doPost(Request request, Response response) throws Exception;
}
