package com.bsj.graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻节点矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//记录某个节点是否被访问

    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
//        isVisited = new boolean[n];
    }
    //插入顶点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /*
    * 图的常用方法
    * */
    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //返回边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回节点i(下标)对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link :edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //返回第一个邻接节点的下标w
    //如果存在就返回值，不存在就返回-1
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size() ; j++) {
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int j=v2+1;j<vertexList.size();j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    public void dfs(boolean[] isVisited,int i){
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i)+"->");
        //将该节点置为已访问
        isVisited[i] = true;
        //查找该节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while(w != -1){
            if(!isVisited[w]){//说明有
                dfs(isVisited,w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs()方法进行重载，遍历所有的节点，并进行dfs
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        //遍历所有的节点，进行dfs(回溯)
        for(int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }

    /*
    *对一个节点进行广度优先遍历的方法
    * */
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头节点对应的下标
        int w;//邻接节点w
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i)+"->");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出头节点的下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while(w != -1){
                if( !isVisited[w]){
                    System.out.print(getValueByIndex(w)+"->");
                    //标记已访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱节点，找w后面的下一个邻节点
                w = getNextNeighbor(u,w);//体现出我们的广度优先
            }
        }
    }
    //重载广度优先
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i=0;i<getNumOfVertex();i++){
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
    public static void main(String[] args) {
        //test
        int n = 8;//节点的个数
//        String[] vertexts = {"A","B","C","D","E"};
        String[] vertexts = {"1","2","3","4","5","6","7","8"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for(String vertex: vertexts){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
//        graph.insertEdges(0,1,1);
//        graph.insertEdges(0,2,1);
//        graph.insertEdges(1,2,1);
//        graph.insertEdges(1,3,1);
//        graph.insertEdges(1,4,1);
        //测试广度优先VS深度优先
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);
        graph.insertEdges(3,7,1);
        graph.insertEdges(4,7,1);
        graph.insertEdges(2,5,1);
        graph.insertEdges(2,6,1);
        graph.insertEdges(5,6,1);

        //输出图
        graph.showGraph();
        //深度遍历
        System.out.println("深度遍历");
        graph.dfs(); //A->B->C->D->E
        System.out.println("广度优先");
        graph.bfs(); //A->B->C->D->E
    }


}
