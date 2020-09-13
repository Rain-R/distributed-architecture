package com.framework.dubbo;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/8/7 0:08
 * @since JDK 1.8
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String msg) {
        return msg;
    }
}
