package com.bsj.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
//        int arr[] = {8,9,1,7,2,3,5,4,6,0};
        int arr[] = new int[800000]; //8   80000
        for(int i=0;i<800000;i++){
            arr[i] = (int)(Math.random()*800000);// 生成80000个[0,80000)的整数
        }
        //排序前的时间为
        long time1 = System.currentTimeMillis();
        shellSort2(arr);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)+"毫秒");
    }
    //交换法
    public static void shellSort(int arr[]){
        int temp = 0;
        //先分组
        for(int gap = arr.length/2;gap>0;gap/=2){
            //分成gap组，每组开始排序
            for(int i=gap;i<arr.length;i++){
                //每组排序
                for(int j = i-gap;j>=0;j -=gap){
                    if(arr[j]>arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
//        System.out.println("希尔排序后的数组为："+ Arrays.toString(arr));
    }

    //移动法
    public static void shellSort2(int arr[]){
        int temp;
        int j;
        //先分组
        for(int gap = arr.length/2;gap>0;gap/=2){
            //分成gap组，每组开始排序
            for(int i=gap;i<arr.length;i++){
                j = i;
                temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0 && temp < arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
//        System.out.println("希尔排序后的数组为："+ Arrays.toString(arr));
    }
}
