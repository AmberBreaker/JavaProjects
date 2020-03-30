package com.blairscott.patterns.factory.factorymethod.factory;

import com.blairscott.patterns.factory.factorymethod.bean.Animal;
import com.blairscott.patterns.factory.factorymethod.bean.Cow;

public class CowFactory implements AnimalFactory {

    @Override
    public Animal createAnimal() {
        return new Cow();
    }
}
