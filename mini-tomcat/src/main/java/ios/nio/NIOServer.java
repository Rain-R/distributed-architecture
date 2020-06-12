package ios.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/18 14:52
 * @since JDK 1.8
 */
public class NIOServer {

    private int port = 8080;
    private Charset charset = Charset.forName("UTF-8");
    //用来记录在线人数，以及昵称
    private static HashSet<String> users = new HashSet<String>();

    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    //相当于自定义协议格式，与客户端协商好
    private static String USER_CONTENT_SPILIT = "#@#";

    private Selector selector = null;


    public NIOServer(int port) {

        //
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.bind(new InetSocketAddress(port));
            server.configureBlocking(false);
            selector = Selector.open();

            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器已经启动，监听端口8080");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen() throws IOException {

        while (true) {

            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> itr = keys.iterator();
            while (itr.hasNext()) {
                SelectionKey key = itr.next();
                //删除正在 处理的selectionKey
                itr.remove();
                process(key);

            }

        }

    }

    public void process(SelectionKey key) throws IOException {


        if (key.isAcceptable()) {

            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            key.interestOps(SelectionKey.OP_ACCEPT);
//            client.write(charset.encode("请输入你的昵称"));
        } else if (key.isReadable()) {

            SocketChannel client = (SocketChannel) key.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuffer content = new StringBuffer();
            try {
                while (client.read(buffer) > 0) {
                    buffer.flip();
                    content.append(charset.decode(buffer));

                }
            } catch (IOException e) {
                //客户端出现异常需要  取消selectionKey的channel注册关系
                key.cancel();
                if (key.channel() != null) {
                    key.channel().close();
                }
            }
            if (content.length() > 0) {
                client.write(charset.encode(content.toString()));
            }


        }


    }


    public static void main(String[] args) throws Exception {
        new NIOServer(8080).listen();
    }
}
