package com.bsj.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70};
        int[] arr = new int[800000]; //8   80000
        for(int i=0;i<800000;i++){
            arr[i] = (int)(Math.random()*800000);// 生成80000个[0,80000)的整数
        }
        //排序前的时间为
        long time1 = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)+"毫秒");
//        System.out.println("快排后的数组为:"+ Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r = right;//右下标
        //中轴值
        int pivot = arr[(left+right)/2];
        int temp;//临时值，作为交换
        //while循环的目的是让比pivot小的值放在左边，比pivot大的值放在右边
        while(l<r){
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while(arr[l]<pivot){
                l+=1;
            }
            //在pivot的右边一直找，找到小于等于pivot值，才退出
            while(arr[r]>pivot){
                r-=1;
            }
            //如果 l >= r, 说明pivot的左右两边的值，已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值排序
            if(l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完，发现 arr[l] == pivot,则 l --
            if(arr[l] == pivot){
                r--;
            }
            //如果交换完，发现 arr[r] == pivot ，则r++
            if(arr[r] == pivot){
                l++;
            }
        }
        //如果l == r,必须 r--,l++;否则为出现栈溢出
        if(l == r){
            l+=1;
            r-=1;
        }
        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right > l){
            quickSort(arr,l,right);
        }
    }

}
