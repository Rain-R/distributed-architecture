package com.rpc.provider.v2;

import com.rpc.rpcannotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 17:23
 * @since JDK 1.8
 */
@Component
public class RpcProxyServer implements ApplicationContextAware, InitializingBean {

    //定义线程池 处理任务
    ExecutorService executorService = Executors.newCachedThreadPool();

    private int port;
    Map<String, Object> handlerMap = new HashMap<>();

    public RpcProxyServer(int port) {
        this.port = port;
    }

    public void publish() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(this.port);
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessHandler(socket, handlerMap));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void afterPropertiesSet() throws Exception {

        //发布服务
        publish();


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //获取暴露服务的 注解
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (!serviceBeanMap.isEmpty()) {
            for (Object serviceBean : serviceBeanMap.values()) {
                //获取类上的服务注解
                RpcService rpcService = serviceBean.getClass().getAnnotation(RpcService.class);
                Class<?> interfaceCls = rpcService.value();
//            interfaceCls.getName();
                //因为客户端调用是根据 接口名 调用 因此要建立与实现类的连接
                handlerMap.put(interfaceCls.getName(), serviceBean);

            }


        }
    }
}
