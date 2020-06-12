package com.ch1;

import java.io.*;
import java.net.Socket;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/2/15 13:19
 * @since JDK 1.8
 */
public class SocketClient {


    public static void main(String[] args) throws IOException {


        Socket socket=new Socket("127.0.0.1",8080);

        PrintWriter pw =new PrintWriter(socket.getOutputStream(),true);

        BufferedReader in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //获取控制台的输入流
        BufferedReader sin =new BufferedReader(new InputStreamReader(System.in));

        String readLine=sin.readLine();
        while (!readLine.equals("bye")){

            pw.println(readLine);
            System.out.println("server:"+in.readLine());
            readLine=sin.readLine();

        }


        pw.close();
        socket.close();
    }
}
