package com;

/**
 * 服务端 和客户端的共同约定
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:30
 * @since JDK 1.8
 */
public interface OrderService {
    /**
     *  
     * 获取所有的服务列表  
     * @return java.lang.String
     * @author wz
     * @date 2020/6/18 20:50
     */
    String getOrders();
}
