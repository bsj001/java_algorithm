package com.bsj.recursion;

public class MiGong {

    /*
    * 扩展：寻找最短路径：
    *   可用各种不同的策略，然后分别保存得到的地图模型，然后分别统计2的个数得到最短路径
    * */
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for(int i=0;i<8;i++){
            map[i][6] = 1;
            map[i][0] = 1;
        }
        //左右全部置为1
        for(int i=0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //设置挡板
        map[3][2] = 1;
        map[3][1] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;
//        map[3][3] = 1;
//        map[3][4] = 1;
//        map[3][5] = 1;
        //打印地图
        System.out.println("原始地图：");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

//        setWay(map,1,1);
        setWay2(map,1,1);
        System.out.println("小球走过并标识过的地图为：");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小一球找路
     * 说明
     * 1.map表示地图
     * 2,i,j表示从地图的哪个位置开始出发（1，2）
     * 3,如果小球能到map[6][5]位置，则说明通路能找到
     * 4,约定：当map[i][j]为0表示该点没有走过， 当为1表示墙，如果为2，表示通路，可以走 ，3 表示该路已经走过，走不通
     * 5,走迷宫时，需要确定一个策略，下->右->上->左 ，如果该点走不通再回溯
     * */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){//通路已经找到ok
            return true;
        }else {
            if(map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略下 右 上 左
                map[i][j] = 2;//假定该点是可以走通
                if(setWay(map,i+1,j)){//向下走
                    return true;
                }else if(setWay(map,i,j+1)){//向右走
                    return true;
                }else if(setWay(map,i-1,j)){//向上走
                    return true;
                }else if(setWay(map,i,j-1)){//向左走
                    return true;
                }else {//说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j] != 0;那可能是 1,2,3
                return false;
            }
        }
    }

    //修改策略 上 右 下 左
    public static boolean setWay2(int[][] map,int i,int j){
        if(map[6][5] == 2){//通路已经找到ok
            return true;
        }else {
            if(map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略下 右 上 左
                map[i][j] = 2;//假定该点是可以走通
                if(setWay(map,i-1,j)){//向上走
                    return true;
                }else if(setWay2(map,i,j+1)){//向右走
                    return true;
                }else if(setWay2(map,i+1,j)){//向下走
                    return true;
                }else if(setWay2(map,i,j-1)){//向左走
                    return true;
                }else {//说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            }else {//如果map[i][j] != 0;那可能是 1,2,3
                return false;
            }
        }
    }
}
