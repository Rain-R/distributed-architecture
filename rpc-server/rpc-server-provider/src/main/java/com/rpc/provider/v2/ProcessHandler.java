package com.rpc.provider.v2;

import com.rpc.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 *
 * 功能描述: 
 *
 * @param 
 * @return 
 * @auther wz
 * @date 2020/3/14 14:33
 */
public class ProcessHandler implements Runnable {

    private Socket socket;
    private Map<String,Object> handlerMap;

    public ProcessHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    public Object invokeTargetMethod(RpcRequest rpcRequest) {

        try {
            Class<?> clazz = Class.forName(rpcRequest.getClassName());
            Object[] params = rpcRequest.getParams();
            Class<?>[] types = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                Class type = params[i].getClass();
                types[i] = type;

            }
            Object service=handlerMap.get(rpcRequest.getClassName());
            if(service==null){
                throw  new RuntimeException("服务不存在");
            }
            Method method = clazz.getMethod(rpcRequest.getMethodName(), types);
            return method.invoke(service,params);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        try {

            inputStream = new ObjectInputStream(socket.getInputStream());
            //反序列化
            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
            Object result = invokeTargetMethod(rpcRequest);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
