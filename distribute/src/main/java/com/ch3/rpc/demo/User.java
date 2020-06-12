package com.ch3.rpc.demo;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author wz
 * @version v1.0
 * @description
 * @datetime 2020/3/12 0:08
 * @since JDK 1.8
 */
public class User extends  Person implements  Serializable {

    private String name;

    private int age;

    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeObject(name);
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        name=(String)s.readObject();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
