package com.shrm.factory;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component // 当前类也是单例
public class SingletonFactory {

    private volatile SingletonBean bean;

    private AtomicLong id;

    private SingletonFactory() {
    }

    public SingletonBean getInstance() {
        if (bean == null) {
            synchronized (this) {
                if (bean == null) {
                    bean = new SingletonBean();
                }
            }
        }
        return bean;
    }

    @Data
    public class SingletonBean {

        private Integer id;

        private String name;

        private SingletonBean() {
        }

    }
}
