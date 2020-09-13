package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/18 22:53
 * @since JDK 1.8
 */
@RestController
public class UserController {

    @GpReference
    private OrderService orderService;

    @GetMapping("/getOrders")
    public String getOrders(){
        String res=orderService.getOrders();
        System.out.println("res----->"+res);
        return  res;
    }






}
