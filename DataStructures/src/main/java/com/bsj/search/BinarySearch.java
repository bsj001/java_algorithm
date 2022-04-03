package com.bsj.search;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,4,6,10,14,61,61,61,61,20};
//        int index = binarySearch(arr,0,arr.length-1,61);
        ArrayList<Integer> arrayList = binarySearch2(arr, 0, arr.length - 1, 61);
        if(arrayList.size() == 0){
            System.out.println("没找到!!!");
        }else{
            System.out.println("找到了，下标是"+arrayList);
        }
    }
    public static int binarySearch(int[] arr,int left,int right,int findValue){
        if(left>right){//没找到
            return -1;
        }
        //二分查找
        int mid = (left+right)/2;
        int midValue = arr[mid];
        if(findValue>midValue){
            return binarySearch(arr,mid+1,right,findValue);
        }else if(findValue<midValue){
            return binarySearch(arr,left,mid-1,findValue);
        }else {
            return mid;
        }
    }

    //升级，返回所有满足条件的下标
    public static ArrayList<Integer> binarySearch2(int[] arr,int left,int right,int findValue){
        if(left>right){//没找到
            return new ArrayList<Integer>();
        }
        //二分查找
        int mid = (left+right)/2;
        int midValue = arr[mid];
        if(findValue>midValue){
            return binarySearch2(arr,mid+1,right,findValue);
        }else if(findValue<midValue){
            return binarySearch2(arr,left,mid-1,findValue);
        }else {
            /*
            * 思路：
            *   1，在找到mid索引值，不要马上返回
            *   2,向mid索引值的左边扫描，将所有满足findValue的元素的下标，加入到集合ArrayList
            *   3,向mid索引值的右边扫描，将所有满足findValue的元素的下标，加入到集合ArrayList
            *   4,将ArrayList返回
            * */
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //2,向mid索引值的左边扫描，将所有满足findValue的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true){
                if(temp < 0 || arr[temp] != findValue){
                    break;
                }
                //否则，就temp放入到resIndexList
                resIndexList.add(temp);
                temp--;//
            }
            resIndexList.add(mid);
            //3,向mid索引值的右边扫描，将所有满足findValue的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true){
                if(temp < arr.length-1 || arr[temp] != findValue){
                    break;
                }
                //否则，就temp放入到resIndexList
                resIndexList.add(temp);
                temp++;//
            }
            resIndexList.add(mid);
            return resIndexList;
        }
    }
}
