package ios.bio.tomcat;

import ios.bio.tomcat.request.Request;
import ios.bio.tomcat.request.Response;
import ios.bio.tomcat.servlet.HttpServlet;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/18 17:54
 * @since JDK 1.8
 */
public class TomcatServer {


    static Map<String,HttpServlet>  servletMapping=new HashMap<>();

    ExecutorService service= Executors.newCachedThreadPool();

    public void init() {

         //加载配置文件   处理配置文件与servlet的映射关系
        String WEB_INFO=this.getClass().getResource("/").getPath();


        Properties properties =new Properties();
        try {
            properties.load(new FileReader(WEB_INFO+"/web.properties"));
            properties.keys();
            for(Object k:properties.keySet() ){

                  String key=k+"";
                  if(key.contains("url")){
                      //
                      String className= key.replaceAll("url","pattern");
                      String clazz=properties.get(className)+"";


                      HttpServlet obj=(HttpServlet)  Class.forName(clazz).newInstance();
                      servletMapping.put(properties.get(k)+"",obj);
                  }
            }


           start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void start() {
        ServerSocket serverSocket;

        try {
             serverSocket=new ServerSocket(8080);

             while (true){


                 Socket socket=serverSocket.accept();
//                 process(socket);
                 service.execute(new ProcessHandler(socket));
             }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

     class ProcessHandler implements  Runnable{

         Socket socket;
         public  ProcessHandler(Socket socket){
             this.socket=socket;
         }

         @Override
         public void run() {
             process(socket);
         }
         private void process(Socket socket) {
             InputStream inputStream;
             OutputStream outputStream;

             try {
                 inputStream=   socket.getInputStream();
                 outputStream =socket.getOutputStream();

                 Request request=new Request(inputStream);
                 Response response=new Response(outputStream);

                 String url=request.getUrl();
                 if(servletMapping.containsKey(url)){
                     servletMapping.get(url).service(request,response);
                 }else{
                     response.write("404 - Not found");
                 }
                 outputStream.flush();
                 outputStream.close();
                 inputStream.close();
                 socket.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }


         }
     }


    public static void main(String[] args) {

        new TomcatServer().init();

    }

}
