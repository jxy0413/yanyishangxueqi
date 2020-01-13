package com.atguigu.shiyan;

/**
 * Created by Cookie on 2019/10/7.
 * 2最长二进制连续序列
 */
public class binarysquence {
    public static void main(String[] args) {
        String []num={"521"};
        binarySq(num);
    }


    public static void binarySq(String args[]){
        if(args.length==0||args==null){
            return;
        }
        //十进制的数
        int number = Integer.parseInt(args[0]);
        //将十进制转换为二进制
        String BinNumber = shiToBin(number);
        //转为数字
        int binNum = Integer.parseInt(BinNumber);
        System.out.println(binNum);
        //求num的二进制串中连续1串的最大长度
        int OneLength = OneLength(BinNumber);
        System.out.println("1串的最大长度为 ："+OneLength);
        //num的二进制串中连续0串的最大长度
        int zeroLength = ZeroLength(BinNumber);
        System.out.println("0串的最大长度为 ："+zeroLength);
    }

    /**
     * 将十进制转为二进制
     */
    public static String shiToBin(int number){
        String str="";
        while (number!=0){
            str=number%2+str;
            number=number/2;
        }
        return str;
    }

    /**
     * 求数组中连续1串最长的长度
     */
    public static int OneLength(String str){
        int count =1;
        int res=1;
        int max;
        for(int i=1;i<str.length();i++){
            char c1 = str.charAt(i-1);
            char c2 = str.charAt(i);
            if(c1==c2&&c1=='1'){
                count++;
            }else{
                if(count>res){
                    res=count;
                }
                count=1;
            }
        }
        max=count>res?count:res;
       return max;
    }

    /**
     * 求数组中连续0串最长的长度
     */
    public static int ZeroLength(String str){
        int count =1;
        int res=1;
        int max;
        for(int i=1;i<str.length();i++){
            char c1 = str.charAt(i-1);
            char c2 = str.charAt(i);
            if(c1==c2&&c1=='0'){
                count++;
            }else{
                if(count>res){
                    res=count;
                }
                count=1;
            }
        }
        max=count>res?count:res;
        return max;
    }
}
