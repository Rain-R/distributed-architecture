package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:54
 * @since JDK 1.8
 */
public class Mediator {
   private  static  Mediator instance;
   //存储发布的服务路径 进行服务路由
   static Map<String,BeanMethod> map=new ConcurrentHashMap<>();
   private Mediator(){

   }
   public static Mediator getInstance(){

       if(instance==null){
           synchronized (Mediator.class){
               if(instance==null){

                   instance =new Mediator();
               }

           }


       }

       return  instance;
   }

   //进行服务的路由
    public Object  process(RpcRequest rpcRequest){

       String key=rpcRequest.getClassName()+ "."+ rpcRequest.getMethodName();
        BeanMethod  beanMethod=map.get(key);
       if(beanMethod==null){
         return  null;
       }
       Object bean=beanMethod.getBean();
        Method method=beanMethod.getMethod();
        try {
           return   method.invoke(bean,rpcRequest.getParams());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }



}
