package com.shrm.service;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int tmp = 2;

        System.out.print(num + "=");
        while (tmp <= num) {
            if (num / tmp == 1) {
                System.out.println(num);
                break;
            } else if (num % tmp == 0) {
                System.out.print(tmp + "*");
                num /= tmp;
            } else {
                tmp++;
            }
        }
    }
}
