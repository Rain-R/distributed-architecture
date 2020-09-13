package com;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  用于网络传输的类
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:35
 * @since JDK 1.8
 */
public class RpcProxyServer {


   /**
    *  实现注册中心发布服务
    * @param service service
    * @param port port
    * @return void
    * @author wz
    * @date 2020/6/18 20:40
    */
   static  final ExecutorService executorService= Executors.newCachedThreadPool();
   public void publish(Object service, int port) throws IOException {


       ServerSocket serverSocket=new ServerSocket(port);
       while (true){
           Socket socket = serverSocket.accept();

           executorService.execute(new ProcessHandler(socket));
       }

   }




}
