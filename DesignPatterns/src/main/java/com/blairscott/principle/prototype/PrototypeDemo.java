package com.blairscott.principle.prototype;

import org.junit.Test;

public class PrototypeDemo {

    @Test
    public void testShallowCopy() {
        Student student = new Student("Bob", 20, null);
        Teacher teacher = new Teacher("Johnson", 30, student);
        try {
            Teacher cloneTeacher = teacher.shallowCopyBean();
            teacher.setName("blair");
            teacher.setAge(10);
            student = new Student("Edward", 25, null);
            teacher.setStudent(student);

            System.out.println("be cloned bean: " + teacher.toString());
            System.out.println("clone bean: " + cloneTeacher.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
