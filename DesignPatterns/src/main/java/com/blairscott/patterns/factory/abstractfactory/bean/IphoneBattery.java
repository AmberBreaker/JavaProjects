package com.blairscott.patterns.factory.abstractfactory.bean;

public class IphoneBattery extends Battery {
    @Override
    public String material() {
        return "materialB";
    }

    @Override
    public double price() {
        return 50;
    }
}
