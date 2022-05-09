package com.bsj.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        //为了方便
        //1,遍历arr数组
        //2，将arr的每个元素构成一个Node
        //3，将Node放入到ArrayList中

        List<Node> nodes = new ArrayList<Node>();
        for(int value: arr){
            nodes.add(new Node(value));
        }
        //排序 从小到大
        Collections.sort(nodes);

        System.out.println("nodes = "+ nodes);

        Node root = createHuffmanTree(nodes);
        preOrder(root);
    }
    //前序遍历
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("该赫夫曼树是空树");
        }
    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size()>1){
            //排序
            Collections.sort(nodes);
            //获取最小的两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构造父节点
            Node parent = new Node(leftNode.value+rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从nodes中删除左右子节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把parent加入到nodes中
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //this写在前面 从小到大排序
        return this.value - o.value;
    }
}