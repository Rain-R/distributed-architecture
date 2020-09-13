package com;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket; /**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:52
 * @since JDK 1.8
 */
public class ProcessHandler implements Runnable {

    Socket socket;
    public ProcessHandler( Socket socket) {
           this.socket=socket;
    }

    @Override
    public void run() {

        ObjectInputStream inputStream=null;
        ObjectOutputStream outputStream=null;
        try {
             inputStream=new ObjectInputStream(socket.getInputStream());
             RpcRequest rpcRequest= (RpcRequest)inputStream.readObject();
             Mediator mediator=Mediator.getInstance();
             Object object= mediator.process(rpcRequest);
             //消息返回给客户端
            outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(object);
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
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


    }
}
