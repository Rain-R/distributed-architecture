package com.netty.tomcat;

import com.netty.tomcat.http.Request;
import com.netty.tomcat.http.Response;
import com.netty.tomcat.http.Servlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/4/7 13:07
 * @since JDK 1.8
 */
public class MyNettyTomcat {

    private int port = 8080;
    private Properties contextConfig = new Properties();
    private Map<String, Servlet> handleMapping = new HashMap<String, Servlet>();


    public void init() {
        doLoadConfig();
        doHandleMapping();
    }

    public void start() {

        init();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {


            serverBootstrap.group(bossGroup, workerGroup).
                    channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //顺序有要求
                            //编码
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            //解码
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());

                            //处理客户端的请求
                            socketChannel.pipeline().addLast(new TomcatHandler());


                        }
                    }).
                    //针对主线程 分配最大工作线程数目
                            option(ChannelOption.SO_BACKLOG, 128)
                    //对于子线程 设置长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 启动服务器
            ChannelFuture f = serverBootstrap.bind(port).sync();
            System.out.println("GP Tomcat 已启动，监听的端口是：" + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
                  e.printStackTrace();
        }

    }

    class TomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
            //处理对应的 请求
            if(msg instanceof  HttpRequest){

                HttpRequest httpRequest=  (HttpRequest) msg;
                Request request=new Request(ctx,httpRequest);
                Response response=new Response(ctx,httpRequest);
                if(handleMapping.containsKey(request.getUrl())){

                    handleMapping.get(request.getUrl()).service(request,response);
                }else{
                    response.write("404 Not Found");

                }

            }


        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }


    private void doHandleMapping() {

        for (Object key : contextConfig.keySet()) {

            String url = key.toString();
            if (url.endsWith(".url")) {
                String servletName = url.replaceAll("\\.url$", "");
                String className = contextConfig.getProperty(servletName + ".className");
                try {
                    Servlet servlet = (Servlet) Class.forName(className).newInstance();
                    handleMapping.put(contextConfig.getProperty(url), servlet);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        }


    }

    private void doLoadConfig() {

        InputStream in = this.getClass().getResourceAsStream("/" + "web.properties");
        try {
            contextConfig.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {

        MyNettyTomcat myNettyTomcat=new MyNettyTomcat();
        myNettyTomcat.start();

    }

}
