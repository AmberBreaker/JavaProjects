package com.blairscott.principle.dimit.wrong;

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

}
