package com.blairscott.patterns.adapter;

public class ConcreteTarget extends Adaptee implements Target {
    @Override
    public void request() {
        adapteeRequest();
    }
}
