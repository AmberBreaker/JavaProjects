package com.blairscott;

import com.blairscott.patterns.adapter.ConcreteTarget;
import com.blairscott.patterns.adapter.Target;
import org.junit.Test;

public class TestFunctions {

    @Test
    public void testAdapter() {
        Target target = new ConcreteTarget();
        target.request();
    }

}
