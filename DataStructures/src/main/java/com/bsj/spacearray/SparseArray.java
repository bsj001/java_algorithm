package com.bsj.spacearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0：表示没有棋子，1表示黑子 2表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][4] = 232;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int[] row : chessArr1){
            for(int item : row){
                System.out.printf("%d\t",item); //printf
//                System.out.print(item + "\t"); //print
            }
            System.out.println(); //println
        }

        //将原始数组转化为稀疏数组
        int sum = 0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println("稀疏数组为：");
        for(int l = 0;l<sparseArr.length;l++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[l][0],sparseArr[l][1],sparseArr[l][2]);
        }

        //将稀疏数组恢复成原始数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for(int i = 1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出原始的chessArr2的二维数组
        System.out.println("原始的二维数组");
        for(int[] row : chessArr2){
            for(int item : row){
                System.out.printf("%d\t",item); //printf
//                System.out.print(item + "\t"); //print
            }
            System.out.println(); //println
        }

    }
}
