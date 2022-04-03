package com.bsj.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
//        int arr[] = {101,34,119,1,234,234,2342,4,242,4,24,2,4,242,4,24,2,35,2,5,235};
        int arr[] = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);// 生成80000个[0,80000)的整数
        }
        //排序前的时间为
        long time1 = System.currentTimeMillis();
        insertSort(arr);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)+"毫秒");
    }
    //插入排序
    public static void insertSort(int arr[]){
        int value;
        int insertIndex;
        for(int i=1;i<arr.length;i++){
            //定义一个待插入的数
            value = arr[i];
            insertIndex = i - 1;

            //给insertIndex找到插入的位置
            //insertIndex >= 0保证不越界
            //value < arr[insertIndex]寻找要插入的数的位置
            while(insertIndex >= 0 && value < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];//后移
                insertIndex--;//
            }
            arr[insertIndex + 1] = value;
            //优化：
//            if(insertIndex + 1 == i){
//                arr[insertIndex + 1] = value;
//            }
//            System.out.printf("第%d轮插入排序后的数组为：%s\n",i+1, Arrays.toString(arr));
        }
    }
}
