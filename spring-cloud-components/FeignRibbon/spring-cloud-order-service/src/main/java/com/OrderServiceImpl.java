package com;

import com.framework.example.OrderService;
import org.springframework.stereotype.Service;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/7 23:01
 * @since JDK 1.8
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public String getOrders() {
        return "Orders";
    }
}
