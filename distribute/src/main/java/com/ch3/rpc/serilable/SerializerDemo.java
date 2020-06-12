package com.ch3.rpc.serilable;

import com.ch3.rpc.demo.User;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 17:00
 * @since JDK 1.8
 */
public class SerializerDemo {


    public static void main(String[] args) {
        User user=new User();
        user.setName("Mic");
        user.setAge(18);
        user.setParentName("333");

           ISerializer   javaSerializer=new JavaSerializer();
           ISerializer    xml=new XmlStreamSerializer();
           ISerializer    json=new FastJsonSerializer();
           ISerializer    hessian=new HessianSerializer();
//

           System.out.println("java原生序列化的子节长度"+javaSerializer.serialize(user).length+ javaSerializer.deserialize(javaSerializer.serialize(user),User.class).getParentName());
           System.out.println("xStream序列化的子节长度"+ new String(xml.serialize(user)));
           System.out.println("json序列化的子节长度"+json.serialize(user)+new String(json.serialize(user)));


           System.out.println("hessian序列化的子节长度"+new String(hessian.serialize(user)));






    }

}
