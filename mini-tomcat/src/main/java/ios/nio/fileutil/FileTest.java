package ios.nio.fileutil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/29 14:00
 * @since JDK 1.8
 */
public class FileTest {
    public static void main(String[] args) throws Exception {

//        Files.copy(Paths.get("E:/test.txt"),new FileOutputStream("b.txt"));

//        List<String> lines=Files.readAllLines(Paths.get("b.txt"), Charset.forName("utf-8"));
//        System.out.println(lines);
//        lines.add("777777777");
//
//        Files.write(Paths.get("b.txt"),lines,Charset.forName("utf-8"));

        Files.list(Paths.get(".")).forEach(path-> System.out.println(path));


    }
}
