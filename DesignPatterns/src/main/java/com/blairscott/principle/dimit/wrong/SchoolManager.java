package com.blairscott.principle.dimit.wrong;

import com.blairscott.principle.dimit.bean.CollageEmployee;
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
        /**
         * List<CollageEmployee>
         * 不是成员变量。
         * 不是方法的形参。
         * 不是方法的返回类型。
         *
         * 所以不符合迪米特法则。
         */
        List<CollageEmployee> collageEmployeeList = sub.getCollageEmployeeList();
        System.out.println(" ------ 学院员工 ------ ");
        for (CollageEmployee collageEmployee : collageEmployeeList) {
            System.out.print(collageEmployee.getId() + " ");
        }
        System.out.println();

        /**
         * 满足迪米特法则。
         */
        System.out.println(" ------ 学校总部员工 ------");
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
