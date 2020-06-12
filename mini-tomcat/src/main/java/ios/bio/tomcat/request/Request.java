package ios.bio.tomcat.request;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/18 17:56
 * @since JDK 1.8
 */
public class Request {

    private String method;

    private String url;

    public Request(InputStream inputStream){

        //解析http的请求报文
        byte[] data=new byte[1024];
        try {
             inputStream.read(data);
             String content=new String(data);
             //http 请求报文  请求行  格式例如：  post /xxx/xxx  http/1.1
           String reqestHeader=  content.split("\r\n")[0];
           String[] ary=reqestHeader.split("\\s");
           String method =ary[0];
            String url=ary[1].split("\\?")[0];

            this.method=method;
            this.url=url;



        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
