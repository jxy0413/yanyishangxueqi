package com.atguigu.shiyan;

import java.util.Scanner;

/**
 * Created by Cookie on 2019/10/7.
 */
public class MonthCount {
    public static void main(String[] args) {
        System.out.println("请输入月份：");
        Scanner sc =new Scanner(System.in);
        int num = sc.nextInt();
        int days = Month(num);
        System.out.println("该月有 "+days+"天");

    }

    public static int Month(int month){
        switch (month){
            case 1:
                return 31;
            case 2:
                return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
            default:
                return -1;
        }
    }
}
