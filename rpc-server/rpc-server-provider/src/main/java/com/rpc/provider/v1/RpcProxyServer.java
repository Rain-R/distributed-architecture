package com.rpc.provider.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 17:23
 * @since JDK 1.8
 */
public class RpcProxyServer {

    //定义线程池 处理任务
    ExecutorService executorService= Executors.newCachedThreadPool();

    public  void publish(Object service,int port){
        ServerSocket serverSocket=null;

        try {
            serverSocket=new ServerSocket(port);
            while (true){
                Socket socket=serverSocket.accept();
                executorService.execute(new ProcessHandler(socket,service));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
