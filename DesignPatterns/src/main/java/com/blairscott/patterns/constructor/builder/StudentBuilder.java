package com.blairscott.patterns.constructor.builder;

import com.blairscott.patterns.constructor.product.Father;
import com.blairscott.patterns.constructor.product.Student;

public class StudentBuilder {

    private Student student = new Student();

    public StudentBuilder name(String name) {
        student.setName(name);
        return this;
    }

    public StudentBuilder age(int age) {
        student.setAge(age);
        return this;
    }

    public StudentBuilder sex(char sex) {
        student.setSex(sex);
        return this;
    }

    public StudentBuilder score(int score) {
        student.setScore(score);
        return this;
    }

    public StudentBuilder father(String name, int age) {
        Father father = new Father();
        father.setName(name);
        father.setAge(age);
        student.setFather(father);
        return this;
    }

    public Student build() {
        return student;
    }
}
