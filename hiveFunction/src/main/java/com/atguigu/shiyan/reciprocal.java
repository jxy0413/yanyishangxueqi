package com.atguigu.shiyan;

import java.util.Scanner;

/**
 * Created by Cookie on 2019/10/7.
 * 编写一个求倒数的程序
 */
public class reciprocal {
    public static void main(String[] args) {
        System.out.println("请从键盘输入数字: ");
        Scanner sc =new Scanner(System.in);
        double num = sc.nextDouble();

        //求倒数
        double reciprocalNum=1/num;

        //输出
        System.out.println("得到的倒数为："+reciprocalNum);
    }
}
