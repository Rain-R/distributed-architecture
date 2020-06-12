package com.ch3.rpc.demo;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 0:15
 * @since JDK 1.8
 */
public class Client {

    public static void main(String[] args) {

        User user=new User();
        user.setAge(18);
        user.setName("xzgg");
        try {
            Socket socket=new Socket("localhost",8080);

            ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(user);

            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
