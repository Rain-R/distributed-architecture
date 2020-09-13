package com.framework.demos.springbootrabbitsamples;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitSamplesApplicationTests {

	@Autowired
	AmqpTemplate amqpTemplate;


	@Test
	void sendMsg1() {
		amqpTemplate.convertAndSend("myTestQueue","test msg: hello mq ");

	}
	@Test
	void sendMsg2() {
		amqpTemplate.convertAndSend("myTestQueue2","自动生成队列 ");

	}
	@Test
	void sendMsg3() {
		amqpTemplate.convertAndSend("myTestQueue3","自动生成队列 ");

	}
	@Test
	void sendMsg4() {
//		amqpTemplate.convertAndSend("fruitQueue","发送分组消息");
		amqpTemplate.convertAndSend("fruitExchange","fruitKey","接受分组消息:fruit");
	}

}
