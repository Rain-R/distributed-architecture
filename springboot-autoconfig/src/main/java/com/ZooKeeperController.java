package com;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试 ZooKeeper的 Starter功能
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/29 21:48
 * @since JDK 1.8
 */
@RestController
public class ZooKeeperController {


     @Autowired
    ZooKeeper zooKeeper;
     @GetMapping("/zookeeper/say")
     public String say() throws Exception{
//     List<String> list =zooKeeper.getChildren("/testRoot",false);
         byte[] data = zooKeeper.getData("/testRoot", false, null);
         // System.out.println(new String(data));
         return  new String(data);
     }






}
