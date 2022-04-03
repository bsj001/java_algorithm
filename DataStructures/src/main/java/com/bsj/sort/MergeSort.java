package com.bsj.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr= {8,4,5,7,1,3,6,2,24,2424,24,24,2,424252,5,35325,3};
        int[] arr = new int[800000]; //8   80000
        for(int i=0;i<800000;i++){
            arr[i] = (int)(Math.random()*800000);// 生成800000个[0,800000)的整数
        }
        //排序前的时间为
        long time1 = System.currentTimeMillis();
        int[] temp = new int[arr.length];//归并排序需要一个额外的空间
        mergeSort(arr,0,arr.length-1,temp);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)+"毫秒");

//        System.out.println("归并排序后的数组为："+ Arrays.toString(arr));
    }
    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid = (left + right)/2;
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,right,mid,temp);
        }
    }
    public static void merge(int[] arr,int left,int right,int mid,int[] temp){
        //
        int i = left;//初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //一、先把左右两边（有序）的数据按照规则直译到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i<=mid && j<= right){
            if(arr[i]<=arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else{
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //二、把剩余数据的一边的数据依次全部填充到temp
        //把左边剩下的数据填充到temp
        while(i<=mid){
            temp[t] = arr[i];
            t++;
            i++;
       }
        //把右边剩下的数据填充到temp
        while(j<=right){
            temp[t] = arr[j];
            t++;
            j++;
        }
        //三、将temp数组的元素拷贝到arr,
        //注意并不是第一次都拷贝
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){//第一次合并tempLeft = 0,right =1;//tempLeft = 2,right = 3 ,
            //最后一次合并是tempLeft = 0,right = 7
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
