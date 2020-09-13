package com.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * NIO 客户端的 端
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/12 0:58
 * @since JDK 1.8
 */
public class NIOClientDemo {

    static Selector selector;

    public static void main(String[] args) {
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8080));
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            socketChannel.write(ByteBuffer.wrap("client: 发送连接".getBytes()));
            while ( selector.select()>0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isReadable()){
                        handleRead(key);
                    }
                }
                TimeUnit.SECONDS.sleep(5);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void handleRead(SelectionKey key ) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        client.read(buffer);
        System.out.println( "client receive msg: "+new String(buffer.array()));
        client.write(ByteBuffer.wrap("123456".getBytes()));
        key.interestOps(SelectionKey.OP_READ);

    }
}
