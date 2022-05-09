package com.bsj.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可连接
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};
        //创建对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();

//        graph.dsj(6);//下一步结果：A(2)B(3)C(9)D(10)E(4)F(6)G(0)
        graph.dsj(2);//下一步结果：A(7)B(12)C(0)D(17)E(8)F(13)G(9)
        graph.showDijkstra();
    }
}
//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标,会动态更新
    public int[] pre_visited;
    //记录出发顶点到其它所有顶点的距离,比如G为出发顶点,就会记录G到其它顶点的距离,会动态更新,求出最短距离就会存放到dis
    public int[] dis;

    //构造器
    public VisitedVertex(int length,int index){
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];

        //初始化dis数组
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1;//设置出发顶点被访问过
        this.dis[index] = 0;//设置出发顶点的访问距离为0
    }

    /*
    * 判断index顶点是否被访问过
    *  如果访问过,返回 true 否则,返回false
    * */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }

    /**
     * 更新顶点的前驱为index节点
     */
    public void updatePre(int pre,int index){
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     */
    public int getDis(int index){
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问节点，比如这里的G完后，就是A点作为新的访问顶点（注意不是出发顶点）
     */
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    /**
     * 输出最后的结果
     *
     */
    public void show(){
        //输出already_arr
        for(int i: already_arr){
            System.out.print(i+" ");
        }
        System.out.println();
        //输出pre_visited
        for(int i:pre_visited){
            System.out.print(i+" ");
        }
        System.out.println();
        //输出dis
//        for(int i:dis){
//            System.out.print(i+" ");
//        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for(int i:dis){
            if(i != 65535){
                System.out.print(vertex[count] + "("+i+")");
            }else {
                System.out.print("N");
            }
            count++;
        }
        System.out.println();
    }
}



class Graph{
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//表示已经访问过的顶点

    //构造器
    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph(){
        for(int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }
    /**
     *
     *
     */
    public void showDijkstra(){
        vv.show();
    }


    /**
     * 编写迪杰斯特拉算法
     * index表示出发的顶点
     */
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length,index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for (int i = 0; i < vertex.length; i++) {
            index = vv.updateArr();//选择并返回新的访问节点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }
    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     */
    private void update(int index){
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len的含义是:从出发顶点到index顶点的距离+从index顶点到i顶点的距离的和
            len = vv.getDis(index)+matrix[index][i];
            //如果i顶点没有被访问过,并且len小球出发顶点到i顶点的距离,就需要更新
            if(!vv.in(i)&&len<vv.getDis(i)){
                vv.updatePre(i,index);//更新i顶点的前驱为index顶点
                vv.updateDis(i,len);//更新出发顶点到i顶点的距离
            }
        }
    }
}

