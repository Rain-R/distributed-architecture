package com.netty.tomcat.servlet;

import com.netty.tomcat.http.Request;
import com.netty.tomcat.http.Response;
import com.netty.tomcat.http.Servlet;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/4/4 20:21
 * @since JDK 1.8
 */
public class SecondServlet extends Servlet {
    public void doGet(Request request, Response response) throws Exception {
        doPost(request,response);
    }

    public void doPost(Request request, Response response) throws Exception {

        response.write("this is Second servlet");
    }





}
