package com.blairscott.patterns.factory.factorymethod.demo;

import com.blairscott.patterns.factory.factorymethod.bean.Animal;
import com.blairscott.patterns.factory.factorymethod.factory.AnimalFactory;
import com.blairscott.patterns.factory.factorymethod.factory.CowFactory;
import org.junit.Test;

public class Demo {

    @Test
    public void testFactoryMethod() {
        AnimalFactory factory = new CowFactory();
        Animal animal = factory.createAnimal();
        animal.eat();
        System.out.println(animal.voice());
    }
}
