package ios.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/24 14:44
 * @since JDK 1.8
 */
public class BufferDemo {
    public static void main(String[] args) throws Exception {

        Charset charset=Charset.forName("utf-8");

        ByteBuffer buffer=ByteBuffer.allocate(10);

        FileInputStream fileInputStream=new FileInputStream("E:/test.txt");
        FileChannel channel=fileInputStream.getChannel();

        channel.read(buffer);
        output("初始化",buffer);

        buffer.flip();

        output("flip",buffer);


        while (buffer.hasRemaining()){

           byte b= buffer.get();
//            System.out.println((char)b);
        }
        output("调用get",buffer);



        buffer.clear();

        output("clear",buffer);


    }

    static  void output(String step, ByteBuffer buffer){
        System.out.println(""+step+":");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());


    }
}
