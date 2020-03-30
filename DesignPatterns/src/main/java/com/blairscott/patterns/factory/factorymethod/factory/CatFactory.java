package com.blairscott.patterns.factory.factorymethod.factory;

import com.blairscott.patterns.factory.factorymethod.bean.Animal;
import com.blairscott.patterns.factory.factorymethod.bean.Cat;

public class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}
