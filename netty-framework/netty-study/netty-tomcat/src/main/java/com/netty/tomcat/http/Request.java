package com.netty.tomcat.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/4/4 20:20
 * @since JDK 1.8
 */
public class Request {


    private ChannelHandlerContext ctx;

    private HttpRequest req;

    public Request(ChannelHandlerContext ctx, HttpRequest req) {
        this.ctx = ctx;
        this.req = req;
    }

    public String getUrl() {

        String uri=req.uri();
        String url= uri.split("\\?")[0];

        return url;
    }
    public String getMethod(){
        return  req.method().name();
    }


    public Map<String, List<String>> getParameters() {
        Map<String, String>requestParams=new HashMap<String, String>();
        // 处理get请求
        QueryStringDecoder decoder = new QueryStringDecoder(req.uri());

        if (req.method() == HttpMethod.GET) {
            Map<String, List<String>> parame = decoder.parameters();
            Iterator<Map.Entry<String, List<String>>> iterator = parame.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, List<String>> next = iterator.next();
                requestParams.put(next.getKey(), next.getValue().get(0));
            }
        }
        // 处理POST请求
        if (req.method() == HttpMethod.POST) {
            HttpPostRequestDecoder decoderPost = new HttpPostRequestDecoder(
                    new DefaultHttpDataFactory(false), req);
            List<InterfaceHttpData> postData = decoderPost.getBodyHttpDatas(); //
            for(InterfaceHttpData data:postData){
                if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    requestParams.put(attribute.getName(), attribute.getValue());
                }
            }
        }


        return decoder.parameters();



    }

    public String getParameter(String name) {
        Map<String, List<String>> params = getParameters();
        List<String> param = params.get(name);
        if (null == param) {
            return null;
        } else {
            return param.get(0);
        }
    }



}
