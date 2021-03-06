package com.bsj.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i] = i+1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println(index);
    }

    //插值查找
    //需要数组是有序的
    public static int insertValueSearch(int[] arr,int left,int right,int findValue){
        if(left>right || findValue < arr[0] || findValue > arr[arr.length-1]){
            return -1;
        }
        //mid自适应
        int mid = left + (right-left) * (findValue -arr[left])/(arr[right]-arr[left]);
        int midValue = arr[mid];

        if(findValue<midValue){
            return insertValueSearch(arr,left,mid-1,findValue);
        }else if(findValue>midValue){
            return insertValueSearch(arr,mid+1,right,findValue);
        }else{
            return mid;
        }
    }
}
