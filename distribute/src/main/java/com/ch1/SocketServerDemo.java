package com.ch1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/2/15 13:19
 * @since JDK 1.8
 */
public class SocketServerDemo {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket=new ServerSocket(8080);
        Socket socket=serverSocket.accept();
        //拿到客户端的输入流
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        String data;
        //输出流
        System.out.println("client:"+in.readLine());
        PrintWriter out =new PrintWriter(socket.getOutputStream(),true);
        BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));

        String readLine=sin.readLine();
        while(!readLine.equals("bye")){

            out.println(readLine);

            System.out.println("client:"+in.readLine());
            readLine=sin.readLine();

        }



//        while(!(data=bf.readLine()).equals("bye")){
//
//
//        }

        in.close();
        out.close();
        serverSocket.close();



    }
}
