package com.netty.tomcat;

import com.netty.tomcat.http.Request;
import com.netty.tomcat.http.Response;
import com.netty.tomcat.http.Servlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
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
 * @datetime 2020/4/4 16:43
 * @since JDK 1.8
 */
public class NettyTomcat {

    private  int port=8080;

    private Map<String,Servlet> servletMapping = new HashMap<String,Servlet>();

    Properties contextConfig=new Properties();

    public void init(){

        loadConfig();
        
        handleMapping();
    }

    public void start(){
        init();

        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();

//        ServerBootstrap server=new ServerBootstrap();

//        try {
//            server.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//
//                        protected void initChannel(SocketChannel client) throws Exception {
//
//                            client.pipeline().addLast(new HttpRequestDecoder());
//                            client.pipeline().addLast(new HttpResponseDecoder());
//                            client.pipeline().addLast(new TomcatHandler() );
//
//                        }
//                    }).option(ChannelOption.SO_BACKLOG, 128)
//                    // 针对子线程的配置 保持长连接
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//
//            // 启动服务器
//            ChannelFuture f = server.bind(port).sync();
//            System.out.println("Netty Tomcat 已启动，监听的端口是：" + port);
//            f.channel().closeFuture().sync();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
        try {
            // Netty服务
            //ServetBootstrap   ServerSocketChannel
            ServerBootstrap server = new ServerBootstrap();
            // 链路式编程
            server.group(bossGroup, workerGroup)
                    // 主线程处理类,看到这样的写法，底层就是用反射
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类 , Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 客户端初始化处理
                        protected void initChannel(SocketChannel client) throws Exception {
                            // 无锁化串行编程
                            //Netty对HTTP协议的封装，顺序有要求
                            // HttpResponseEncoder 编码器
                            client.pipeline().addLast(new HttpResponseEncoder());
                            // HttpRequestDecoder 解码器
                            client.pipeline().addLast(new HttpRequestDecoder());
                            // 业务逻辑处理
                            client.pipeline().addLast(new TomcatHandler());
                        }

                    })
                    // 针对主线程的配置 分配线程最大数量 128
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 针对子线程的配置 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器
            ChannelFuture f = server.bind(port).sync();
            System.out.println("GP Tomcat 已启动，监听的端口是：" + port);
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    private void handleMapping() {

        try {
            for (Object k : contextConfig.keySet()) {

                String key = k.toString();
                if(key.endsWith(".url")){
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = contextConfig.getProperty(key);
                    String className = contextConfig.getProperty(servletName + ".className");
                    Servlet obj = (Servlet)Class.forName(className).newInstance();
                    servletMapping.put(url, obj);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadConfig() {
        InputStream is=this.getClass().getResourceAsStream("/"+"web.properties");
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
  public class TomcatHandler extends ChannelInboundHandlerAdapter{

      @Override
      public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

          if(msg instanceof HttpRequest){

              HttpRequest req=(HttpRequest) msg;
              Request request=new Request(ctx,req);

              Response response=new Response(ctx,req);
              String url=request.getUrl();
              if(servletMapping.containsKey(url)){
                  servletMapping.get(url).service(request,response);
              }else{
                  response.write("404 Not Found");
              }

          }

      }

      @Override
      public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

      }
  }

    public static void main(String[] args) {
        new NettyTomcat().start();
    }

}
