package com.framework.demos.springbootrabbitsamples;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/7 16:32
 * @since JDK 1.8
 */
@Component

public class MQReceiver {

    @RabbitListener(queues = {"myTestQueue"})
    public void receiveMsg(String msg) {

        System.out.println("receive msg:" + msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("myTestQueue2"))

    public void receiveMsg2(String msg) {

        System.out.println("=======自动生成队列,无需手动建立, msg2:" + msg);
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue("myTestQueue3"),
                            exchange = @Exchange("testExchange")
                    )
            }
    )
    public void receiveMsg3(String msg) {

        System.out.println("=======自动生成队列,绑定exchange 和 queue, msg3:" + msg);
    }



    @RabbitListener(bindings = @QueueBinding(value = @Queue("fruit"),
            exchange = @Exchange("fruitExchange"),key = "fruitKey"))
    public void processComputer(String msg ){
        System.out.println("接受分组消息:"+ msg );
    }


}
