package com.bsj.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str2 = "AAAB";

        int[] next = kmpNext(str2);
        System.out.println("next="+ Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index="+index);
    }


    //kmp搜索算法
    public static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0,j = 0; i < str1.length(); i++) {
            //当str1.charAt(i) != str1.charAt(j)匹配失败时，重新调整j的指针位置
            //kmp算法的核心
            while(j>0&&str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            //当str1.charAt(i) == str1.charAt(j)匹配当前第j个字符成功时
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }

            if(j == str2.length()){//完全匹配成功
                return i-j+1;//返回匹配成功的第一个字符的指针位置
            }
        }
        return -1;
    }


    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//默认第一个为0
        for (int i = 1,j = 0; i < next.length; i++) {
            //当dest.charAt(i) != dest.charAt(j)时，需要重新调整j的指针位置
            while(j>0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            //当dest.charAt(i) == dest.charAt(j)时，匹配成功
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
