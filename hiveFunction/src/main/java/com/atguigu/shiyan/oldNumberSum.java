package com.atguigu.shiyan;

import org.apache.derby.impl.store.access.sort.Scan;

import java.util.Scanner;

/**
 * Created by Cookie on 2019/10/7.
 * 求解奇数数列前N项累加和的Java程序
 */
public class oldNumberSum {

    public static void main(String[] args) {
        System.out.println("请输入数字：");
        Scanner sc =new Scanner(System.in);
        int num = sc.nextInt();
        int countSum = oldNumberSum(num);
        System.out.println("前n项奇数的和为 ："+countSum);
    }

    public static int oldNumberSum(int n){
        int sum=0 ;
        for(int i=1;i<=n;i=i+2){
            sum+=i;
        }
        return  sum;
    }
}
