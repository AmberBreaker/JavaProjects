package com.blairscott.patterns.factory.simplefactory;

import com.blairscott.patterns.factory.simplefactory.beans.Cat;
import com.blairscott.patterns.factory.simplefactory.beans.Cow;

/**
 * 简单工厂
 * spring就是使用了简单工厂实现了IOC容器。
 */
public class SimpleFactory {

    /**
     * 以下的代码虽然是采取了简单工厂的写法，但是它存在的问题也很大：
     * 1、违反了开闭原则
     *      当新增一个实体的时候，就不得不修改代码以满足需求。
     * 2、该类太"累"，过于"重量级"。
     *
     * 解决方案1：Spring有一套改造方案。
     * 解决方案2：其他工厂方法模式。
     */
    public Object getAnimalByName(String name) {
        if ("cat".equals(name)) {
            return new Cat();
        } else if ("cow".equals(name)) {
            return new Cow();
        } else if ("...".equals(name)) {
            // ...
        }
        return null;
    }

}
