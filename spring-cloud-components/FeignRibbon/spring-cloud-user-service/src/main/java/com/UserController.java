package com;

//import com.clients.OrderServiceFeignClient;
import com.framework.example.client.OrderServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * springCloud 应用间的通信方式
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/7/4 14:54
 * @since JDK 1.8
 */

@RestController
public class UserController {

    //第一种
//    @Autowired
//    RestTemplate restTemplate;

//    @Bean
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    //第二种
    @Autowired
    LoadBalancerClient loadBalancerClient;

    //第三种方式

    @Autowired
    RestTemplate restTemplate;
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //第四种方式
    @Autowired
    OrderServiceFeignClient orderServiceFeignClient;



    /**
     * 单个节点  RestTemplate方式1
     *
     */
//    @GetMapping(value = "/user/{id}")
//    public String getOrders(@PathVariable("id") Integer id){
//      Object o=  restTemplate.getForObject("http://localhost:8081/order",String.class);
//        return  o.toString();
//
//    }

    /**
     * loadBalancerClient 方式2
     */
//    @GetMapping(value = "/user/{id}")
//    public String getOrders(@PathVariable("id") Integer id){
//        ServiceInstance instance = loadBalancerClient.choose("spring-cloud-order-service");
//        String url=String.format("http://%s:%s",instance.getHost(),instance.getPort()+"/order");
//        Object o=  restTemplate.getForObject(url,String.class);
//        return  o.toString();
//
//    }

    /**
     *  使用 @LoadBalanced注解在restTemplate的Bean的声明之上
     *  直接调用服务名
     * @author wz
     * @date 2020/7/4 15:49
     */
    @GetMapping(value = "/user/{id}")
    public String getOrders(@PathVariable("id") Integer id){
        return   restTemplate.getForObject("http://spring-cloud-order-service/order",String.class);

    }

    @GetMapping(value = "/users/{id}")
    public String getOrdersByFeign(@PathVariable("id") Integer id){
        return   orderServiceFeignClient.getOrders();

    }


}
