package com.framework.samples.dispatchapp.entity;

/**
 * ${description}
 *
 * @author wz
 * @version v1.0
 * @datetime 2020/9/8 17:59
 * @since JDK 1.8
 */
import lombok.Data;

@Data
public class DispatchEntity {

    private Long id;
    // 订单号
    private String orderId;
    // 外卖员id
    private Long takeoutUserId;

}
