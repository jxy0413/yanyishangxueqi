package com.atguigu.shiyan;

import java.util.Scanner;

/**
 * Created by Cookie on 2019/10/7.
 * 求符号函数sgn(x)的Java程序。（else-if语句。）
 */
public class sgnFunction {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {


            System.out.println("输入数字:");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            //调用函数
            int sgnNum = sgn(num);
            System.out.println(sgnNum);
            System.out.println("------------------");
        }
    }
    //sgn函数
    public static int sgn(int x){
        if(x>0){
            return 1;
        }else if(x==0){
            return 0;
        }else {
            return -1;
        }
    }
}
