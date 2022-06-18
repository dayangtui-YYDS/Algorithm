package com.yc;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BitOperation {
    enum JinZhi{
        // 二进制
        Binary,
        // 八进制
        Octal,
        // 十进制
        Decimal,
        //十六进制
        Hex,
    }

    @Test
    public void Test(){
        int totalCount =1000;
        for (int i =0; i<totalCount; ++i){
            //int[] arr = RandomArray(10,100);
//            int[] arr = {9,7,4,6};
            //int index = GetLeftIndexOfGreateEqual(arr,num);
//            int index = GetRightIndexOfLessEqual(arr,num);
//            if (!CheckLeftIndexOfGreateEqual(arr,num, index)) {
//                System.out.print("find error");
//            }
//            if (!CheckRightIndexOfLessEqual(arr, num, index)) {
//                System.out.print("find error");
//                System.out.print(arr.toString()+ " ");
//                System.out.print(index);
//            }
//            System.out.println();

//            int index = GetIndexOfLocalMin(arr);
//            if (!CheckIndexOfLocalMin(arr, index)) {
//                System.out.print("check error");
//            }

            int a =Random(1000);
            int b =Random(1000);
//            if (!CheckAdd(a, b)) {
//                System.out.println("add error");
//            }

//            if (!CheckSubtract(a, b)) {
//                System.out.println("Subtract error");
//            }

//            if (!CheckMultiply(a, b)) {
//                System.out.println("Multiply error");
//            }

            if (!CheckDivide(a, b)) {
                System.out.println("Divide error");
            }
        }

    }

    // 随机生成-value~value之间的整数
    private int Random(int value){
        return (int)(Math.random()*2*(value+1)) -value;
    }

    // 位运算实现加法
    private int Add(int a,int b){
        int add;
        int carry;
        do {
            add = a^b; //无进位相加
            carry = (a&b)<<1; //进位
            a = add;
            b = carry;
        }while (carry!=0);
        return add;
    }

    private boolean CheckAdd(int a,int b){
        int result = Add(a,b);
        System.out.println(String.format("%d + %d = %d",a,b,result));
        return a+b == result;
    }

    // 位运算实现减法
    private int Subtract(int a,int b){
        return Add(a,Add(~b,1));
    }

    private boolean CheckSubtract(int a,int b){
        int result = Subtract(a,b);
        System.out.println(String.format("%d - %d = %d",a,b,result));
        return result == a-b;
    }

    private boolean CheckMultiply(int a,int b){
        int result = a * b;
        System.out.println(String.format("%d * %d = %d",a,b,result));
        return result == Multiply(a,b);
    }

    // 位运算实现乘法
    private int Multiply(int a,int b){
        //取a的绝对值
        int i1 = a >> 31; //i为0 或-1
        int abs1 = (a^i1) - i1; // a的绝对值
        int i2 = b >> 31;
        int abs2 = (b^i2) - i2; // b的绝对值
        if ((i1 ^ i2) == 0) {
            return MultiplyImpl(abs1,abs2);
        }
        return -MultiplyImpl(abs1,abs2);
    }

    // 位运算实现乘法
    private int MultiplyImpl(int a,int b){
        int sum = 0;
        do {
            if ((b & 1) !=0) {
                sum = Add(a,sum);
            }

            b>>=1;
            a<<=1;
        }while (b != 0);
        return sum;
    }

    private boolean CheckDivide(int a,int b){
        int result = Divide(a,b);
        System.out.println(String.format("%d / %d = %d",a,b,result));
        return result == (a/b);
    }

    // 位运算实现除法
    private int Divide(int a,int b){
        if (b==0) {
            throw new ArithmeticException("divide zero");
        }

        //取a的绝对值
        int i1 = a >> 31; //i为0 或-1
        int abs1 = Subtract(a^i1,i1);// a的绝对值
        int i2 = b >> 31;
        int abs2 = Subtract(b^i2,i2);// b的绝对值
        if ((i1 ^ i2) == 0) {
            return DivideImpl(abs1,abs2);
        }
        return -DivideImpl(abs1,abs2);
    }

    // 位运算实现除法
    private int DivideImpl(int a,int b){

        int result = 0;
        for (int i =30;i >=0; i = Subtract(i,1)){
            if ((a>>i) >= b) {
                a = Subtract(a,b<<i);
                result = Add(result,1<<i);
            }
        }
        return result;
    }


    // 获取数组局部最小值的索引
    private int GetIndexOfLocalMin(int[] arr){
        if (arr == null || arr.length == 0) return -1;
        if (arr.length == 1) return 0;

        int L= 0;
        int R = arr.length -1;
        if (arr[L] < arr[L+1]) return L;
        if (arr[R-1] > arr[R]) return R;

        while (L < R){
            int M = L + ((R - L) >> 1);
            if (arr[M] < arr[M-1] && arr[M] < arr[M+1]) {
                return M;
            }

            if (arr[M] > arr[M-1]) {
                R = M;
                continue;
            }

            if (arr[M] > arr[M+1]) {
                L = M;
                continue;
            }
        }
        return -1;
    }

    // 检查index是否为局部最小
    private boolean CheckIndexOfLocalMin(int[] arr,int index){
        if (arr==null || arr.length==0) return index == -1;

        boolean left = index -1 >=0 ? arr[index]< arr[index-1]:true;
        boolean right = index +1 < arr.length? arr[index] < arr[index+1]:true;
        return left &&right;
    }

    //在有序数组中找到大于等于num的左边界
    private int GetLeftIndexOfGreateEqual(int[] arr, int num){
        if (arr == null || arr.length == 0) return -1; //不存在

        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R){
            int M = L + ((R-L) >> 1);
            if (arr[M] >= num) {
                index = M;
                R = M - 1;
            }else {
                L = M + 1;
            }
        }

        return index;
    }

    // 检查有序数组中大于等于num的左边界是否正确
    private boolean CheckLeftIndexOfGreateEqual(int[] arr, int num, int index){
        for (int i = 0; i< arr.length; ++i) {
            if (arr[i] >= num) {
                return index ==i;
            }
        }

        return index==-1;
    }

    // 在有序数组中找到小于等于num的右边界
    private int GetRightIndexOfLessEqual(int[] arr,int num){
        if (arr == null || arr.length == 0) return -1; //不存在

        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R){
            int M = L + ((R-L) >> 1);
            if (arr[M] <= num) {
                index = M;
                L = M + 1;
            }else {
                R = M - 1;
            }
        }

        return index;
    }

    // 检查有序数组中小于等于num的右边界是否正确
    private boolean CheckRightIndexOfLessEqual(int[] arr, int num, int index){
        for (int i = arr.length - 1; i>=0 ; --i) {
            if (arr[i] <= num) {
                return index ==i;
            }
        }

        return index==-1;
    }

    // 随机生成一个有序数组
    private int[] RandomSortedArray(int maxLength, int maxValue){
        int length = (int)(Math.random()*(maxLength+1));
        if (length <=0) return new int[]{};

        int[] arr = new int[length];
        arr[0] = (int) (Math.random()*(maxValue+1));
        for (int i=1; i< arr.length;  i++) {
            do {
                arr[i] = (int) (Math.random()*(maxValue+1));

            }while (arr[i-1] > arr[i]);
        }
        return arr;
    }

    /**
     * 随机生成相邻不等的数组
     * */
    private int[] RandomArray(int maxLength, int maxValue){
        int length = (int)(Math.random()*(maxLength+1));
        if (length <=0) return new int[]{};

        int[] arr = new int[length];
        arr[0]= (int) (Math.random()*(maxValue+1));
        for (int i=1; i< arr.length; ++i){

            do {
                arr[i] = (int) (Math.random()*(maxValue+1));
            }while (arr[i] == arr[i-1]);
        }
        return arr;
    }
}