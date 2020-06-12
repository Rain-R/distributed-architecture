package com.niodemo;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/17 21:14
 * @since JDK 1.8
 */
public class NIOClient {


    private Selector selector;

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    private Charset charset = Charset.forName("UTF-8");

    private SocketChannel client;

    public void init() {

        InetSocketAddress address = new InetSocketAddress("localhost", 8080);

        try {
            client = SocketChannel.open(address);
            selector = Selector.open();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            new ClientRead().start();
            new ClientWrite().start();
            //启动 读取服务器数据的线程
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public class ClientWrite extends Thread {

        @Override
        public void run() {

            System.out.println("请输入内容");
            Scanner scanner = new Scanner(System.in);
            try {
                while (scanner.hasNextLine()) {
                    String line = "" + scanner.nextLine();
                    client.write(charset.encode(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public class ClientRead extends Thread {

        @Override
        public void run() {

            try {
                while (true) {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) {
                        continue;
                    }
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> itr = keys.iterator();
                    while (itr.hasNext()) {
                        SelectionKey key = itr.next();
                        itr.remove();
                        process(key);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void process(SelectionKey key) throws IOException {
            if (key.isReadable()) {
                //使用 NIOServerDemoBak 读取 Channel中的数据，这个和全局变量client是一样的，因为只注册了一个SocketChannel
                //client既能写也能读，这边是读
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buff = ByteBuffer.allocate(1024);
                String content = "";
                while (sc.read(buff) > 0) {
                    buff.flip();
                    content += charset.decode(buff);
                }
                System.out.println("服务器端返回"+content);
                key.interestOps(SelectionKey.OP_READ);
            }
        }

    }

    public static void main(String[] args) {
        new NIOClient().init();
    }


}
