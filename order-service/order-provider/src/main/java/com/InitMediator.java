package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 21:17
 * @since JDK 1.8
 */
@Component
public class InitMediator implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //对加了指定标签的service 发布服务
        if (bean.getClass().isAnnotationPresent(GpRemoteService.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String key=bean.getClass().getInterfaces()[0].getName()+"."+method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key,beanMethod);
            }

        }


        return bean;
    }


}
