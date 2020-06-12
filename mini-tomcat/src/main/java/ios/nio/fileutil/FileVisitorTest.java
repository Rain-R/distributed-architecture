package ios.nio.fileutil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/29 14:16
 * @since JDK 1.8
 */
public class FileVisitorTest {
    private  void test(){
        System.out.println(this.getClass().getClassLoader().getResource("/ios"));

    }
    public static void main(String[] args) throws IOException {
     new FileVisitorTest().test();
       URL url=FileVisitorTest.class.getClassLoader().getResource("/"+"ios.nio.fileutil".replaceAll("\\.","/"));
        try{
            Files.walkFileTree(Paths.get(url.toString()), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path f, BasicFileAttributes attrs) throws IOException {

//                    if (f.getFileName().endsWith(".class")) {
                        System.out.println(""+f.getFileName());
//                    }

                    return FileVisitResult.CONTINUE;
                }
            });

        }catch (Exception e){

        }
//        Files.walkFileTree(Paths.get("F:","jgs-workspace","mini-tomcat"
//        ),new SimpleFileVisitor<Path>(){
//            @Override
//            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                if(file.endsWith("FileVisitorTest.java")){
//                    System.out.println("==========找到目标文件============");
//                    System.out.println(file);
//                    return  FileVisitResult.TERMINATE;
//                }
//                return super.visitFile(file, attrs);
//            }
//
//            @Override
//            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                System.out.println("++++++++++访问扫描路径路径之后+++++++++++++"+dir);
//                return super.postVisitDirectory(dir, exc);
//            }
//
//            @Override
//            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                System.out.println("-------------开始访问目录之前---------------"+dir);
//                return FileVisitResult.CONTINUE;
//            }
//
//            @Override
//            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//                System.out.println("访问文件失败");
//                return FileVisitResult.TERMINATE;
//            }
//        });



    }
}
