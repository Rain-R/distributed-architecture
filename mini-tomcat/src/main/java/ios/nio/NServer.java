package ios.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/17 17:17
 * @since JDK 1.8
 */
public class NServer {

    //服务器的端口
    private int port = 8080;

    //缓冲区
//    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    Charset charset=Charset.forName("UTF-8");

    //选择器
    private Selector selector;


    public static void main(String[] args) {
        new NServer().init();
    }
    public void init() {

        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(port));
            //NIO 为了兼容bio 默认采用阻塞的方式  手动设置非阻塞
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while(selector.select()>0){

                 Set<SelectionKey> keys=selector.selectedKeys();
                 Iterator<SelectionKey> itr=keys.iterator();
                 while(itr.hasNext()){
                     SelectionKey key=itr.next();
                     itr.remove();
                     if(key.isAcceptable()){

                           SocketChannel sc =serverSocketChannel.accept();
                           sc.configureBlocking(false);
                           sc.register(selector,SelectionKey.OP_READ);
                           key.interestOps(SelectionKey.OP_ACCEPT);
                           sc.write(charset.encode("请输入你的昵称"));

                     }
                     if(key.isReadable()){
                         SocketChannel sc =(SocketChannel) key.channel();
                         String content="";
                         ByteBuffer buffer = ByteBuffer.allocate(1024);
                         while(sc.read(buffer)>0){
                             buffer.flip();
                             content+= charset.decode(buffer);
                         }
                         System.out.println("客户端输入数据:"+content);
                         //将selectionKey设置成 准备下一次读取
                         key.interestOps(SelectionKey.OP_READ);
                         if(content.length()>0){
                           for(SelectionKey k: selector.keys()){


                               Channel channel=k.channel();

                               if(channel instanceof  SocketChannel && channel!=sc){

                                   SocketChannel socketChannel=(SocketChannel) channel;
                                   socketChannel.write(charset.encode(content));
                                   buffer.clear();



                               }

                           }



                         }

                     }

                 }





            }


        } catch (Exception e) {

            e.printStackTrace();
        }


    }



}
