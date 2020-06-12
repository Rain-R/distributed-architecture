package ios.bio.tomcat.servlet;

import ios.bio.tomcat.request.Request;
import ios.bio.tomcat.request.Response;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/18 23:27
 * @since JDK 1.8
 */
public class FirstServlet extends  HttpServlet {
    @Override
    protected void doGet(Request req, Response res) {

         doPost(req,res);

    }

    @Override
    protected void doPost(Request req, Response res) {
         res.write("this is firstServlet");
    }
}
