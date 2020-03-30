package com.blairscott.patterns.factory.abstractfactory.factory;

import com.blairscott.patterns.factory.abstractfactory.bean.Battery;
import com.blairscott.patterns.factory.abstractfactory.bean.Screen;

public abstract class MobileFactory {

    public abstract Screen createScreen();

    public abstract Battery createBattery();

}
