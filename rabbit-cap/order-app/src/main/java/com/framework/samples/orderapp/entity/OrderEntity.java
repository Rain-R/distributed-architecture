package com.framework.samples.orderapp.entity;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 17:50
 * @since JDK 1.8
 */
import lombok.Data;

@Data
public class OrderEntity {

    private Long id;
    // 订单名称
    private String name;
    // 下单金额
    private Double orderMoney;
    // 订单id
    private String orderId;
}