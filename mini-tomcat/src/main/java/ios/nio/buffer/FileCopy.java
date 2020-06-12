package ios.nio.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Executors;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/24 15:53
 * @since JDK 1.8
 */
public class FileCopy {

    public static void main(String[] args) throws Exception {

        File file=new File("E://test.txt");
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer=fileChannel.map(FileChannel.MapMode.READ_WRITE,0,file.length());
        fileChannel.position(file.length());
        fileChannel.write(mappedByteBuffer);

//        ByteBuffer buffer=ByteBuffer.allocate(4);
//
//        FileInputStream fin=new FileInputStream("E://test.txt");
//        FileChannel fileChannel= fin.getChannel();
//
//
//
//
//        String  outFile=String.format("E://testcopy.txt");
//        FileOutputStream fileOutputStream=new FileOutputStream(outFile);
//
//        FileChannel outChannel=fileOutputStream.getChannel();
//
//
//        while(fileChannel.read(buffer)>0){
//
//            buffer.flip();
//
//            outChannel.write(buffer);
//
//            buffer.clear();
//
//        }
//        fileChannel.close();
//        fin.close();
//        fileOutputStream.close();
//        outChannel.close();


    }
}
