package ios.nio.buffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/24 15:42
 * @since JDK 1.8
 */
public class MapByteBuffer {
    public static void main(String[] args) throws Exception {

        ByteBuffer buffer= ByteBuffer.allocate(1024);

        RandomAccessFile raf = new RandomAccessFile( "E://test.txt", "rw" );
        FileChannel channel=raf.getChannel();

        MappedByteBuffer mappedBuffer= channel.map(FileChannel.MapMode.READ_WRITE,0,26);

        mappedBuffer.put(0, (byte) 97);
        mappedBuffer.put(25, (byte) 122);





    }
}
