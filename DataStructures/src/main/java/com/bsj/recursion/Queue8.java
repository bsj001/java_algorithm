package com.bsj.recursion;

public class Queue8 {
    //定义一个Max表示共有多少个皇后
    int max = 8;
    //定义数组array,保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
//        System.out.printf("一共有%d种解法\n",count);
        System.out.printf("一共判断%d次冲突\n",judgeCount);
    }
    //编写一个方法，放置第n个皇后
    //特别注意：check是每一次递归时，进入到check中都有for(int i=0;i<max;i++),因此会有回溯
    private void check(int n){
        if(n == max){
            count++;
            System.out.printf("第%d种解法\n",count);
            print();//n = 8,其实8个皇后已经放好了
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i=0;i<max;i++){
            //先把当前这个皇后n，放到该行的第1列
            array[n] = i;
            if(judge(n)){//不冲突
                //接着放n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行array[n] = i,即将第n个皇后，放置在本行的后移的一个位置
        }
    }


    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++) {
            //说明
            //1，array[i] == array[n] 说明在同一列
            //2,Math.abs(n - i) == Math.abs(array[n] - array[i]) 说明在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
    //打印
    private void print(){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+"\t");
        }
        System.out.println();
    }
}
