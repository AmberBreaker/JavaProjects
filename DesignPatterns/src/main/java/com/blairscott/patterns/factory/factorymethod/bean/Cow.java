package com.blairscott.patterns.factory.factorymethod.bean;

public class Cow extends Animal {
    @Override
    public void eat() {
        System.out.println("grass");
    }

    @Override
    public String voice() {
        return "mou";
    }
}
