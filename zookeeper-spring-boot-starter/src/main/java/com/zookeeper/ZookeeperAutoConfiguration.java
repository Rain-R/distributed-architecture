package com.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/6/29 21:22
 * @since JDK 1.8
 */

@ConditionalOnClass(ZooKeeper.class)
@EnableConfigurationProperties(ZookeeperProperties.class)
@Configuration
public class ZookeeperAutoConfiguration {

    @Bean
    ZooKeeper zooKeeper(ZookeeperProperties zookeeperProperties){

        Watcher watcher=new Watcher(){
            public void process(WatchedEvent event) {
                System.out.println("监听到的事件："+event);
            }
        };
        try {
            final ZooKeeper zookeeper=new ZooKeeper(zookeeperProperties.getConnectString()
                    ,zookeeperProperties.getSessionTimeout(),watcher);
            System.out.println("获得连接："+zookeeper);
            return  zookeeper;
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
//        final byte[] data=zookeeper.getData("/zk1", watcher, null);
//        System.out.println("读取的值："+new String(data));
//        zookeeper.close();

    }


}
