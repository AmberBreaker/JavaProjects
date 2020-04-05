package com.blairscott.principle.prototype;

public class Teacher implements Cloneable {

    public Teacher(String name, Integer age, Student student) {
        this.name = name;
        this.age = age;
        this.student = student;
    }

    /* 浅拷贝*/
    public Teacher shallowCopyBean() throws CloneNotSupportedException {
        return (Teacher) super.clone(); // 调用C函数，对内存中对对象进行复制。
    }

    private String name;

    private Integer age;

    private Student student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "teacher[name=" + name + ", age=" + age + "], student[name=" + student.getName() + ", age=" + student.getAge() + "]";
    }
}
