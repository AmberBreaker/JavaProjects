package com.blairscott.patterns.singleton;

/**
 * 懒汉式之静态内部类
 */
public class StaticSingleton {

    private StaticSingleton() {}

    private static class StaticSingletonFactory {
        private static StaticSingleton staticSingleton = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return StaticSingletonFactory.staticSingleton;
    }

}
