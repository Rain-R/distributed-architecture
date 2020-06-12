package com.netty;

import com.netty.tomcat.http.Request;
import com.netty.tomcat.http.Response;
import com.netty.tomcat.http.Servlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/4/8 14:44
 * @since JDK 1.8
 */
public class NettyTomcat2 {


    private int port = 8080;


    Properties contextConfig = new Properties();
    private Map<String, Servlet> handleMapping = new HashMap<String, Servlet>();


    public void init() {
        doLoadConfig();
        doHandleMapping();

    }

    private void doHandleMapping() {

        try {
            for (Object k : contextConfig.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String servletClassName = contextConfig.getProperty(servletName + ".className");
                    Object instance = Class.forName(servletClassName).newInstance();
                    handleMapping.put(contextConfig.getProperty(key), (Servlet) instance);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doLoadConfig() {
        InputStream in = this.getClass().getResourceAsStream("/web.properties");
        try {
            contextConfig.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        //加载配置文件
        init();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //与解码器 先后有顺序要求
                        //编码器
                        socketChannel.pipeline().addLast(new HttpResponseEncoder());
                        //解码器
                        socketChannel.pipeline().addLast(new HttpResponseDecoder());

                        //自定义即 我们处理客户端的请求类

                        socketChannel.pipeline().addLast(new NettyTomcatHandle());



                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
           ChannelFuture channelFuture= serverBootstrap.bind(port).sync();
            System.out.println("Netty tomcat 已经启动监听端口"+port);
            channelFuture.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    class NettyTomcatHandle extends ChannelInboundHandlerAdapter{

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            if(msg instanceof HttpRequest){

                HttpRequest httpRequest=(HttpRequest) msg;

                Request request=new Request(ctx,httpRequest);
                Response response=new Response(ctx,httpRequest);

                if(handleMapping.containsKey(request.getUrl())){
                    handleMapping.get(request.getUrl()).service(request,response);

                }else{
                    response.write("404 Not found");
                }

            }

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }

    public static void main(String[] args) {
        NettyTomcat2 nettyTomcat2=new NettyTomcat2();
        nettyTomcat2.start();
    }

}
