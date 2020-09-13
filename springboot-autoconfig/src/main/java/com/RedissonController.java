package com;

import org.redisson.api.RBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.redisson.api.RedissonClient;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/29 19:55
 * @since JDK 1.8
 */
@RestController
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/redisson/say")
    public String say(){
     RBucket rBucket= redissonClient.getBucket("name");
        if(rBucket.get()==null){
            rBucket.set("redis");

        }
        return  rBucket.get().toString();
    }

}
