package com.bsj.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder(); //1,2,4,5,3,6,7
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrayBinaryTree{
    private int[] arr;//存储数据节点的数组
    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }
    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
    //编写一个方法，完成顺序存储二叉树的前序遍历
    public void preOrder(int index){
        //先判断数组是否为空，或数组长度为0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左遍历
        //先判断向左是否越界
        if(index*2+1<arr.length){
            preOrder(index*2+1);
        }
        //向右遍历
        //判断向右是否越界
        if(index*2+2<arr.length){
            preOrder(index*2+2);
        }
    }
}
