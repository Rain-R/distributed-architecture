package com.netty.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/4/4 20:21
 * @since JDK 1.8
 */
public class Response {


    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public Response(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }


    public void write(String out) throws Exception {

        try {
            if (out == null || out.length() == 0) {
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8"))
            );
            response.headers().set("Content-Type", "text/html;");
            ctx.write(response);
        } catch (UnsupportedEncodingException e) {

        } finally {
            ctx.flush();
            ctx.close();
        }


    }


}
