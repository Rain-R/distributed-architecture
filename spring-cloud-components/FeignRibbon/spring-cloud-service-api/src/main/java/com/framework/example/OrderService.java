package com.framework.example;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/7 22:50
 * @since JDK 1.8
 */
public interface OrderService {

     @GetMapping("/order")
     String getOrders();
}
