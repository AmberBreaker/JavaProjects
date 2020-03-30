package com.blairscott.patterns.factory.factorymethod.bean;

public class Cat extends Animal {

    @Override
    public void eat() {
        System.out.println("fish");
    }

    @Override
    public String voice() {
        return "miao";
    }
}
