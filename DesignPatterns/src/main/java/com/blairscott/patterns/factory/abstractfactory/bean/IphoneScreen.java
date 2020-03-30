package com.blairscott.patterns.factory.abstractfactory.bean;

public class IphoneScreen extends Screen {
    @Override
    public String material() {
        return "materialD";
    }

    @Override
    public double price() {
        return 300;
    }
}
