package com.bsj.kruskal;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KrusKalAlgorithm {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;
    //构造器
    public KrusKalAlgorithm(char[] vertexs,int[][] matrix){
        //初始化顶点数和边的个数
        int vlen = vertexs.length;
        //初始化顶点，复制拷贝的方式 ，不改变原值
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，复制拷贝
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j] != INF){
                    edgeNum++;
                }
            }
        }
    }
    //打印邻接矩阵
    public void print(){
        System.out.println("邻接矩阵为:");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%-12d",matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    //对边进行排序处理
    public void sortEdge(Edata[] edges){
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if(edges[j].weight>edges[j+1].weight){
                    Edata temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    //获取当前顶点的下标
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch){
                return i;
            }
        }
        return -1;//找不到
    }

    //获取下标为i的顶点的终点,用于后面判断两个顶点的终点是否相同
    //ends数组就是记录了各个顶点对应的终点是哪个,ends数组是在遍历过程中,逐步形成
    //i 表示传入的顶点对应的下标
    //返回的就是下标为i的这个顶点对应的终点的下标
    private int getEnd(int[] ends,int i){
        while(ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    //获取图中边,放到Edata[]数组中,后面我们需要遍历该数组
    //是通过matrix邻接矩阵来获取
    public Edata[] getEdges(){
        int index = 0;
        Edata[] edges = new Edata[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j] != INF){
                    edges[index++] = new Edata(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }

        return edges;
    }

    //克鲁斯卡尔算法
    public void kruskal(){
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中的每个顶点在最小生成树中的终点
        //创建结果数组,保存最后的最小生成树
        Edata[] rets = new Edata[edgeNum];

        //获取图中所有的边的集合,一共有12条边
        Edata[] edges = getEdges();
//        System.out.println("图的集合="+ Arrays.toString(edges)+"共"+edges.length+"条边");

        //按照边的权值大小进行排序(从小到大)
        sortEdge(edges);

        //遍历edges数组,将边添加到最小生成树中,判断是准备加入的边是否形成了回路,如果没有,就加入rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends,p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends,p2);
            //是否构成回路
            if(m != n){
                ends[m] = n;//设置m在"已有最小生成树"中的终点
                rets[index++] = edges[i];//把这条边加入到数组中
            }
        }
        //统计并打印"最小生成树",输出rets
//        System.out.println("最小生成树为:"+Arrays.toString(rets));
        System.out.println("最小生成树为:");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A','B','C','D','E','F','G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                {0,12,INF,INF,INF,16,14},
                {12,0,10,INF,INF,7,INF},
                {INF,10,0,3,6,6,INF},
                {INF,INF,3,0,4,INF,INF},
                {INF,INF,5,4,0,2,8},
                {16,7,6,INF,2,0,9},
                {14,INF,INF,INF,8,9,0},
        };

        KrusKalAlgorithm krusKalAlgorithm = new KrusKalAlgorithm(vertexs, matrix);
//        krusKalAlgorithm.print();
        Edata[] edges = krusKalAlgorithm.getEdges();
//        System.out.println("排序前:"+Arrays.toString(edges));
//        krusKalAlgorithm.sortEdge(edges);
//        System.out.println("排序后:"+Arrays.toString(edges));

        krusKalAlgorithm.kruskal();
    }
}

//定义一个类,表示一条边
class Edata{
    char start;//表示一个顶点
    char end;//表示另一个顶点
    int weight;//表示边的权值

    public Edata(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edata{" +
                "<" + start +
                 "," +end +
                ">=" + weight +
                '}';
    }
}