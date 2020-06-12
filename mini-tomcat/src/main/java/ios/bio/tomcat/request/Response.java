package ios.bio.tomcat.request;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/18 17:57
 * @since JDK 1.8
 */
public class Response {


    OutputStream outputStream ;
    public Response(OutputStream outputStream){
      this.outputStream=outputStream;
    }
    public void write(String s){


        //输出要要支持http 协议

        StringBuffer sb =new StringBuffer();
        sb.append("HTTP/1.1 200 OK \n");
        sb.append("content-type: text/html \n");
        sb.append("\r\n");
        sb.append(s);
        try {
            outputStream.write(sb.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}
