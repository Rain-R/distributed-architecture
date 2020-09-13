package com.framework.samples.dispatchapp.rabbitmq;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 18:02
 * @since JDK 1.8
 */

import java.util.Map;

import com.framework.samples.dispatchapp.entity.DispatchEntity;
import com.framework.samples.dispatchapp.mapper.DispatchMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;

/**
 * 派单服务
 */
@Component
public class DispatchConsumer {
    @Autowired
    private DispatchMapper dispatchMapper;

    @RabbitListener(queues = "order_dic_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");
        System.out.println("派单服务平台" + msg + ",消息id:" + messageId);
        JSONObject jsonObject = JSONObject.parseObject(msg);
        String orderId = jsonObject.getString("orderId");
        if (StringUtils.isEmpty(orderId)) {
            // 日志记录
            return;
        }
        DispatchEntity dispatchEntity = new DispatchEntity();
        // 订单id
        dispatchEntity.setOrderId(orderId);
        // 外卖员id
        dispatchEntity.setTakeoutUserId(12L);

        try {
            int insertDistribute = dispatchMapper.insertDistribute(dispatchEntity);
            if (insertDistribute > 0) {
                // 手动签收消息,通知mq服务器端删除该消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // // 丢弃该消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

}