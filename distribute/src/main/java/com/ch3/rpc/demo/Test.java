package com.ch3.rpc.demo;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 0:15
 * @since JDK 1.8
 */
public class Test {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket=new ServerSocket(8080);

            Socket socket=serverSocket.accept();
            socket.getInputStream();
            ObjectInputStream inputStream=new ObjectInputStream(  socket.getInputStream());
            User user=(User) inputStream.readObject();
            System.out.println(user);

            inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
