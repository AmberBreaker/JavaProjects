package com.blairscott.patterns.singleton;

import org.junit.Test;

public class Demo {

    @Test
    public void test() {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        instance1.sayHallo();
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        System.out.println(instance1 == instance2);

    }
}
