package com;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * spring容器 启动后会发布一个 ContextRefreshedEvent
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 21:45
 * @since JDK 1.8
 */
@Component
public class SocketServerInit implements ApplicationListener {

    static  final ExecutorService executorService= Executors.newCachedThreadPool();
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {


        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
