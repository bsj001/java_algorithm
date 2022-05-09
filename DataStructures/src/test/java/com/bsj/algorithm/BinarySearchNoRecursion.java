package com.bsj.algorithm;

public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1,3,8,11,67,100};
        int index = binarySearch(arr, 100);
        System.out.println("index="+index);
    }
    //二分查找的非递归实现
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left+right)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid - 1;//向左边查找
            }else{
                left = mid + 1;//向右边查找
            }
        }
        return -1;
    }
}
