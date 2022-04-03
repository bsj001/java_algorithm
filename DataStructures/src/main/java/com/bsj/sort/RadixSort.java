package com.bsj.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53,3,542,748,14,214};
        //生成800000个数据
        int arr[] = new int[800000]; //8   80000
        for(int i=0;i<800000;i++){
            arr[i] = (int)(Math.random()*800000);// 生成80000个[0,80000)的整数
        }
        long time1 = System.currentTimeMillis();
        //基数排序会耗费大量内存，如果是8千万个数据，则会耗费80000000 * 11 * 4 /1024/1024/1024 = 3.3GB内存，可能会报错
        radixSort(arr);
        long time2 = System.currentTimeMillis();
        System.out.println("time2-time1="+(time2-time1)+"毫秒");
    }

    //基数排序方法
    public static void radixSort(int[] arr){
        //1，得到数组中最大的数的位数
        int max = arr[0];//假设第一数就是最大数
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }
        //2，得到最大数的位数
        int maxLength = (max + "").length();
        /*
        * 说明：
        *   1，二维数组包含10个一维数组
        *   2，为了防止放入数的时候，数年溢出，则每个一维数组（桶），大小定为arr.length
        *   3，明确，基数排序是使用空间换时间的经典算法
        * */
        int[][] bucket = new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少数据，我们定义一个一维数组来记录各个桶的每次放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //3,桶排序
        for(int i=0,n=1;i<maxLength;i++,n*=10){
            for(int j=0;j<arr.length;j++){
                //取出每个元素的个位的值放入对应的桶中
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //遍历每一桶，并将桶中的数据，放入到原数组
            for(int k=0;k<bucketElementCounts.length;k++){
                //如果桶中有数据，我们才放入到原数组中
                if(bucketElementCounts[k] != 0){
                    //循环该桶即将第k个桶（即第k个一维数组），放入
                    for(int l=0;l<bucketElementCounts[k];l++){
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                    //清零
                    bucketElementCounts[k] = 0;
                }
            }
//            System.out.printf("第%d次排序后的数组：%s\n",i+1,Arrays.toString(arr));
        }
    }
}
