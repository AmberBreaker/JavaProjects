package com.blairscott.patterns.factory.abstractfactory.factory;

import com.blairscott.patterns.factory.abstractfactory.bean.Battery;
import com.blairscott.patterns.factory.abstractfactory.bean.HuaweiBattery;
import com.blairscott.patterns.factory.abstractfactory.bean.HuaweiScreen;
import com.blairscott.patterns.factory.abstractfactory.bean.Screen;

public class HuaweiFactory extends MobileFactory {

    @Override
    public Screen createScreen() {
        return new HuaweiScreen();
    }

    @Override
    public Battery createBattery() {
        return new HuaweiBattery();
    }
}
