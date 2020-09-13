package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 *  可以使用 @Reference注解来  生成远程服务接口->api的  代理类
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 22:24
 * @since JDK 1.8
 */
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    RemoteInvocationHandler remoteInvocationHandler;
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] fields=bean.getClass().getDeclaredFields();
        for (Field field : fields) {

              if(field.isAnnotationPresent(GpReference.class)){

                  Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(),
                          new Class[]{field.getType()},remoteInvocationHandler);

                  field.setAccessible(true);
                  try {
                      field.set(bean,proxy );
                  } catch (IllegalAccessException e) {
                      e.printStackTrace();
                  }

              }


        }


        return bean;
    }
}
