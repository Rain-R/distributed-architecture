package com.rpc.provider.v2;

import com.rpc.api.IPaymentService;
import com.rpc.rpcannotation.RpcService;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/14 14:13
 * @since JDK 1.8
 */
@RpcService(value = IPaymentService.class)
public class PaymentService implements IPaymentService {
    @Override
    public String payInfo(Object msg) {
        System.out.println("server received:paymentService:"+msg);
        return msg.toString();
    }
}
