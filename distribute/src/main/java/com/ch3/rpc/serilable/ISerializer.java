package com.ch3.rpc.serilable;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 16:11
 * @since JDK 1.8
 */
public interface ISerializer {

    <T> byte[] serialize(T obj);


    <T>   T  deserialize(byte[] data , Class<T> clazz);
}
