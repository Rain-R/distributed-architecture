package com.rpc.client;

import com.rpc.api.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/13 22:32
 * @since JDK 1.8
 */
public class RpcNetTransport {


    public Object send(RpcRequest rpcRequest){
        Object result=null;
        ObjectOutputStream outputStream=null;
        ObjectInputStream inputStream =null;
        try {
            Socket socket=new Socket(rpcRequest.getHost(),rpcRequest.getPort());
             outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
             inputStream=new ObjectInputStream(socket.getInputStream());
            result=  inputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return  result;
    }
}
