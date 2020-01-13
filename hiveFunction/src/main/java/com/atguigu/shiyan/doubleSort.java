package com.atguigu.shiyan;

/**
 * Created by Cookie on 2019/10/7.
 */
public class doubleSort {
    public static void main(String[] args) {
        //初始String数组
        String str [] = {"12","3","43","13","41","2"};
        doubleSort(str);
    }

    public static void doubleSort(String str[]){
        if(str.length==0||str==null){
            System.out.println("没有数据");
        }
        //建立数组
        double num[] =new double[str.length];
        //将字符串数组中每一个元素转成双精度数组中的数据
        for(int i=0;i<str.length;i++){
            num[i]=Double.parseDouble(str[i]);
        }
        //打印num数组
        System.out.println("转化后的数组");
        for(double number:num){
            System.out.print(number+"  ");
        }
        //对数组进行冒泡排序
        bubbleSort(num);
        System.out.println();
        //打印num数组
        System.out.println("排序后数组：");
        for(double number:num){
            System.out.print(number+"  ");
        }

    }

    //冒泡排序
    public static  void bubbleSort(double [] arr){
        //小于或只有一个元素 不用排队
        if(arr.length<=1){
            return;
        }
        for(int i=0;i<arr.length;i++){
            boolean falg=false;
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    double temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    falg = true;
                }
            }
            //没有交换的话
            if(!falg){
                break;
            }
        }
    }
}
