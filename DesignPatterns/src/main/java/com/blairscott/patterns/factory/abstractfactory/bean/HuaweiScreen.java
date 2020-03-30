package com.blairscott.patterns.factory.abstractfactory.bean;

public class HuaweiScreen extends Screen {
    @Override
    public String material() {
        return "materialC";
    }

    @Override
    public double price() {
        return 200;
    }
}
