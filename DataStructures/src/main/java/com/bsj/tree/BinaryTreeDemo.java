package com.bsj.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"公孙胜");
        HeroNode node5 = new HeroNode(5,"李逵");
        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        //
//        System.out.println("前序遍历");
//        binaryTree.preOrder();//1,2,3,4
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();//2,1,3,4
//        System.out.println("后序遍历");
//        binaryTree.postOrder();//2,4,3,1


        //在node3添加一个左子节点后
//        System.out.println("在添加一个节点后的情况：");
        node3.setLeft(node5);
//        System.out.println("前序遍历");
//        binaryTree.preOrder();//1,2,3,5,4
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();//2,1,5,3,4
//        System.out.println("后序遍历");
//        binaryTree.postOrder();//2,5,4,3,1

        //测试
        HeroNode res = binaryTree.postOrderSearch(5);
        if(res != null){
            System.out.printf("找到了,信息为no = %d,name = %s]\n",res.getNo(),res.getName());
        }else {
            System.out.println("没找到");
        }
    }
}

//创建二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

}


//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认为空
    private HeroNode right;//默认为空

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);//先输出父节点
        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder(){
        //递归向后子树后序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);//输出父节点
    }

    /*
    * 前序遍历查找
    * */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序查找！！！");
        //先比较当前节点是不是
        if(this.no == no){
            return this;
        }
        //如果不是，则判断当前左子节点是否为空，如果不为空，则向左递归查找
        //如果左递归查找找到，返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){//说明左子树找到
            return resNode;
        }


        //右
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    /*
     * 中序遍历查找
     * */
    public HeroNode infixOrderSearch(int no){
        //如果不是，则判断当前左子节点是否为空，如果不为空，则向左递归查找
        //如果左递归查找找到，返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){//说明左子树找到
            return resNode;
        }
        System.out.println("进入中序查找！！！");
        //先比较当前节点是不是
        if(this.no == no){
            return this;
        }

        //右
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }


    /*
     * 后序遍历查找
     * */
    public HeroNode postOrderSearch(int no){
        //如果不是，则判断当前左子节点是否为空，如果不为空，则向左递归查找
        //如果左递归查找找到，返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){//说明左子树找到
            return resNode;
        }
        //右
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }

        System.out.println("进入后序查找！！！");
        //先比较当前节点是不是
        if(this.no == no){
            return this;
        }

        return resNode;
    }
}
