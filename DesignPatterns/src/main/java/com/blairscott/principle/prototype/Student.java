package com.blairscott.principle.prototype;

import java.io.*;

public class Student implements Serializable {

    public Student(String name, int age, Teacher teacher) {
        this.name = name;
        this.age = age;
        this.teacher = teacher;
    }

    /* 深拷贝*/
    public Student deepCopyBean() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Student) ois.readObject();
    }

    private String name;

    private int age;

    private Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "student[name=" + name + ", age=" + age + "], teacher[name=" + teacher.getName() + ", age=" + teacher.getAge() + "]";
    }
}
