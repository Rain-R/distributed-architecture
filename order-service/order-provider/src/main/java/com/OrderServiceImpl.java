package com;

import org.springframework.stereotype.Service;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 20:32
 * @since JDK 1.8
 */
@GpRemoteService
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrders() {
        System.out.println("get all orders");
        return "get all orders";
    }
}
