package com.bsj.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        System.out.println("堆排序");
        //升序排序 -》 大顶堆
        int temp;
        int[] arr = {4,6,8,5,9};
//        int[] arr = {4,6,8,5,9,23,2342,423,433,234,-23};
        //将待排序序列构造成一个大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        System.out.println("数组="+ Arrays.toString(arr));
        //3，将其与末尾元素进行交换，此时末尾就为最大值
        //4，然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
        for(int j=arr.length-1;j>0;j--){
            //exchange
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
            System.out.println("数组="+ Arrays.toString(arr));
        }
//        System.out.println("数组="+ Arrays.toString(arr));
    }


    //将一个数组（二叉树），整理成大顶堆
    /**
     * @param arr 要整理的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整，length是在逐渐减少
     * */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1. k = i*2+1 k是i节点的左子节点
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1 <length && arr[k] < arr[k+1]){//说明左子节点的值小球右子节点的值
                k++;//k指向右子节点
            }
            if(arr[k] > temp){
                arr[i] = arr[k];//把较大的值赋给当前节点
                i = k;//i指向k，继续循环比较
            }else{
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
