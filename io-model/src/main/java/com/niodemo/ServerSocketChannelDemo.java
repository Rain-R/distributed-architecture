package com.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/12 0:31
 * @since JDK 1.8
 */
public class ServerSocketChannelDemo {

    static Selector selector;
    public static void main(String[] args) {
        ServerSocketChannel server;
        try {

            server=ServerSocketChannel.open();
            server.bind(new InetSocketAddress(8080));
            server.configureBlocking(false);
            selector=Selector.open();
            //将目标网络连接先注册到系统调用 select/epoll 上
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {

                    SelectionKey key = keys.next();
                    keys.remove();
                   if (key.isAcceptable()){
                     ServerSocketChannel serverChannel=(ServerSocketChannel)key.channel();
                     SocketChannel client=serverChannel.accept();
                       client.configureBlocking(false);
                       client.write(ByteBuffer.wrap("test".getBytes()));
                       client.register(selector,SelectionKey.OP_READ);
                   }else if (key.isReadable()){

                       SocketChannel client=(SocketChannel) key.channel();

                       ByteBuffer buffer=ByteBuffer.allocate(1024);
                       client.read(buffer);
                       System.out.println("收到客户端端的数据:"+new String(buffer.array()));
                       client.write(ByteBuffer.wrap("我是服务器:".getBytes()));

                   }


                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
