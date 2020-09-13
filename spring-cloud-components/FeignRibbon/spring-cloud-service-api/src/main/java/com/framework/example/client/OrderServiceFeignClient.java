package com.framework.example.client;

import com.framework.example.OrderService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/7 22:52
 * @since JDK 1.8
 */

@FeignClient("spring-cloud-order-service")
public interface OrderServiceFeignClient extends OrderService {
}
