package com.bsj.search;

public class SeqSearch {
    //线性查找，是逐一比对，如果找到，就返回下标
    public static void main(String[] args) {
        int[] arr = {1,9,11,-1,34,89};
        int index = seqSearch(arr,111);
        if(index == -1){
            System.out.println("没找到!!!");
        }else{
            System.out.println("找到了，下标是"+index);
        }
    }
    public static int seqSearch(int[] arr,int value){
        //这里是找到一个就返回，如果要找到多个，就加个数组
        for(int i=0;i<arr.length;i++){
            if( value == arr[i]){
                return i;
            }
        }
        return -1;
    }
}
