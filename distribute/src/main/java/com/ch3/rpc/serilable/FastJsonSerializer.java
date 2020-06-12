package com.ch3.rpc.serilable;

import com.alibaba.fastjson.JSON;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 16:18
 * @since JDK 1.8
 */
public class FastJsonSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {

        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {

        return JSON.parseObject(data,clazz);
    }
}
