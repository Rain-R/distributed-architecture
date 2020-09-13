package com.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class NewIOClient {


    static Selector selector;

    public static void main(String[] args) {
        try {
            selector=Selector.open();
            SocketChannel socketChannel=SocketChannel.open();

            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",8080));

            socketChannel.register(selector, SelectionKey.OP_CONNECT); //连接事件
            while(true){
                selector.select();
                Set<SelectionKey> selectionKeySet=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeySet.iterator();
                while(iterator.hasNext()){
                    SelectionKey key=iterator.next();
                    iterator.remove();
                    if(key.isConnectable()){ //连接事件
                        handleConnect(key);
                    }else if(key.isReadable()){
                        handleRead(key);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     static void handleConnect(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
        if(socketChannel.isConnectionPending()){
            //
            socketChannel.finishConnect();
        }
        socketChannel.configureBlocking(false);
        socketChannel.write(ByteBuffer.wrap("Hello Server,I'm NIo Client".getBytes()));
        socketChannel.register(selector,SelectionKey.OP_READ); //
    }

     static void handleRead(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
        //TODO
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        System.out.println("client receive msg:"+new String(byteBuffer.array()));

    }

}
