package com.bsj.search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};
        int index = fibSearch(arr, 1234);
        System.out.println(index);
    }
    //我们需要一个斐波那契数列
    //使用非递归方法得到一个斐波那契数列
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i=2;i<maxSize;i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }
    //编写斐波那契查找算法
    public static int fibSearch(int[] a,int key){
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        while(high > f[k] -1){
            k++;
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a,f[k]);
        //实际上需求使用a数组最后的数填充 temp
        //如：temp = {1,8,10,89,1000,1234,0,0,0} => {1,8,10,89,1000,1234,1234,1234,1234};
        for(int i=high+1;i<temp.length;i++){
            temp[i] = a[high];
        }
        //循环
        while(low<=high){
            mid = low + f[k-1] - 1;
            if(key<temp[mid]){//我们应该继续向数组的前面查找（左边）
                high = mid - 1;
                /*
                * 为什么是k--
                * 说明：
                *   1，全部元素 = 前面的元素 + 后面的元素
                *   2，f[k] = f[k-1] + f[k-2]
                * 因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                * 即 在f[k-1]的前面继续查找 k--
                *即 下次循环 mid = f[k-1-1] - 1
                * */
                k--;
            }else if(key > temp[mid]){
                low = mid + 1;
                k -= 2;
            }else {
                //需要确定，返回的是哪个下标
                if(mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
