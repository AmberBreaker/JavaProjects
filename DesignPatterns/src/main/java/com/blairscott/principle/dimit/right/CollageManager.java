package com.blairscott.principle.dimit.right;

import com.blairscott.principle.dimit.bean.CollageEmployee;

import java.util.ArrayList;
import java.util.List;

public class CollageManager {

    public List<CollageEmployee> getCollageEmployeeList() {
        List<CollageEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CollageEmployee collageEmployee = new CollageEmployee();
            collageEmployee.setId(i);
            list.add(collageEmployee);
        }
        return list;
    }

    public void printCollageEmployeeInfo() {
        List<CollageEmployee> collageEmployeeList = this.getCollageEmployeeList();
        System.out.println("------- 学院员工 -------");
        for (CollageEmployee collageEmployee : collageEmployeeList) {
            System.out.print(collageEmployee.getId() + " ");
        }
        System.out.println();
    }
}
