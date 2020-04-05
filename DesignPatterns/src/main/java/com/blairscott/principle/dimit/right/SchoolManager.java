package com.blairscott.principle.dimit.right;

import com.blairscott.principle.dimit.bean.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SchoolManager {

    public List<Employee> getEmployeeList() {
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            list.add(employee);
        }
        return list;
    }

    public void printEmployeeInfo(CollageManager sub) {
        sub.printCollageEmployeeInfo();
        /**
         * 满足迪米特法则。
         */
        System.out.println("------- 学校总部员工 -------");
        List<Employee> employeeList = this.getEmployeeList();
        for (Employee employee : employeeList) {
            System.out.print(employee.getId() + " ");
        }
    }

    @Test
    public void test() {
        printEmployeeInfo(new CollageManager());
    }
}
