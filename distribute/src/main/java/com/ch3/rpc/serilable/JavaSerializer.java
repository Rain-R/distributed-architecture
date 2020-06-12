package com.ch3.rpc.serilable;

import java.io.*;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 16:21
 * @since JDK 1.8
 */
public class JavaSerializer implements ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();

        try {
            ObjectOutputStream outputStream=new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(obj);
            outputStream.close();
            byteArrayOutputStream.close();
            return  byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {

        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(data);
        try {
            ObjectInputStream in=new ObjectInputStream(byteArrayInputStream);
            T t=(T)in.readObject();
            return  t;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
