package com.ch3.rpc.serilable;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 16:37
 * @since JDK 1.8
 */
public class XmlStreamSerializer implements  ISerializer {

    XStream xStream=new XStream(new DomDriver());
    @Override
    public <T> byte[] serialize(T obj) {
        return xStream.toXML(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return (T)xStream.fromXML(new String(data));
    }
}
