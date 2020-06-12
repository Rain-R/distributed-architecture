package ios.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/24 15:11
 * @since JDK 1.8
 */
public class BufferSlice {

    public static void main(String[] args) {

        ByteBuffer buffer=ByteBuffer.allocate(10);
        for (int i = 0; i <buffer.capacity() ; i++) {
            buffer.put((byte)i);
        }

        buffer.position(4);
        buffer.limit(7);
        ByteBuffer slice= buffer.slice();

        for(int i=0;i<slice.capacity();i++){
            byte b=slice.get();
            b*=10;
            slice.put(i,b);
        }
        buffer.position( 0 );
        buffer.limit( buffer.capacity() );

        while (buffer.remaining()>0) {
            System.out.println( buffer.get() );
        }


    }
}
