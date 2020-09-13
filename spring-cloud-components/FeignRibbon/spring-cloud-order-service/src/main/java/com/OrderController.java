package com;

import com.framework.example.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/4 14:56
 * @since JDK 1.8
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String getOrders(){
        return  orderService.getOrders();
    }



}
