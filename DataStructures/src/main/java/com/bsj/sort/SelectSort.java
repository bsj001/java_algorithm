package com.bsj.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
//        int arr[] = {2,-1,10,5,0};
        int arr[] = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);// 生成80000个[0,80000)的整数
        }
        //排序前的时间为
        long time1 = System.currentTimeMillis();
        selectSort(arr);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)/1000+"秒");
    }

    public static void selectSort(int arr[]){
        //先找最小的数和下标
        for(int i=0;i<arr.length-1;i++){
            int min = arr[i];
            int index = i;
            for(int j=i+1;j<arr.length;j++){
                if(min>arr[j]){
                    index = j;
                    min = arr[j];
                }
            }
            //交换
            if(index != i){
                arr[index] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+(i+1)+"趟后的数组是："+ Arrays.toString(arr));
        }
    }
}
