package com.ch3.rpc.serilable;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 16:29
 * @since JDK 1.8
 */
public class HessianSerializer implements  ISerializer {
    @Override
    public <T> byte[] serialize(T obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            HessianOutput output=new HessianOutput(byteArrayOutputStream);
            output.writeObject(obj);
            return   byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        ByteArrayInputStream inputStream=new ByteArrayInputStream(data);

        HessianInput hessianInput=new HessianInput(inputStream);
        try {
           return (T) hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
