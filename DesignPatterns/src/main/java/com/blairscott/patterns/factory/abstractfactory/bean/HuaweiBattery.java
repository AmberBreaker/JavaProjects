package com.blairscott.patterns.factory.abstractfactory.bean;

public class HuaweiBattery extends Battery {
    @Override
    public String material() {
        return "materialA";
    }

    @Override
    public double price() {
        return 100;
    }
}
