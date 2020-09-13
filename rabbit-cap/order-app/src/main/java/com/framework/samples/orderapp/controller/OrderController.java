package com.framework.samples.orderapp.controller;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 17:55
 * @since JDK 1.8
 */
import com.framework.samples.orderapp.base.BaseApiService;
import com.framework.samples.orderapp.base.ResponseBase;
import com.framework.samples.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrderController extends BaseApiService {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/addOrder")
    public ResponseBase addOrder() {
        return orderService.addOrderAndDispatch();
    }

}
