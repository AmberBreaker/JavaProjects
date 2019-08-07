package com.shrm.basic;

import java.util.Date;

public class JavaFunctions {


    public static <T> T getTest(Object obj, Class<T> valueType) {
        return valueType.cast(obj);
    }

    public static void main(String[] args) {
        String test = getTest(new Date(), String.class);
    }
}
