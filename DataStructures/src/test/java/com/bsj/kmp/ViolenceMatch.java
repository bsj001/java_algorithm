package com.bsj.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index="+index);
    }

    public static int violenceMatch(String str1,String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;


        int i = 0;//s1的指向索引
        int j = 0;//s2的指向索引

        while(i<s1Len && j<s2Len){
            if(s1[i]==s2[j]){//匹配成功
                i++;
                j++;
            }else {//匹配失败
                i = i - (j-1);//重回j前然后加1
                j = 0;//重新匹配str2
            }
        }

        if(j == s2Len){
            return i-j;
        }
        return -1;//无匹配
    }
}
