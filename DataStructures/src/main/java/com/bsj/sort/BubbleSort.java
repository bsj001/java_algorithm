package com.bsj.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3,9,-1,10,2,5};
//        int arr[] = {-1, 2, 3, 5, 9, 10};
        //测试排序的时间复杂度为O(n2),生成80000个数据
        int arr[] = new int[80000];
        for(int i=0;i<80000;i++){
            arr[i] = (int)(Math.random()*80000);// 生成80000个[0,80000)的整数
        }
        //排序前的时间为
        long time1 = System.currentTimeMillis();
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间为："+format);
        //排序
        bubbleSort(arr);
        //排序后的时间
//        Date date2 = new Date();
//        String format1 = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间为:"+format1);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)/1000+"秒");
    }
    public static void bubbleSort(int arr[]){
        boolean flag = false;//标识变量，是否进行过交换
        int temp;
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                //如果前面的数大于后面的数
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
//            System.out.printf("第%d趟排序后的数组为:%s\n",i+1,Arrays.toString(arr));
            if(!flag){//没有发生交换
                break;
            }else{
                flag = false;
            }
        }
    }

}
