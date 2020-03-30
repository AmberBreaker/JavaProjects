package com.blairscott.patterns.factory.abstractfactory.demo;

import com.blairscott.patterns.factory.abstractfactory.bean.Battery;
import com.blairscott.patterns.factory.abstractfactory.bean.Screen;
import com.blairscott.patterns.factory.abstractfactory.factory.HuaweiFactory;
import com.blairscott.patterns.factory.abstractfactory.factory.IphoneFactory;
import com.blairscott.patterns.factory.abstractfactory.factory.MobileFactory;

public class Demo {
    public void testAbstractFactory() {
        MobileFactory iphoneFactory = new IphoneFactory();
        Battery battery = iphoneFactory.createBattery();
        System.out.println(battery.material() + " == " + battery.price());

        MobileFactory huaweiFactory = new HuaweiFactory();
        Screen screen = huaweiFactory.createScreen();
        System.out.println(screen.material() + " == " + screen.price());
    }
}