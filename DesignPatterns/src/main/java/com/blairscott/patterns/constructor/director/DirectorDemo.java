package com.blairscott.patterns.constructor.director;

import com.blairscott.patterns.constructor.builder.StudentBuilder;
import com.blairscott.patterns.constructor.product.Student;
import org.junit.Test;

public class DirectorDemo {

    @Test
    public void doDirector() {
        StudentBuilder builder = new StudentBuilder();
        builder.name("Lucy").age(18).sex('F').score(80).father("Bob", 45);
        Student student = builder.build();
        System.out.println(student);
    }
}
