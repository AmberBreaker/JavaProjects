package com.blairscott.patterns.singleton;

import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;

public class SingletonAttack {

    @Test
    public void reflectionAttack() {
        try {
            Constructor<StaticSingleton> constructor = StaticSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            StaticSingleton singleton1 = constructor.newInstance();
            StaticSingleton singleton2 = constructor.newInstance();
            System.out.println(singleton1 == singleton2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void serializeAttack() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/Users/blairscott/Desktop/test.txt"));
            StaticSingleton s1 = StaticSingleton.getInstance();
            oos.writeObject(s1);

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/blairscott/Desktop/test.txt"));
            StaticSingleton s2 = (StaticSingleton) ois.readObject();
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
