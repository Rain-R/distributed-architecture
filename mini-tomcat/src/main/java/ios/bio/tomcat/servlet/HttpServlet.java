package ios.bio.tomcat.servlet;

import ios.bio.tomcat.request.Request;
import ios.bio.tomcat.request.Response;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/18 17:56
 * @since JDK 1.8
 */
public abstract class HttpServlet {



    public  void service(Request req, Response res){

      if( "GET".equalsIgnoreCase(req.getMethod()))  {
          doGet(req,res);
      }else{
          doPost(req,res);
      }

    }

    protected  abstract void doGet(Request req, Response res);
    protected  abstract void doPost(Request req, Response res);




}
