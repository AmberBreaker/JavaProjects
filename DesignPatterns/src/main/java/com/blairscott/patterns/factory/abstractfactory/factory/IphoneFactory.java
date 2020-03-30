package com.blairscott.patterns.factory.abstractfactory.factory;

import com.blairscott.patterns.factory.abstractfactory.bean.Battery;
import com.blairscott.patterns.factory.abstractfactory.bean.IphoneBattery;
import com.blairscott.patterns.factory.abstractfactory.bean.IphoneScreen;
import com.blairscott.patterns.factory.abstractfactory.bean.Screen;

public class IphoneFactory extends MobileFactory {
    @Override
    public Screen createScreen() {
        return new IphoneScreen();
    }

    @Override
    public Battery createBattery() {
        return new IphoneBattery();
    }
}
