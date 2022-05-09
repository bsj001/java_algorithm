package com.bsj.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val = {1000,3000,2500};//物品的价值
        int m = 4;//背包容量
        int n = val.length;//物品的个数

        //创建二维数组
        int[][] v = new int[n+1][m+1];
        //初始化二维数组，把第一行和第一列置为0
        for(int i=0;i<v.length;i++){
            v[i][0] = 0;
        }
        for(int i=0;i<v[0].length;i++){
            v[0][i] = 0;
        }
        //创建记录放入背包物品的数组
        int[][] path = new int[n+1][m+1];

        //根据前面得到公式来动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                if(w[i-1]>j){//第w[i-1]个物品重量大于当前背包,所以不放入背包，从上面v[i-1][j]复制一个价值粘贴到当前v[i][j]
                    v[i][j] = v[i-1][j];
                }else{//可以放入背包
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，不能直接使用上面的公式，需要使用if-else体现公式
                    if(v[i][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }


        //输出二维数组
        for(int i=0;i<v.length;i++){
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }


        //输出记录背包放入物品的数组
        int i = path.length - 1;
        int j = path[0].length - 1;
        while(i>0 && j>0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入背包\n",i);
                j -= w[i-1];
            }
            i--;
        }
    }
}
